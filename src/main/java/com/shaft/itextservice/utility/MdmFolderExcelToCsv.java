package com.shaft.itextservice.utility;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MdmFolderExcelToCsv {

    public static void main(String[] args) {
        String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csvutility-files\\all-product-specification-files\\allcategories";
        String csvFolderPath = folderPath + "\\csv";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String excelFilePath = file.getAbsolutePath();
                    String csvFileName = file.getName().replace(".xlsx", ".csv");
                    String csvFilePath = csvFolderPath + "\\" + csvFileName;
                    processExcelFile(excelFilePath, csvFilePath);
                }
            }
        }
    }

    private static void processExcelFile(String excelFilePath, String csvFilePath) {
        try (FileInputStream excelFile = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(excelFile);
             FileWriter csvFile = new FileWriter(csvFilePath);
             CSVWriter csvWriter = new CSVWriter(csvFile)) {

            Sheet sheet = workbook.getSheetAt(1); // You might want to adjust this index
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                String[] csvData = new String[row.getPhysicalNumberOfCells()];
                int cellIndex = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            csvData[cellIndex++] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                csvData[cellIndex++] = cell.getDateCellValue().toString();
                            } else {
                                csvData[cellIndex++] = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            csvData[cellIndex++] = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            csvData[cellIndex++] = cell.getCellFormula();
                            break;
                        default:
                            csvData[cellIndex++] = "";
                    }
                }
                csvWriter.writeNext(csvData);
            }

            csvWriter.close();
            System.out.println("Excel to CSV file created successfully for file: " + csvFilePath);

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
            String output2CsvPath = csvFilePath.replace(".csv", "_output2.csv");
            CSVWriter writer = new CSVWriter(new FileWriter(output2CsvPath));
            writer.writeAll(dataList);
            writer.close();
            System.out.println("CSV file generated: " + output2CsvPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        String[] column = csvReader.readNext();
        String[] row;
        while ((row = csvReader.readNext()) != null) {
            JsonObject rowObj = new JsonObject();
            for (int i = 0; i < row.length; i++) {
                rowObj.addProperty(column[i], row[i]);
            }
            csvJsonArr.add(rowObj);
        }
        csvReader.close();
        return csvJsonArr;
    }
}
