package com.shaft.itextservice.functionalProg;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {

        //Traditional Way
        Customer maria = new Customer("Maria","7896456321");

        System.out.println("***** Traditional Way  *****");
        greetCustomer(maria);
        greetCustomerV2(maria,false);

        System.out.println("***** Functional Programming  Way  *****");
        greetCustomerConsumer.accept(maria);
        greetCustomerBiConsumer.accept(maria,false);


    }

    static Consumer<Customer> greetCustomerConsumer = customer ->  System.out.println("Hello "+customer.customerName+" thanks for registering phone number "+customer.customerPhoneNumber);
    static BiConsumer<Customer,Boolean> greetCustomerBiConsumer = ((customer, showNumber) -> System.out.println("Hello "+customer.customerName+" thanks for registering phone number "+( showNumber ? customer.customerPhoneNumber : "**********")));

    static void greetCustomer(Customer customer) {
        System.out.println("Hello "+customer.customerName+" thanks for registering phone number "+customer.customerPhoneNumber);
    }

    static void greetCustomerV2(Customer customer, boolean showNumber) {
        System.out.println("Hello "+customer.customerName+" thanks for registering phone number "+( showNumber ? customer.customerPhoneNumber : "**********"));
    }

    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }


}



