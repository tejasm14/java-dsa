package com.shaft.itextservice.test;

public class Main {
    public static void main(String[] args) {
        /*String[] myArray = {"pattern", "category", "usp", "feature-1-name", "feature-1-deta"};

        String col = "-pattern";
        String abc  = col;
        System.out.println(abc);

        System.out.println();*/

        /*for (String item : myArray) {
            // Remove leading and trailing hyphens
            //String cleanItem = item.replaceAll("^-+|-+$", "");
            // Remove leading and trailing white spaces
            //cleanItem = cleanItem.trim();

            System.out.println(item);

            //System.out.println(cleanItem);
        }*/

        String originalString = "Hello\uFEFFWorld! This is a #Test_String with^special_characters.";
        // Use a regular expression to match and remove special characters
        String cleanedString = originalString.replaceAll("[^\\\\w\\\\s]+", "");
        System.out.println(cleanedString);

    }
}






