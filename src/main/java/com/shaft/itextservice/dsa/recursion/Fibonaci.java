package com.shaft.itextservice.dsa.recursion;

public class Fibonaci {

    public static void main(String[] args) {
        int ans = fibo(4);
        System.out.println(ans);
    }


    public static int fibo(int num) {
        if (num < 2) {
            return num;
        }
        return fibo(num - 1) + fibo(num - 2);
    }
}
