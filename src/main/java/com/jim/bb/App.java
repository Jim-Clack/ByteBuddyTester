package com.jim.bb;

/**
 * Test app for Byte Buddy testing.
 * Make sure that...
 *   Requires net.bytebuddy.byte.buddy:1.18.2
 *   The MANIFEST file must be correct
 *   A JAR facet is created
 *   Run/debug configuration for a JAR app and set the path and javaagent
 *   If you change the package, there are lots of scattered tricky changes
 */
public class App {
    public static void main(String[] args) throws Exception {
        new TestClass().showString();
    }
}