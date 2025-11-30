package com.jim.bb;

/**
 * Class to be injected
 */
public class XampleInjectedMethods {

    /**
     * This is the injected method for method entry
     * @param name name of class and method that this is being injected into
     */
    public void injectOnEnter(String name) {
        System.out.println(">>> Injection Handled On Enter " + name + " !!!");
    }

    /**
     * This is the injected method for method exit
     * @param name name of class and method that this is being injected into
     */
    public void injectOnExit(String name) {
        System.out.println(">>> Injection Handled On Exit " + name + " !!!");
    }

}
