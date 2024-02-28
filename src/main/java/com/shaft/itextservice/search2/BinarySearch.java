package com.shaft.itextservice.search2;

public class BinarySearch {


    public static void main(String[] args) {

        int arr[] = {-18,-12,12,20,30,45,67,77,89,90,100};
        int target = 77;
        int element = binarySearch(arr,target);
        System.out.println("Element index : "+element);
    }

    public static int binarySearch(int arr[], int target) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }






}
