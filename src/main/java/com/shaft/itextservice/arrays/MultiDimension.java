package com.shaft.itextservice.arrays;

import java.util.Arrays;

public class MultiDimension {

    public static void main(String[] args) {

        int arr[][] = new int[3][3];

        int arr2[][] = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println(Arrays.toString(arr2));
    }
}
