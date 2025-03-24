/*
package com.shaft.itextservice.utility;

import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.FileInputStream;
import java.io.IOException;

public class GetExcelEncoding {

    public static void main(String[] args) {
        String filePath = "your_file.csv"; // Replace with your file path

        try (FileInputStream fis = new FileInputStream(filePath)) {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(fis);
            CharsetMatch match = detector.detect();

            if (match != null) {
                System.out.println("Detected encoding: " + match.getName());
            } else {
                System.out.println("Encoding could not be detected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
