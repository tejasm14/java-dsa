package com.shaft.itextservice.search;

public class BinarySearch {

    public static void main(String[] args) {

        int arr[] = {-2,12,15,36,45,54,68,77,79};
        int target = 54;
        int result = binarySearch(arr,target);
        System.out.println("********** Ascending array search ***********");
        System.out.println("index position : "+result);

        int arrDesc[] = {90,75,18,12,6,4,3,1};
        int descTarget = 75;
        int descResult = binarySearchDescending(arrDesc,descTarget);
        System.out.println("********** Descending array search ***********");
        System.out.println("index position : "+descResult);

    }

    //return the index  of the target element
    //returns -1  if target element not found
    public static int binarySearch(int arr[], int target) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            //find the middle element
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

    public static int binarySearchDescending(int arr[],int target) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            //find the middle element
            int mid = start + (end - start) / 2;

            if (target > arr[mid]) {
                end = mid - 1;
            } else if (target < arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}
