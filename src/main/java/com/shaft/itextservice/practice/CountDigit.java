package com.shaft.itextservice.practice;

import java.util.Scanner;

public class CountDigit {
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number : ");
        int num = sc.nextInt();
        int count = 0;

        while (num >= 0) {
            if (num == 0) {
                break;
            }
            int div = num / 10;
            count++;
            num = div;
        }
        System.out.println("no of digit : "+count);
    }
}
