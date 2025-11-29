package com.jim.bb;

/**
 * Interface for injectable classes
 */
public interface IInjectable {

    /**
     * This is the injected method for method entry
     * @param name name of class and method that this is being injected into
     */
    void injectOnEnter(String name);

    /**
     * This is the injected method for method exit
     * @param name name of class and method that this is being injected into
     */
    void injectOnExit(String name);

}
