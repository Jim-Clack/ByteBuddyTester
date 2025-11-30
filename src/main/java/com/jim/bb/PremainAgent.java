package com.jim.bb;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * Class with premain() agent to inject an Advice class into methods.
 */
public class PremainAgent {

    /** [CONFIGURATION: Classes that will perform the injection] */
    private static final Class<?> classToDoEnterInjection = XampleAdviceClasses.EnterAdvice.class;

    /** [CONFIGURATION: Classes that will perform the injection] */
    private static final Class<?> classToDoExitInjection = XampleAdviceClasses.ExitAdvice.class;

    /**
     * Called before main() when so-directed by the MANIFEST - triggers instrumentation.
     * @param agentArgs not used here.
     * @param inst the instrumentation object we use to perform the transforms
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.hasAnnotation(ElementMatchers.annotationType(InjectMyMethods.class)))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(classToDoEnterInjection).on(ElementMatchers.any()))
                )
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(classToDoExitInjection).on(ElementMatchers.any()))
                )
      //          .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                .installOn(inst);
    }
}