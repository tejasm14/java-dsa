package com.shaft.itextservice.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestLambdaDemo {

    final static String salutation = "Hello";

    public static void main(String[] args) {

        TestLambdaDemo lambdaObj = new TestLambdaDemo();

        MathOperation addition = (a,b) -> a + b;

        MathOperation subtraction = (int a, int b) -> a - b;

        MathOperation multiplication = (int a, int b) -> { return a * b ;};

        MathOperation division = (int a,int b) -> a / b;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num1 = ");
        int num1 = sc.nextInt();
        System.out.println("Enter num2 = ");
        int num2 = sc.nextInt();

        System.out.println("Addition = "+ lambdaObj.operate(num1,num2,addition));
        System.out.println("Subtraction = "+ lambdaObj.operate(num1,num2,subtraction));
        System.out.println("Multiplication = "+ lambdaObj.operate(num1,num2,multiplication));
        System.out.println("Division = "+ lambdaObj.operate(num1,num2,division));

        Greeting test = message -> System.out.println("Hello "+message);
        Greeting sam = (message) -> {
            System.out.println("Hello "+message);
        };

        test.Greet("Test");
        sam.Greet("Sam");

        Greeting david = message -> System.out.println(salutation + message);
        david.Greet(" David");

        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(32);
        list.add(52);
        list.add(22);
        list.add(12);

        list.forEach(nums-> System.out.println(nums));



    }

    interface MathOperation{
        int operation(int a, int b);
    }

    interface Greeting {
        void Greet(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
       return mathOperation.operation(a,b);
    }

}
