package com.shaft.itextservice.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewMDMToCsvToCsv {

    public static void main(String[] args) {


        try {

            String csvFilePath = "C:\\Users\\tejas.mohite\\Downloads\\ProductSpecifications-OTG_Flashdrive_Data_03-Aug-2024.csv";
            JsonArray csvJsonArr = readCsvtoJsonArray(csvFilePath);
            List<String[]> dataList = new ArrayList<>();
            dataList.add(new String[]{"ProductID", "SKU", "heading", "key", "value"});
            csvJsonArr.forEach(eachObj -> {
                JsonObject js = (JsonObject) eachObj;
                String productId = js.get("ProductID").getAsString();
                String sku = js.get("SKU").getAsString();
                js.entrySet().forEach(entry -> {
                    if (entry.getKey().contains("_")) {
                        String heading = entry.getKey().split("_")[0];
                        String key = entry.getKey().split("_")[1];
                        dataList.add(new String[]{productId, sku, heading, key, entry.getValue().getAsString()});
                    }
                });
            });

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get("C:\\Users\\tejas.mohite\\Downloads\\csv\\output2.csv")), StandardCharsets.UTF_8);
            CSVWriter csvWriter1 = new CSVWriter(outputStreamWriter);
            csvWriter1.writeAll(dataList);
            csvWriter1.close();
            System.out.println("CSV file generated..");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] column = csvReader.readNext();
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                JsonObject rowObj = new JsonObject();
                for (int i = 0; i < column.length; i++) {
                    if (i < row.length) {
                        String cleanedValue = row[i].trim().replaceAll("^\"|\"$", ""); // Remove leading and trailing quotes
                        // Convert numeric strings like "27864.0" to "27864"
                        if (cleanedValue.matches("\\d+\\.0")) {
                            cleanedValue = cleanedValue.substring(0, cleanedValue.length() - 2);
                        }
                        rowObj.addProperty(column[i], cleanedValue);
                    } else {
                        rowObj.addProperty(column[i], ""); // Handle missing columns
                    }
                }
                csvJsonArr.add(rowObj);
            }
        }
        return csvJsonArr;
    }

    private static boolean isDateFormattedString(String cellValue) {
        // Check if the string matches a date format pattern
        return cellValue.matches("\\d{2}-[a-zA-Z]{3}-\\d{4}"); // e.g., "22-Oct-2021"
    }

    private static String parseDateFromString(String dateStr, SimpleDateFormat dateFormat) {
        try {
            Date date = dateFormat.parse(dateStr);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr; // Fallback to original string if parsing fails
        }
    }


}
