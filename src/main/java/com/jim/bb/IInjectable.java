package com.jim.bb;

import java.lang.reflect.Method;

/**
 * Interface for injectable classes
 */
public interface IInjectable {

    /**
     * This is the injected method
     * @param name name of class and method that this is being injected into
     */
    void injectOnEntry(String name);

}
