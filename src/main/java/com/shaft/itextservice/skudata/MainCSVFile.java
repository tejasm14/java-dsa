package com.shaft.itextservice.skudata;

import com.google.gson.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCSVFile {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\Vijay-Sales-All-Product-Specification-31-08-2024";

        try {
            // Iterate over each CSV file in the directory
            Files.list(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(path -> processCsvFile(path.toString()));

            System.out.println("All files processed successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCsvFile(String csvFilePath) {
        String fileName = Paths.get(csvFilePath).getFileName().toString();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = csvReader.readAll();
            Map<String, Map<String, String>> skuMap = new HashMap<>();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String productID = row[0];
                String sku = row[1];
                String heading = row[2];
                String key = row[3];
                String value = row[4];

                skuMap.putIfAbsent(sku, new HashMap<>());
                skuMap.get(sku).put(key, value);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(skuMap);

            List<String> checkList = Arrays.asList(
                    "GENERIC NAME",
                    "COUNTRY OF ORIGIN",
                    "COUNTRY OF MANUFACTURER",
                    "MANUFACTURERS DETAILS",
                    "IMPORTER DETAIL",
                    "PACKERS DETAILS"
            );
            JsonObject originalJson = gson.fromJson(json, JsonObject.class);
            JsonObject filteredJson = new JsonObject();

            for (Map.Entry<String, JsonElement> entry : originalJson.entrySet()) {
                JsonObject item = entry.getValue().getAsJsonObject();
                JsonObject filteredItem = new JsonObject();

                // Filter the fields based on checkList
                for (String key : checkList) {
                    if (!item.has(key)) {
                        filteredItem.addProperty(key, "");
                    }
                }

                filteredJson.add(entry.getKey(), filteredItem);
            }

            // Convert filteredJson to finalData (String)
            String finalData = gson.toJson(filteredJson);

            // Convert finalData (String) back to JsonObject
            JsonObject finalObject = gson.fromJson(finalData, JsonObject.class);
            System.out.println(finalObject);

            // Output CSV file path
            String outputCsvFile = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\new-all-files\\" + fileName;

            try (FileWriter csvWriter = new FileWriter(outputCsvFile)) {
                // Write the header row
                csvWriter.append("SKU,KEY\n");

                // Write data rows
                for (Map.Entry<String, JsonElement> entry : finalObject.entrySet()) {
                    String sku = entry.getKey();
                    JsonObject skuDetails = entry.getValue().getAsJsonObject();

                    // Collect all keys for the SKU as a comma-separated string
                    StringBuilder keys = new StringBuilder();
                    for (Map.Entry<String, JsonElement> detail : skuDetails.entrySet()) {
                        if (keys.length() > 0) {
                            keys.append(", ");
                        }
                        keys.append(detail.getKey());
                    }

                    // Write data row
                    csvWriter.append(sku).append(",").append(keys.toString()).append("\n");
                }

                System.out.println("CSV file created successfully for " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
