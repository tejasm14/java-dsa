package com.shaft.itextservice.search2;

public class OrderAgnosticArray {

    public static void main(String[] args) {

        int[] arr = {102,95,85,75,67,55,45,35,22,14,5,2};
        int target = 55;
        int element = binarySearch(arr, target);
        System.out.println("Element found at index : "+element);
    }

    public static int binarySearch(int arr[], int target) {

        int start = 0;
        int end = arr.length - 1;

        boolean isAsc = arr[start] < arr[end];

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (isAsc) {
                if (target < arr[mid]) {
                    end = mid - 1;
                } else if (target > arr[mid]) {
                    start = mid + 1;
                }
            } else {
                if (target > arr[mid]) {
                    end = mid - 1;
                } else if (target < arr[mid]) {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }





}
