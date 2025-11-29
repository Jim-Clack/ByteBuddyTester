package com.jim.bb;

/**
 * This is the class we mess around with - injecting into its methods
 */
@InjectMyMethods
public class TestClass {

    public void showString() {
        System.out.println("TestClass.getString() Called");
    }
}
