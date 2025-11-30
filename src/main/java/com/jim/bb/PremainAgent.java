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

    /** WIring for injection, key=annotation, value=static class with advice. */
    private static final Map<Class<?>, Class<?>> wiring = new HashMap<>();

    /** Used only to check keys to ensure they are Annotations. */
    private static final Class<? extends Annotation> AnnotationBase = Annotation.class;

    /**
     * Called before main() when so-directed by the MANIFEST - triggers instrumentation.
     * @param agentArgs not used here.
     * @param inst the instrumentation object we use to perform the transforms
     */
    @SuppressWarnings("unchecked")
    public static void premain(String agentArgs, Instrumentation inst) {
        new XampleConfiguration().getWiring(wiring);
        // find classes with specified annotations and wire them
        for(Class<?> key : wiring.keySet()) {
            if(!AnnotationBase.isAssignableFrom(key)) {
                continue;
            }
            if(key.isAnnotation()) {
                new AgentBuilder.Default()
                        .type(ElementMatchers.hasAnnotation(
                                ElementMatchers.annotationType((Class<? extends Annotation>) key)))
                        .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                                builder.visit(Advice.to(wiring.get(key)).on(ElementMatchers.any()))
                        )
                        //          .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                        .installOn(inst);
            }
        }
    }

}