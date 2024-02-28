package com.shaft.itextservice.java8;

import java.util.Arrays;
import java.util.function.DoubleConsumer;

public class MethodReference {

    public static void main(String[] args) {

        System.out.println(Math.sqrt(9));

        DoubleConsumer sqrt = Math::sqrt;




    }
}
