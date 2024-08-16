package com.shaft.itextservice.sort;

public class SortingAlgorithm {

    public static void main(String[] args) {
        int[] arr = {5,6,1,9,2,8};
        //bubble(arr);
        /*System.out.println("Bubble sort");
        for (int a :arr) {
            System.out.print(a+" ");
        }*/
        //selection(arr);
        insertion(arr);
        System.out.println("insertion sort");
        for (int n:arr) {
            System.out.print(n+" ");
        }

    }

    public static void selection(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            int last = arr.length - i - 1;
            int max = max(arr, 0, last);
            swap(arr,max,last);
        }

    }

    public static int max(int arr[], int start, int last) {
        int max = start;
        for (int i = start; i <= last; i++) {
            if (arr[max] < arr[i]) {
                max = i;
            }
        }
        return max;
    }

    public static void swap(int arr[], int first, int second) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[second];
            arr[first] = arr[second];
            arr[second] = temp;

        }
    }

    public static void bubble(int arr[]) {

        boolean isSort;

        for (int i = 0; i < arr.length; i++) {
            isSort = false;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j-1]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
                isSort = true;
            }
            if (!isSort) {
                break;
            }
        }
    }

    public static void insertion(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr,j,j-1);
                }
            }
        }

    }


}
