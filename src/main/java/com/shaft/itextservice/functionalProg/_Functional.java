package com.shaft.itextservice.functionalProg;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Functional {

    public static void main(String[] args) {

        System.out.println("****** function ******");
        System.out.println(add(10));
        System.out.println(addOne.apply(10));

        System.out.println("***** Bi-function ******");
        System.out.println(addition(10,20));
        System.out.println(addition.apply(10,10));
        System.out.println(incrementByOneAndMultiply.apply(10,2));


    }

    //traditional way
    public static int add(int num) {
        return num + 1;
    }

    //Functional programming
    public static Function<Integer,Integer> addOne = num -> num + 1;

    //traditional approach
    public static int addition(int num1, int num2) {
        return num1 + num2;
    }

    public static BiFunction<Integer,Integer,Integer> addition = (num1,num2) -> num1 + num2 ;

    public static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiply =
            (numberToIncrementByOne, numberToMultiply) -> (numberToIncrementByOne + 1) * numberToMultiply;




}
