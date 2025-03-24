/*
This utility is getting the data where value for following keys is missing
"GENERIC NAME",
                    "COUNTRY OF ORIGIN",
                    "COUNTRY OF MANUFACTURER",
                    "MANUFACTURERS DETAILS",
                    "IMPORTER DETAIL",
                    "PACKERS DETAILS"
 */

package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCSVFile {
    public static void main(String[] args) {
        String directoryPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\prod-spec-new-files-updated\\NewProdSpec\\prod-spec-03-12-2024";
//        String directoryPath = "C:\\Users\\Saqib.Khan\\Documents\\NewGeneratedFiler\\IssueFilePrac";
//        String directoryPath = "D:\\Saqib\\Vijay_Sales_Project\\Document\\Prod_Spec_Folder_11OCT2024\\Sk";

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
                String productID = "";
                String sku = "";
                String heading = "";
                String key = "";
                String value = "";
                if(row.length == 5){
                    productID = row[0];
                    sku = row[1];
                    heading = row[2];
                    key = row[3];
                    value = row[4];
                } else if (row.length == 4) {
                    sku = row[0];
                    heading = row[1];
                    key = row[2];
                    value = row[3];
                }


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
                if (filteredItem.size() > 0){
                    filteredJson.add(entry.getKey(), filteredItem);
                }
                else {
                    System.out.println("All the key are available in the "+fileName+" against the sku:"+entry.getKey());
                }
            }

            // Convert filteredJson to finalData (String)
            if (filteredJson.size()>0){
                String finalData = gson.toJson(filteredJson);

                // Convert finalData (String) back to JsonObject
                JsonObject finalObject = gson.fromJson(finalData, JsonObject.class);
                System.out.println(finalObject);

                // Output CSV file path
                String outputCsvFile = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\prod-spec-new-files-updated\\NewProdSpec\\prod-spec-03-12-2024\\output\\" + fileName;

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
            }else {
                System.out.println("File not generated for :"+fileName);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

