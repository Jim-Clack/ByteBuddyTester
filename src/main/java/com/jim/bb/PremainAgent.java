package com.jim.bb;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.annotation.Annotation;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * Class with premain() agent to inject an Advice class into methods.
 */
public class PremainAgent {

    /** Wiring for class injection, key=annotation, value=static class with advice. */
    private static final Map<Class<?>, Class<?>> classWiring = new HashMap<>();

    /**
     * Called before main() when so-directed by the MANIFEST - triggers instrumentation.
     * @param agentArgs not used here.
     * @param inst the instrumentation object we use to perform the transforms
     */
    @SuppressWarnings("unchecked")
    public static void premain(String agentArgs, Instrumentation inst) {
        new XampleConfiguration().getClassWiring(classWiring);
        // find classes with specified annotations and wire them
        for(Class<?> key : classWiring.keySet()) {
            if(key != null && Annotation.class.isAssignableFrom(key)) {
                new AgentBuilder.Default()
                        .type(ElementMatchers.hasAnnotation(
                                ElementMatchers.annotationType((Class<? extends Annotation>) key)))
                        .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                                builder.visit(Advice.to(classWiring.get(key)).on(ElementMatchers.any()))
                        )
                        //          .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                        .installOn(inst);
            }
        }
    }

}