/*
* 1. Program to search in the 2D array
* 2. Program to find max in 2D array
* */

package com.shaft.itextservice.arrays;

import java.util.Arrays;

public class Search2DArray {

    public static void main(String[] args) {

        int[][] arr = {
                {14,12,32},
                {23,45,22,45,76},
                {34,56,78,33,9,2}
        };

        int [] serach = searchNumber(arr,78);
        System.out.println(Arrays.toString(serach));
        System.out.println("Maximimum number : "+maxNumber(arr));

    }

    public static int[] searchNumber(int [][] arr, int target) {

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {

                if (arr[row][col] == target) {
                    return new int[]{row,col};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static int maxNumber(int [][] arr) {
        int max = Integer.MIN_VALUE;
        for (int[] ints : arr) {

            for (int anInt : ints) {
                if (anInt > max) {
                    max = anInt;
                }
            }
        }
        return max;
    }
}
