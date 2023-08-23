/*
INPUT : 5
OUTPUT :

      *****
     *****
    *****
   *****
  *****


* */

package com.shaft.itextservice.practice;

import java.util.Scanner;

public class Pattern10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to print pattern");
        int n = Integer.parseInt(sc.next());

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
