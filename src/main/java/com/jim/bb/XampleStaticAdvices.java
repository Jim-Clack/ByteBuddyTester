package com.jim.bb;

import net.bytebuddy.asm.Advice;

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
        @Advice.OnMethodEnter
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (!info.endsWith(">()")) {
                injectMe.injectOnEnter(info);
            }
        }
    }

    /**
     * This advice class handles the injection on exit from a method.
     */
    public static class ExitAdvice {
        @Advice.OnMethodExit
        public static void onMethodEnter(@Advice.Origin("#t.#m()") String info) {
            if (!info.endsWith(">()")) {
                injectMe.injectOnExit(info);
            }
        }
    }

    // Note that instead of calling "injectMe.injectOnEnter(info);" above, you could
    // use reflection, which would be handy if you were injecting code that was
    // third-party and you had no sources to it. Just replace that line with:
    /*
    // @SuppressWarnings("unused")
    ...
    try {
        Method method = thirdPartyObject.getClass().getMethod("methodToCall", String.class);
        Class<?>[] parameterTypes = method.getParameterTypes();
        method.invoke(thirdPartyObject, info);
    } catch (Exception e) {
        System.err.println("[BBTester Trace Method Enter] Error calling: " + e.getMessage());
    }
    */

}