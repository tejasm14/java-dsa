package com.shaft.itextservice.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestConsumer {

    public static void main(String[] args) {
        List<Integer> arrayList = Arrays.asList(12,13,45,56,67);

        Consumer<Integer> con = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        System.out.println("Using consumer interface");
        arrayList.forEach(con);

        Consumer<Integer> cons = integer -> System.out.println(integer);

        System.out.println("Using consumer and lambda function");
        arrayList.forEach(cons);


        System.out.println("Using lambda functions");
        arrayList.forEach(s -> System.out.println(s));

    }

}
