package com.shaft.itextservice.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(2,6,9,5,1,8,3);
        System.out.println("first");

        for (Object o : list.toArray()) {
            System.out.print(o+"\t");
        }
        System.out.println();


        List<Integer> abc = list.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println(abc);

        list.stream().filter(n->n%2==0).map(n->n*2).forEach(s-> System.out.println(s));

        list.stream().filter(i -> i % 2 == 0).forEach(s-> System.out.println(s));


        System.out.println("Map use");
        list.stream().map(n -> n * 2).forEach(s -> System.out.println(s));




        Map<Integer,String> map = new HashMap<>();
        map.put(1,"Tom");
        map.put(2,"Jerry");
        map.put(3,"Edd");
        map.put(4,"Donald");
        map.put(5,"Ballu");

        System.out.println("Map");

        Stream.of(map)
                .filter(integerStringMap -> integerStringMap.get(4)
                        .contains("a"))
                            .forEach(s-> System.out.println(s));












    }
}
