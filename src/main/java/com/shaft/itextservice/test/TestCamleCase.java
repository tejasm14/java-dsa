package com.shaft.itextservice.test;

public class TestCamleCase {

    public static void main(String[] args) {
        System.out.println(toCamelCase("Colour Code"));
    }

    private static String toCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (char c : input.toCharArray()) {
            if (c == ' ' || c == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }
}
