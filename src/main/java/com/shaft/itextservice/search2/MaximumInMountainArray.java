///https://leetcode.com/problems/peak-index-in-a-mountain-array/
package com.shaft.itextservice.search2;

public class MaximumInMountainArray {

    public static void main(String[] args) {

        MaximumInMountainArray obj = new MaximumInMountainArray();
        int arr[] = {0,2,1,0};
        System.out.println(obj.peakIndexInMountainArray(arr));

    }

    public int peakIndexInMountainArray(int[] arr) {

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid+1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

}
