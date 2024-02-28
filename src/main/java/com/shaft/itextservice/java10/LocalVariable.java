/*
package com.shaft.itextservice.java10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalVariable {
	
	
	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(15,56,78,9,36,15); 
		List<Integer> unmodifiableList = List.copyOf(list);
		//unmodifiableList.add(15); //this will result in the run time exception
		System.out.println(unmodifiableList);	
		
		//using stream class
		//Added toUnmodifiableList in java 10
		Stream.of(15,56,89,14,56).collect(Collectors.toUnmodifiableList()).forEach(System.out::println);
		

	}
	
	
	
}
*/
