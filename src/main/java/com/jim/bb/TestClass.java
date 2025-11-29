package com.jim.bb;

/**
 * This is the class we mess around with - injecting into its methods
 */
@InjectIntoMyMethods
public class TestClass {

    public String getString() {
        return "TestClass.getString() Called";
    }
}
