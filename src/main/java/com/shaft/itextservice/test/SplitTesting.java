package com.shaft.itextservice.test;

import java.util.Arrays;

public class SplitTesting {

    public static void main(String[] args) {
        String time = "10:00AM – 9:00PM";
        String[] timeArray = time.split("-");
        for (String s : timeArray) {
            System.out.println(s);
        }
    }
}
