package com.jim.bb;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import javax.swing.text.Element;
import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class with premain() agent to inject an Advice class into methods.
 */
public class InjectionAgent {

    /** [CONFIGURATION: Class that will perform the injection] */
    private static final Class<?> classToPerformInjection = AdviceClasses.EnterAdvice.class;

    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                // What class is to be modified by injecting into it?
                .type(ElementMatchers.hasAnnotation(ElementMatchers.annotationType(InjectIntoMyMethods.class)))
                // What class will be injected?
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(classToPerformInjection).on(ElementMatchers.any()))
                )
                // Register a listener, helpful for debugging only
      //          .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                // Install the agent on the instrumentation instance
                .installOn(inst);
    }
}