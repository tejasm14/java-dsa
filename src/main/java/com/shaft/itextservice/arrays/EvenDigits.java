package com.shaft.itextservice.arrays;

public class EvenDigits {

    public static void main(String[] args) {

        int[] arr = {12,1,45,4475,88};

        System.out.println("Number of even no. digits present = "+findNumbers(arr));

    }

    static int findNumbers(int[] arr) {

        if (arr.length == 0) {
            return -1;
        }

        int noEven = 0;
        for (int i : arr) {
            if (even(i)) {
                noEven++;
            }
        }
        return noEven;
    }

    private static boolean even(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num = num/10;
        }
        return count % 2 == 0;
    }


}
