package com.jim.bb;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Classes that perform the injection are nested in this class.
 */
public class AdviceClasses {

    public static IInjectable injectMe = new InjectMe();

    /**
     * This handles the injection on entry to a method.
     */
    public static class EnterAdvice {
        @Advice.OnMethodEnter
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (injectMe == null) {
                return;
            }
            if (info.contains(".<")) {
                //System.out.println("[BBTester Trace Method Entry] Skipping: " + info);
            } else {
                //System.out.println("[BBTester Trace Method Entry] Injecting: " + info);
                try {
                    Method method = injectMe.getClass().getMethod("injectOnEntry", String.class);
                    // Class<?>[] parameterTypes = method.getParameterTypes();
                    method.invoke(injectMe, info);
                } catch (Exception e) {
                    System.err.println("[BBTester Trace Method Entry] Error calling: " + e.getMessage());
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
            if (info.contains(".<")) {
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