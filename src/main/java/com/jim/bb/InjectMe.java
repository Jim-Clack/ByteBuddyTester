package com.jim.bb;

import java.lang.reflect.Method;

/**
 * Class to be injected
 */
public class InjectMe implements IInjectable {

    /**
     * This is the injected method
     * @param name name of class and method that this is being injected into
     */
    @Override
    public void injectOnEntry(String name) {
        System.out.println("Injection Handled When Calling " + name + " !!!");
    }
}
