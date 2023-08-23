package com.shaft.itextservice.search;

public class OrderAgnosticBS {

    public static void main(String[] args) {

        //int arr[] = {-18,-9,12,26,38,56,96,112,132}; //Ascending array
        int arr[] = {132,112,96,56,38,26,12,-9,-18};
        int target = 56;
        System.out.println("Target index position "+orderAgnosticBinarySearch(arr,target));

    }

    public static int orderAgnosticBinarySearch(int arr[], int target) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            //find whether array is in ascending order or descending
            boolean isAsc = arr[start] < arr[end];

            //find the middle element
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
