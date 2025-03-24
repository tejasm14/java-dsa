package com.shaft.itextservice.dsa.recursion;

public class PrintNumberRecursion {

    public static void main(String[] args) {
        printNumber(1);
    }

    public static void printNumber(int num) {
        System.out.println(num);
        if (num == 5) {
            return;
        }
        printNumber(num + 1);
    }


}
