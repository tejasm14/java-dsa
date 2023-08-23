package com.shaft.itextservice.arrays;

import java.util.Arrays;

public class TestArray {

    public static void main(String[] args) {

        int[] arr = {12,150,545,45,21,64};
        System.out.println("Before reverse :");
        System.out.println(Arrays.toString(arr));
        reverse(arr);
        System.out.println("After reverse : ");
        System.out.println(Arrays.toString(arr));

        //TestArray.swap(arr,1,3);

        //System.out.println("Maximum number : "+max(arr));
        //System.out.println(Arrays.toString(arr));
    }

    private static int reverse(int[] arr) {

        if (arr.length == 0) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            swap(arr,start,end);
            start++;
            end--;
        }

        return 1;
    }

    private static int max(int[] arr) {

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
