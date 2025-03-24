package test;

import java.util.ArrayList;
import java.util.List;

public class TestStream {

    public static void main(String[] args) {
        //arraylist duplicate

        List<Integer> test = new ArrayList<>();
        test.add(10);
        test.add(10);
        test.add(20);
        test.add(50);
        test.add(10);
        test.add(10);

        test.stream().distinct().forEach(System.out::println);
    }
}
