package com.shaft.itextservice.questions;

public class TwoSums {

    public static void main(String[] args) {
        int arr[] = {2,7,11,15};
        int target = 9;
        TwoSums obj = new TwoSums();
        obj.twoSum(arr,target);
    }


    public int[] twoSum(int[] nums, int target) {

        int ans[] = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] + nums[i+1];
            if (sum == target) {
                for (int i1 = 0; i1 < ans.length; i1++) {
                    
                }
            }
        }
        return ans;
    }

}
