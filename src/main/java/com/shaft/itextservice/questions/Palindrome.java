/*
leetcode question
*  https://leetcode.com/problems/palindrome-number/
* */

package com.shaft.itextservice.questions;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
    }
    public static boolean isPalindrome(int x) {

        if ( x < 0) {
            return false;
        }

        int ans = 0;
        int compare = x;
        while (x > 0) {
            int rm = x % 10;
            ans = ans * 10 + rm;
            x = x / 10;
        }

        return compare == ans;
    }


}
