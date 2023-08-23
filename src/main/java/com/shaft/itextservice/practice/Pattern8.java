package com.shaft.itextservice.practice;

import java.util.Scanner;

public class Pattern8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to print pattern");
        int n = Integer.parseInt(sc.next());
        int number = 1;

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(number+" ");
                number++;
            }
            System.out.println();
        }
    }
}
