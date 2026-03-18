package com.shaft.itextservice.dsa.recursion;

public class PrintNumberRecursion {

    public static void main(String[] args) {
        int ans = printNumber(1);
        System.out.println(ans);
    }

    public static int  printNumber(int num) {

        if (num == 5) {
            return num;
        }
        System.out.println(num);
        return printNumber(num + 1);
    }


}
