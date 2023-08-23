package com.shaft.itextservice.practice;

import java.util.Scanner;

public class Pattern4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to print pattern");
        int n = Integer.parseInt(sc.next());

        for (int i = 1; i <= n ; i++) {
            for (int j = n; j >= i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}
