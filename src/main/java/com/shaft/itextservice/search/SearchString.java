package com.shaft.itextservice.search;

public class SearchString {

    public static void main(String[] args) {

        String name = "Tejas";
        char search = 'e';
        searchString(name,search);
    }

    private static boolean searchString(String name, char search) {

        char stringArray[] =  name.toCharArray();
        boolean flag = false;

        for (int i = 0; i < stringArray.length; i++) {
            char element = stringArray[i];
            if (element == search) {
                flag = true;
                return flag;
            }
        }
        System.out.println(search+" not found in the string");

        return true;
    }
}
