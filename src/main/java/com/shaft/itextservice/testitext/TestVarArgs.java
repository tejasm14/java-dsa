package com.shaft.itextservice.testitext;

import java.util.ArrayList;
import java.util.List;

public class TestVarArgs {

    @SafeVarargs
    private final void display(List<String>... displayList) {

        for (List<String> list :displayList) {
            System.out.println(list);
        }
    }


    public static void main(String[] args) {

        TestVarArgs testObj = new TestVarArgs();

        List<String> disList = new ArrayList<>();
        disList.add("Test1");
        disList.add("Test2");
        disList.add("Test3");

        testObj.display(disList);
    }

}
