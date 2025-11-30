package com.jim.bb;

/**
 * Test app for Byte Buddy testing.
 * Make sure that...
 *   Requires net.bytebuddy.byte.buddy:1.18.2
 *   The MANIFEST file must be correct
 *   A JAR facet must be created and bytebuddy must be extracted to it
 *   Run/debug configuration for a JAR app and set the path and javaagent
 */
public class App {
    public static void main(String[] args) {
        new AppTestClass().showString();
    }
}