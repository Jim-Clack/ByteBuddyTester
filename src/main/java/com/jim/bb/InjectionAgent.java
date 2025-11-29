package com.jim.bb;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * Class with premain() agent to inject an Advice class into methods.
 */
public class InjectionAgent {

    /** [CONFIGURATION: Classes that will perform the injection] */
    private static final Class<?> classToDoEntryInjection = AdviceClasses.EnterAdvice.class;
    private static final Class<?> classToDoExitInjection = AdviceClasses.ExitAdvice.class;

    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.hasAnnotation(ElementMatchers.annotationType(InjectIntoMyMethods.class)))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(classToDoEntryInjection).on(ElementMatchers.any()))
                )
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(classToDoExitInjection).on(ElementMatchers.any()))
                )
      //          .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                .installOn(inst);
    }
}