package com.shaft.itextservice.stream.ed;

import java.util.function.Function;
import java.util.function.Predicate;

public class NewFeatures {

    public static void main(String[] args) {

        Function<Integer,Integer> doubleFunction = x -> x * 2;
        System.out.println(doubleFunction.apply(10));
        Function<Integer,Integer> tripleFunction = x -> x * 3;
        System.out.println(tripleFunction.apply(20));
        System.out.println(doubleFunction.andThen(tripleFunction).apply(10));










    }
}
