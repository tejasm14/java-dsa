package com.shaft.itextservice.search;

public class BinaryCeiling {

    public static void main(String[] args) {

        int arr[] = {2,3,5,9,14,16,8};
        System.out.println(binarySearch(arr,15));
    }

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
        return end;
    }


}
