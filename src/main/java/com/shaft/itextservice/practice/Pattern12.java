package com.shaft.itextservice.practice;

import java.util.Scanner;

public class Pattern12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to print pattern");
        int n = Integer.parseInt(sc.next());
        //Pattern12.pattern(n);


        int count = (int)(Math.log10(8)) + 1;
        System.out.println(count);
    }

    public static void pattern(int n) {
        for (int i = 1; i <= 2*n; i++) {
            int col = i <= n ? col=i : 2*n - i;
            for (int j = 1; j <= col; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /*
    INPUT : 5
    OUTPUT :
         *
        * *
       * * *
      * * * *
     * * * * *
      * * * *
       * * *
        * *
         *
     */
    public static void pattern2(int n) {
        for (int i = 1; i <= 2*n; i++) {
            int noOfSpace = i <= n ? n - i : i - n;
            for (int j = 1; j <= noOfSpace; j++) {
                System.out.print(" ");
            }
            int col = i <= n ? col=i : 2*n - i;
            for (int j = 1; j <= col; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    
}
