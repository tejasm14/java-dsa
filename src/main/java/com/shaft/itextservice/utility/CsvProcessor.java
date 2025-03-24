package com.shaft.itextservice.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CsvProcessor {

    public static void main(String[] args) {
        String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\New-prod-spec-files-29-nov-2024"; // Path to your folder
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String csvFilePath = file.getAbsolutePath();
                    String outputCsvFilePath = csvFilePath.replace(".csv", "_output2.csv");
                    try {
                        processCsvFile(csvFilePath, outputCsvFilePath);
                    } catch (Exception e) {
                        System.err.println("Error processing file: " + csvFilePath);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void processCsvFile(String csvFilePath, String outputCsvFilePath) throws IOException {
        // Read the original CSV into a JSON array
        JsonArray csvJsonArr = readCsvToJsonArray(csvFilePath);

        // Prepare the data for the new CSV format
        List<String[]> dataList = new ArrayList<>();
        dataList.add(new String[]{"ProductID", "SKU", "heading", "key", "value"}); // Header row

        csvJsonArr.forEach(eachObj -> {
            JsonObject js = (JsonObject) eachObj;
            String productId = js.get("ProductID").getAsString();
            String sku = js.get("SKU").getAsString();

            // Process each key-value pair in the JSON object
            js.entrySet().forEach(entry -> {
                if (entry.getKey().contains("_")) { // Match keys with an underscore
                    String[] parts = entry.getKey().split("_");
                    String heading = parts[0];
                    String key = parts[1];
                    dataList.add(new String[]{productId, sku, heading, key, entry.getValue().getAsString()});
                }
            });
        });

        // Write the transformed data into a new CSV file
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(outputCsvFilePath)), StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(outputStreamWriter)) {
            writer.writeAll(dataList);
            System.out.println("CSV file generated: " + outputCsvFilePath);
        }
    }

    private static JsonArray readCsvToJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            String[] column = csvReader.readNext(); // Read the header row
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                JsonObject rowObj = new JsonObject();
                for (int i = 0; i < column.length; i++) {
                    if (i < row.length) {
                        String cleanedValue = row[i].trim().replaceAll("^\"|\"$", ""); // Remove leading/trailing quotes
                        if (cleanedValue.matches("\\d+\\.0")) { // Convert numeric strings like "27864.0" to "27864"
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
}
