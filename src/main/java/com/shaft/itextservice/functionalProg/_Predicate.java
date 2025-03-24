package com.shaft.itextservice.functionalProg;

import java.util.function.Predicate;

public class _Predicate {

    public static void main(String[] args) {

        System.out.println("Without predicate");
        System.out.println(isValidPhoneNumber("0985632145"));
        System.out.println(isValidPhoneNumber("85632145"));

        System.out.println("With predicate");
        System.out.println(isPhoneNumberValidPredicate.test("0985632145"));
        System.out.println(isPhoneNumberValidPredicate.test("85632145"));

    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("09") && phoneNumber.length() == 10;
    }
    public static Predicate<String> isPhoneNumberValidPredicate = phoneNumber -> phoneNumber.startsWith("09") && phoneNumber.length() == 10;
}
