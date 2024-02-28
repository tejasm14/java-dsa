package com.shaft.itextservice.java8;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;

interface Parser {
    String parse(String s);
}

class StringParser {

    public static String convert(String s) {

        if (s.length() <= 3) {
            s = s.toUpperCase();
        } else {
            s = s.toLowerCase();
        }
        return s;
    }

}




class MyPrinter {

    public void print(String str, Parser p){
        str = p.parse(str);
        System.out.println(str);
    }
}

public class TestMethodReference {

    public static void main(String[] args) {

        DoubleFunction<Double> sqrt = Math::sqrt;

        System.out.println(sqrt);

        String str = "Tejas Mohite";
        MyPrinter myPrinter = new MyPrinter();
        /*myPrinter.print(str, new Parser() {
            @Override
            public String parse(String s) {
                return StringParser.convert(s);
            }
        });*/
        myPrinter.print(str, (StringParser::convert));
    }





}
