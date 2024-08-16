package com.shaft.itextservice.dsa.recursion;

public class PrintOneTen {

    public static void main(String[] args) {
        printNumber(10);
    }

    public static void printNumber(int num) {

        int count = 0;
        if (count == num) {
            return;
        }
        count = count + 1;
        print(count);
        printNumber(count);
    }

    public static void print(int num) {
        System.out.println(num);
    }

}
