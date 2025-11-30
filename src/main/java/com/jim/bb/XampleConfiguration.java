package com.jim.bb;

import java.util.Map;

/**
 * Supply wiring to be performed for injection.
 */
public class XampleConfiguration {

    /**
     * Populate wiring diagram.
     * @param wiring to be populated with: key=annotation and value=static advice class.
     */
    public void getWiring(Map<Class<?>, Class<?>> wiring) {
        // TODO: Read these from a configuration file
        wiring.put(XampleAnnoOnEnter.class, XampleStaticClasses.EnterAdvice.class);
        wiring.put(XampleAnnoOnExit.class, XampleStaticClasses.ExitAdvice.class);
    }
}
