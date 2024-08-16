package com.shaft.itextservice.test;

public class TestString {

    public static void main(String[] args) {
        String abc = "abc,abc,ssd";

        /*if (!abc.contains(",")){
            System.out.println("Test");
        } else {
            System.out.println("Failed");
        }*/

        String s1 = "test";
        String s2 = new String("test");
        String s3 = "test";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        printObjectAddress(s1);
        printObjectAddress(s2);
        printObjectAddress(s3);

    }

    public static void printObjectAddress(Object obj) {
        // Print the identity hash code of the object
        int identityHashCode = System.identityHashCode(obj);
        String address = Integer.toHexString(identityHashCode);
        System.out.println("Object address: " + address);
    }
}
