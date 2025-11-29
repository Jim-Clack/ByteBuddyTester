package com.jim.bb;

/**
 * Class to be injected
 */
public class InjectMe implements IInjectable {

    /**
     * This is the injected method for method entry
     * @param name name of class and method that this is being injected into
     */
    @Override
    public void injectOnEntry(String name) {
        System.out.println("Injection Handled On Entry " + name + " !!!");
    }

    /**
     * This is the injected method for method exit
     * @param name name of class and method that this is being injected into
     */
    @Override
    public void injectOnExit(String name) {
        System.out.println("Injection Handled On Exit " + name + " !!!");
    }

}
