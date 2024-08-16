package com.shaft.itextservice.test;

import java.util.ArrayList;

public class TestFile {

    public static void main(String[] args) {
        // Sample ArrayList containing pin codes
        ArrayList<String> pinCodes = new ArrayList<>();
        pinCodes.add("12345");
        pinCodes.add("123456");
        pinCodes.add("233333");
        pinCodes.add("678943");

        // Convert ArrayList to string
        StringBuilder sb = new StringBuilder("WHERE 'pincode' IN (");
        for (int i = 0; i < pinCodes.size(); i++) {
            sb.append("'").append(pinCodes.get(i)).append("'");
            if (i < pinCodes.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");

        String result = sb.toString();
        System.out.println(result);
    }
}
