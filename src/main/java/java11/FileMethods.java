/*
package java11;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMethods {
	
	public static void main(String[] args) {
		Path path = Paths.get("D:\\TEJAS MOHITE\\Projects-06-06-2022\\Java-practice-git\\java-dsa\\resources\\Test.txt");
		try {
			System.out.println("Reading file ");
			System.out.println(Files.readString(path));
			//This method reads the file as string 
			//we are reading a file by using the readString() method that returns data in string form
			
			path = Files.writeString(path, "This is another string");
			System.out.println(Files.readString(path));
			//write method overrides the data removes the data
			
			path = Files.writeString(path, "Welcome!!", Charset.forName("UTF-8"));
			System.out.println(Files.readString(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/
