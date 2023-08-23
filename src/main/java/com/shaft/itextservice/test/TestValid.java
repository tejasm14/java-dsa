package com.shaft.itextservice.test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.regex.Pattern;

public class TestValid {
    public static void main(String[] args) {
        /*String input = "123456";
        boolean isNumber = input.matches("-?\\d+(\\.\\d+)?");

        if (isNumber) {
            System.out.println("Input is a valid number.");
        } else {
            System.out.println("Input is not a valid number.");
        }*/

        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        boolean status = p.matcher("12345y").matches();
        System.out.println(status);
    }



}
