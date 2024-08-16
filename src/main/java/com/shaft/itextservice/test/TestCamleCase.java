package com.shaft.itextservice.test;

public class TestCamleCase {

    public static void main(String[] args) {
        System.out.println(toCamelCase("Billing State/Province"));
    }

    public static String toCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;
        boolean capitalizeAfterSlash = false;

        for (char c : input.toCharArray()) {
            if (c == ' ' || c == '_') {
                capitalizeNext = true;
            } else if (c == '/') {
                capitalizeAfterSlash = true;
            } else {
                if (capitalizeNext || capitalizeAfterSlash) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                    capitalizeAfterSlash = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }
}
