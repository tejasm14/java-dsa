package com.shaft.itextservice.mysql;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvToDatabase {

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\tejas.mohite\\Downloads\\mix-prods-spec - mixed-prod-spec-28-oct.csv";
        String jdbcURL = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "tejas@root";
        String insertQuery = "INSERT INTO `mixed-prod-spec-28-oct` (productid, sku, heading, `key`, value) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                // Skip header if needed
                csvReader.readNext();

                String[] values;
                while ((values = csvReader.readNext()) != null) {
                    // Set values based on your column types
                    preparedStatement.setString(1, values[0]);
                    preparedStatement.setString(2, values[1]);
                    preparedStatement.setString(3, values[2]);
                    preparedStatement.setString(4, values[3]);
                    preparedStatement.setString(5, values[4]);
                    preparedStatement.setString(6, getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
                    preparedStatement.setString(7, "shaftUser");
                    preparedStatement.setString(8, getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
                    preparedStatement.setString(9, "shaftUser");
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                System.out.println("Data inserted successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat sdfo = new SimpleDateFormat(format);
        Date date = new Date();
        return sdfo.format(date);
    }
}
