package com.shaft.itextservice.java8;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamObject {
    public static void main(String[] args) {

        Stream<Object> emptyStream = Stream.empty();

        String names[] = {"Jaykant","Uday","Majanu","Raju","Shyam"};
        Stream.of(names).filter(s -> s.contains("j")).forEach(s -> System.out.println(s));


    }
}
