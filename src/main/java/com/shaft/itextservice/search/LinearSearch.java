package com.shaft.itextservice.search;

import java.util.Scanner;

public class LinearSearch {

    public static void main(String[] args) {

        int arr[] = {45,22,45,534,23,2,32,56};
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the element to search :");
        int num = sc.nextInt();

        /*boolean isPresent = searchElement(arr,num);
        if (!isPresent) {
            System.out.println(num+" not found in the array");
        }*/

        boolean isPresentInRange = searchElement(arr,num,1,6);
        if (!isPresentInRange) {
            System.out.println(num+" not found in the array");
        }
    }

    /*
    *
    * */
    private static boolean searchElement(int[] arr, int num) {

        boolean flag = false;

        if (arr.length == 0) {
            flag = false;
            return flag;
        }

        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            if (element == num) {
                System.out.println("Element found at index : "+i);
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    private static boolean searchElement(int[] arr, int num, int start, int end) {

        boolean flag = false;

        if (arr.length == 0) {
            flag = false;
            return flag;
        }

        for (int i = start; i <= end; i++) {
            int element = arr[i];
            if (element == num) {
                System.out.println("Element found at index : "+i);
                flag = true;
                return flag;
            }
        }
        return flag;
    }


}
