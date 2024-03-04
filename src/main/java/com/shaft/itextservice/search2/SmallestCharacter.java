
///https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
package com.shaft.itextservice.search2;

public class SmallestCharacter {

    public static void main(String[] args) {

        char letters[] = {'c','f','j'};
        char target = 'g';
        System.out.println(smalltestChar(letters,target));
    }

    public static char smalltestChar(char letters[], char target) {

        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {

            int mid = start + (end-start) / 2;

            if (target < letters[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return letters[start % letters.length];
    }



}
