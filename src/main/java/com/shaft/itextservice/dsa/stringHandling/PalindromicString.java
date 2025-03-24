package com.shaft.itextservice.dsa.stringHandling;

import java.util.Objects;

public class PalindromicString {

    public static void main(String[] args) {

        String name = "abccba";
        System.out.println(isPalinDrom(name));
    }

    static boolean isPalinDrom(String string) {

        if (string.length() == 0) {
            return false;
        }

        string = string.toLowerCase();
        for (int i = 0; i < string.length()/2; i++) {
            char start = string.charAt(i);
            char end = string.charAt(string.length()-1-i);

            if (start != end) {
                return false;
            }
        }
        return true;
    }
}
