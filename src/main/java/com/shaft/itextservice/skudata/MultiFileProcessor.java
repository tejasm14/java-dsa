package com.shaft.itextservice.skudata;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.google.gson.*;
import com.opencsv.*;

public class MultiFileProcessor {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\Vijay-Sales-All-Product-Specification-31-08-2024";
        String outputFile = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\combined_output.csv";

        // Initialize a map to collect data across all files
        Map<String, Map<String, String>> combinedSkuMap = new HashMap<>();

        try {
            Files.list(Paths.get(folderPath))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(path -> processFile(path.toString(), combinedSkuMap));

            // Write the combined data to a single CSV file
            writeOutputFile(combinedSkuMap, outputFile);
            System.out.println("All files processed successfully into a single output file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processFile(String csvFilePath, Map<String, Map<String, String>> combinedSkuMap) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String sku = row[1];
                String key = row[3];
                String value = row[4];

                combinedSkuMap.putIfAbsent(sku, new HashMap<>());
                combinedSkuMap.get(sku).put(key, value);
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public static void writeOutputFile(Map<String, Map<String, String>> combinedSkuMap, String outputFile) {
        List<String> checkList = Arrays.asList(
                "MANUFACTURER DETAILS",
                "GENERIC NAME",
                "COUNTRY OF ORIGIN",
                "COUNTRY OF MANUFACTURER",
                "MANUFACTURERS DETAILS",
                "IMPORTER DETAIL",
                "PACKERS DETAILS"
        );

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.append("SKU,KEY\n");

            for (Map.Entry<String, Map<String, String>> entry : combinedSkuMap.entrySet()) {
                String sku = entry.getKey();
                Map<String, String> skuData = entry.getValue();

                for (String key : checkList) {
                    writer.append(sku)
                            .append(',')
                            .append(key)
                            .append('\n');
                }
            }

            System.out.println("Combined CSV file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


