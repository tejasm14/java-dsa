package com.shaft.itextservice.java8.refer;

import java.util.Calendar;

public class Stuff {

    public static void doStuff() {
        System.out.println("This is do stuff method");
        int a = 10 + 20;
        System.out.println(a);
    }

    public static void threadTask() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
