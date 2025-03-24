package com.shaft.itextservice.skudata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SkuKeyDataFolderUtility {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\Vijay-Sales-All-Product-Specification-31-08-2024\\";
        String outputFilePath = "C:\\Users\\tejas.mohite\\Downloads\\prod-spec\\consolidated_output.csv";

        List<String> checkList = Arrays.asList(
                "GENERIC NAME",
                "COUNTRY OF ORIGIN",
                "COUNTRY OF MANUFACTURER",
                "MANUFACTURERS DETAILS",
                "IMPORTER DETAIL",
                "PACKERS DETAILS"
        );

        Map<String, Map<String, String>> skuMap = new HashMap<>();

        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".csv"))
                    .forEach(filePath -> processCSVFile(filePath.toString(), skuMap, checkList));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(skuMap);

            JsonObject originalJson = gson.fromJson(json, JsonObject.class);
            JsonObject filteredJson = filterJsonObject(originalJson, checkList, gson);

            writeOutputCsv(filteredJson, outputFilePath);
            System.out.println("Consolidated CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCSVFile(String csvFilePath, Map<String, Map<String, String>> skuMap, List<String> checkList) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String sku = row[1];
                String key = row[3];
                String value = row[4];

                skuMap.putIfAbsent(sku, new HashMap<>());
                skuMap.get(sku).put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject filterJsonObject(JsonObject originalJson, List<String> checkList, Gson gson) {
        JsonObject filteredJson = new JsonObject();

        for (Map.Entry<String, JsonElement> entry : originalJson.entrySet()) {
            JsonObject item = entry.getValue().getAsJsonObject();
            JsonObject filteredItem = new JsonObject();

            for (String key : checkList) {
                filteredItem.addProperty(key, item.has(key) ? item.get(key).getAsString() : "");
            }

            filteredJson.add(entry.getKey(), filteredItem);
        }

        return filteredJson;
    }

    private static void writeOutputCsv(JsonObject finalObject, String outputFilePath) {
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            writer.append("SKU,KEY,VALUE\n");

            for (String sku : finalObject.keySet()) {
                JsonObject skuData = finalObject.getAsJsonObject(sku);

                for (String key : skuData.keySet()) {
                    String value = skuData.get(key).getAsString();
                    writer.append(sku).append(',').append(key).append(',').append(value).append('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
