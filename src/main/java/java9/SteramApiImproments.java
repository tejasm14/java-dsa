/*
package java9;

import java.util.stream.Stream;

public class SteramApiImproments {

	public static void main(String[] args) {

		System.out.println("abcdef");
		//This method will take the element while string is not empty after that it not takes
		System.out.println("Output for take while");
		Stream.of("a", "b", "c", "d", "", "e", "f").takeWhile(s -> !s.isEmpty()).forEach(System.out::print);
		System.out.println();	
		System.out.println("======================================================================");
		System.out.println("Output for drop while");
		Stream.of("a", "b", "c", "d", "", "e", "f").dropWhile(s -> !s.isEmpty()).forEach(System.out::print);
		
		//both methods are vice versa
	}
}
*/
