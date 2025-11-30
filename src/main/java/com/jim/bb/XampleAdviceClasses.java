package com.jim.bb;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Static classes that perform the injection are nested in this class.
 */
public class XampleAdviceClasses {

    public static IInjectable injectMe = new XampleInjectedMethods();

    /**
     * This handles the injection on entering a method.
     */
    public static class EnterAdvice {
        @Advice.OnMethodEnter
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (injectMe == null) {
                return;
            }
            if (info.endsWith(">()")) {
                //System.out.println("[BBTester Trace Method Entry] Ignoring: " + info);
            } else {
                //System.out.println("[BBTester Trace Method Entry] Injecting: " + info);
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
     * This handles the injection on exit from a method.
     */
    public static class ExitAdvice {
        @Advice.OnMethodExit
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (injectMe == null) {
                return;
            }
            if (info.endsWith(">()")) {
                //System.out.println("[BBTester Trace Method Exit] Skipping: " + info);
            } else {
                //System.out.println("[BBTester Trace Method Exit] Injecting: " + info);
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