package com.shaft.itextservice.testcsvpack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MDMCSVFileCreation2 {

    public static void main(String[] args) {
        //iphone - Tech Specs
        try {
            //String filePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Product-Specification-files-06-08-2024\\output-csv-files\\1723811969393.csv";
            String filePath = "C:\\Users\\tejas.mohite\\Downloads\\iPhone Specs Checked File.csv";
//            //String filePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024" +
//                    "\\csvutility-files\\all-product-specification-files\\excel-files\\allcategories" +
//                    "\\csv\\output1718709858630.csv";
            JsonArray csvJsonArr = readCsvtoJsonArray(filePath);
            List<String[]> dataList = new ArrayList<>();
            dataList.add(new String[] { "ProductID", "SKU", "heading", "key", "value"});
            csvJsonArr.forEach(eachObj -> {
                JsonObject js = (JsonObject) eachObj;
                String productId = js.get("ProductID").getAsString();
                String sku = js.get("SKU").getAsString();
                js.entrySet().forEach(entry -> {
                    if (entry.getKey().contains("_")) {
                        String heading = entry.getKey().split("_")[0];
                        String key = entry.getKey().split("_")[1];
                        dataList.add(new String[] { productId, sku, heading, key, entry.getValue().getAsString()});
                    }
                });
            });
            CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\tejas.mohite\\Downloads\\files-prod-spec\\output2.csv"));
            writer.writeAll(dataList);
            writer.close();
            System.out.println("file generated..");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        String[] column = csvReader.readNext();
        String[] row;
        while ((row = csvReader.readNext()) != null){
            JsonObject rowObj = new JsonObject();
            for (int i =0 ; i < row.length ; i++){
                rowObj.addProperty(column[i], row[i]);
            }
            csvJsonArr.add(rowObj);
        }
        return csvJsonArr;
    }


}
