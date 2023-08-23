package com.shaft.itextservice.test;

public class TestString {

    public static void main(String[] args) {
        String abc = "abc,abc,ssd";

        if (!abc.contains(",")){
            System.out.println("Test");
        } else {
            System.out.println("Failed");
        }
    }
}
