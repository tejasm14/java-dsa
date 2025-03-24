package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        for (int i = 0; i < list.size(); i++) {
            list.remove(1);
        }

//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            int test = iterator.next();
//            System.out.println(test);
//            list.remove(1);
//        }

        System.out.println(list);

    }

}
