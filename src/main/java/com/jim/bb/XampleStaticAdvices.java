package com.jim.bb;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Static classes that perform the injection are nested in this class.
 */
public class XampleStaticAdvices {

    /** This class contains the methods to be injected. */
    public static XampleInjectedMethods injectMe = new XampleInjectedMethods();

    /**
     * This advice class handles the injection on entering a method.
     */
    public static class EnterAdvice {
        @SuppressWarnings("unused")
        @Advice.OnMethodEnter
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (!info.endsWith(">()")) {
                try {
                    Method method = injectMe.getClass().getMethod("injectOnEnter", String.class);
                    // Class<?>[] parameterTypes = method.getParameterTypes();
                    method.invoke(injectMe, info);
                } catch (Exception e) {
                    System.err.println("[BBTester Trace Method Entry] Error " + info + ": " + e.getMessage());
                }
            }
        }
    }

    /**
     * This advice class handles the injection on exit from a method.
     */
    public static class ExitAdvice {
        @SuppressWarnings("unused")
        @Advice.OnMethodExit
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (!info.endsWith(">()")) {
                try {
                    Method method = injectMe.getClass().getMethod("injectOnExit", String.class);
                    // Class<?>[] parameterTypes = method.getParameterTypes();
                    method.invoke(injectMe, info);
                } catch (Exception e) {
                    System.err.println("[BBTester Trace Method Exit] Error calling: " + e.getMessage());
                }
            }
        }
    }

}