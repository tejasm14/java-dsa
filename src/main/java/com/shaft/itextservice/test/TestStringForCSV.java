package com.shaft.itextservice.test;

public class TestStringForCSV {

    public static void main(String[] args) {
        String heading = "GENERAL FEATURE_MODEL NAME";
        String values [] = heading.split("_");
        for (String value : values) {
            System.out.println(value);
        }

    }
}
