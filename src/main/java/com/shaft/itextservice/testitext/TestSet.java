package com.shaft.itextservice.testitext;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    public static void main(String[] args) {

        Set testSet = new HashSet();
        testSet.add("abc");
        testSet.add("Welcome");
        testSet.add("To");
        testSet.add("Geeks");
        testSet.add("For");
        testSet.add("Geek");

        String[] Geeks = (String[]) testSet.toArray(new String[testSet.size()]);

        // Accessing elements by index
        System.out.println("Element at index 3 is: "
                + Geeks[3]);







    }

}
