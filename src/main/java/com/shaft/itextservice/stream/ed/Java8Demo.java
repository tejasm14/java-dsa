//stream
//java 8 -> minimal code, functional programming
//java 8 -> lambda expression, Streams, Date and Time API

//1. Lambda expression
// is an anonymous function (no name, no return type, no access modifier )

//lambda expression is used to implement the functional interface

//funtional means the interface which contains only one abstract method

//Functional interface reference can hold the lambda expression. This can be called as functional programming




package com.shaft.itextservice.stream.ed;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Java8Demo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> System.out.println("This is run method"));
        t1.start();

        MathOperation sum = (a,b) -> (a + b);
        MathOperation subtract = (a,b) -> (a - b);
        System.out.println(sum.operation(10,20));
        System.out.println(subtract.operation(20,10));

        //Predicate  --> Functional interface (Boolean value function)
        // What predicate do is it hold the condition

        /*
        This is holding the condition in the variable this we can call the functional programming
        * */
        Predicate<Integer> isEven = x -> x % 2 == 0 ;
        System.out.println(isEven.test(10));
        Predicate<String> isWordStartsWithA = x -> x.toLowerCase().startsWith("a");
        Predicate<String> isWordEndsWithS = x -> x.toLowerCase().endsWith("s");
        Predicate<String> and = isWordStartsWithA.and(isWordEndsWithS);
        System.out.println(and.test("asda"));

        //Function --> Works for you . It will take something and will give you something
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, Integer> tripleIt = x -> x * 3;
        System.out.println("and them method : " + doubleIt.andThen(tripleIt).apply(10));//it will check doubleIt first and then tripleIt
        System.out.println("compose method :" + doubleIt.compose(tripleIt).apply(10));
        System.out.println(doubleIt.apply(10));
        System.out.println(tripleIt.apply(20));

        //identity function will return the same which is given to it
        Function<Integer,Integer> identity = Function.identity();
        System.out.println(identity.apply(5));


        //Consumer -->  consumer will take something but will not give you anything. it will consume it
        Consumer<Integer> consumer = x -> System.out.println("Consumer output: "+x);
        consumer.accept(10);

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        Consumer<List<Integer>> consumeList = x -> {
            for (int a :x) {
                System.out.println(a);
            }
        };
        consumeList.accept(list);

        //Supplier will not take anything and will give the output
        Supplier<String> printHello = () -> "Hello";
        System.out.println("Supplier method : "+ printHello.get());

        //Combined example
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer1 = x -> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if (predicate.test(supplier.get())) {
            consumer1.accept(function.apply(supplier.get()));
        }

        //like function, predicate and consumer
        // there is concept of the bifunction , bipredicate and biconsumer

        //BiPredicate
        BiPredicate<Integer,Integer> isSumEven = (x,y) -> (x + y) % 2 == 0;
        System.out.println("BiPredicate : "+isSumEven.test(10,20));

        BiConsumer<Integer,Integer> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        System.out.println("Use of the BiConsumer ===>>");
        biConsumer.accept(10,20);

        BiFunction<String,String,Integer> biFunction = (x,y) -> (x+y).length();
        System.out.println("BiFunction use ===>>");
        System.out.println(biFunction.apply("te","tesdfg"));

        UnaryOperator<Integer> unaryOperator = x -> x * 2;
        System.out.println("UnaryOperator use ====>");
        System.out.println(unaryOperator.apply(10));

        BinaryOperator<Integer> binaryOperator = (x,y) -> x * y;
        System.out.println("BinaryOperator use ====>");
        System.out.println(binaryOperator.apply(10,20));

        // Method reference --> use method without invoking and in place of lambda expression

        List<String> student = Arrays.asList("Ram","Shaam","Raju");
        student.forEach(x-> System.out.println(x));
        student.forEach(System.out::println);



































    }

}

class Task implements Runnable {

    // To convert this expression to lambda expression then remove the access modifier, return type, name and add the arrow function
    @Override
    public void run() {
        System.out.println("This is run method");
    }

}

interface MathOperation {

    int operation(int a, int b);
}

