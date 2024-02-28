package com.shaft.itextservice.java8.refer;

public class RefDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Method reference");

        /*WorkInter workInter = () -> {
            System.out.println("This is task");
        };
        workInter.doStuff();*/
        WorkInter workInter = Stuff::doStuff;
        workInter.doTask();

        Runnable runnable = Stuff::threadTask;












    }


}
