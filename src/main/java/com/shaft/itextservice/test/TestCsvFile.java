package com.shaft.itextservice.test;

import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class TestCsvFile {

    public static void main(String[] args) {
        generateCSV("sample_data.csv", 200);
        System.out.println("Sample CSV data generated successfully.");
    }

    public static void generateCSV(String filename, int numRows) {
        try (Writer writer = new FileWriter(filename)) {
            writer.write("Name,Date of Birth\n");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Faker faker = new Faker(new Locale("en-US"));

            for (int i = 0; i < numRows; i++) {
                String name = faker.name().fullName();
                String dateOfBirth = dateFormatter.format(faker.date().birthday(18, 90).toInstant());
                writer.write(name + "," + dateOfBirth + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
