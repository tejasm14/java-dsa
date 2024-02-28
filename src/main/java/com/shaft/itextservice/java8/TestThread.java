package com.shaft.itextservice.java8;

public class TestThread {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                System.out.println("inside run method");
            }
        });

        for (int i = 1; i <= 10; i++) {
            System.out.println("outside run");
        }

        thread.start();

    }



}
