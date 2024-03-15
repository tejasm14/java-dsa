package com.shaft.itextservice.search2;

public class InfiniteArraySearch {

    public static void main(String[] args) {

        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 9;
        System.out.println(searchInfinte(arr,target));

    }

    public static int searchInfinte(int [] arr, int target) {

        int start = 0;
        int end = 1;

        while (target > arr[end]) {
            int temp = end + 1;
            if (end + (end - start + 1) * 2 >= arr.length) {
                end = arr.length - 1; // Set end to the last index
                break;
            }
            end = end + (end - start + 1) * 2;
            start = temp;
        }

        return binarySearch(arr,target,start,end);
    }


    public static int binarySearch(int arr[], int target, int start, int end) {

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
