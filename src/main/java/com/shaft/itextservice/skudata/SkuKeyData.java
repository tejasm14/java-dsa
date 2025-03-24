package com.shaft.itextservice.skudata;

import com.google.gson.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkuKeyData {
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\air-purifier-updated-product-specification.csv";
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
                    "MANUFACTURER DETAILS",
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


            // You can now use the skObj variable as a JsonObject
            System.out.println(finalObject);

            String csvFile = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\test\\" + fileName;
            try (FileWriter writer = new FileWriter(csvFile)) {
                // Write the CSV header
                writer.append("SKU,KEY\n");

                // Iterate over each entry in the JSON object
                for (String sku : finalObject.keySet()) {
                    JsonObject skuData = finalObject.getAsJsonObject(sku);

                    // Iterate over all keys dynamically
                    for (String key : skuData.keySet()) {
                        // Write the SKU and each dynamic key in the KEY column
                        writer.append(sku)
                                .append(',')
                                .append(key) // Store the key here instead of the value
                                .append('\n');
                    }
                }

                System.out.println("CSV file created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

