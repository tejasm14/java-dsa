//facebook interview question
//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
package com.shaft.itextservice.search2;

public class LastPositionInArray {

    public static void main(String[] args) {

        int nums[] = {5,7,7,8,8,10};
        int target = 7;
        int ans [] = searchRange(nums,target);
        for (int num : ans) {
            System.out.print(num+" ");
        }

    }

    public static int[] searchRange(int[] nums, int target) {

        int output[] = {-1,-1};
        int startPos = binarySearch(nums,target,true);
        int endPos = binarySearch(nums,target,false);
        output[0] = startPos;
        output[1] = endPos;
        return output;
    }

    public static int binarySearch(int nums[], int target, boolean isFirstIndex) {

        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                ans = mid;
                // we need to find the occurance thats why we are running binary search again
                if (isFirstIndex) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return ans;
    }

}
