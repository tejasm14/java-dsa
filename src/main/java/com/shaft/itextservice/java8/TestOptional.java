package com.shaft.itextservice.java8;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TestOptional {
    public static void main(String[] args) {

        //String testString = null;
        String testString = "This is string";
        Optional<String> testOption = Optional.ofNullable(testString);

        // System.out.println(testOption.isPresent());
        if (testOption.isPresent()) {
            System.out.println("Value present");
        } else {
            System.out.println("Value is not present");
        }

        testOption.ifPresent(val -> System.out.println(val.toUpperCase()));

        System.out.println(testOption.orElse("N/A"));




    }
}
