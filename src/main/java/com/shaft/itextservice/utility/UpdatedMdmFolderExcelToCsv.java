/*
* This is utility for converting the excel to csv files by taking the sheet from the excel files
* */


package com.shaft.itextservice.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdatedMdmFolderExcelToCsv {

    public static void main(String[] args) {
        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csvutility-files\\all-product-specification-files\\excel-files\\allcategories";
        //String csvFolderPath = folderPath + "\\csv";

        String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Product-Specification-files-06-08-2024\\New folder - amaan";
        String csvFolderPath = folderPath + "\\csv";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String excelFilePath = file.getAbsolutePath();
                    String csvFileName = file.getName().replace(".xlsx", ".csv");
                    String csvFilePath = csvFolderPath + "\\" + csvFileName;
                    try {
                        processExcelFile(excelFilePath, csvFilePath);
                    } catch (Exception e) {
                        System.err.println("Error processing file: " + excelFilePath);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void processExcelFile(String excelFilePath, String csvFilePath) throws IOException {
        try {
            // Ensure the directory exists
            Path pathToFile = Paths.get(csvFilePath);
            Files.createDirectories(pathToFile.getParent());

            try (FileInputStream excelFile = new FileInputStream(excelFilePath);
                 Workbook workbook = new XSSFWorkbook(excelFile);
                 FileWriter csvFile = new FileWriter(csvFilePath);
                 CSVWriter csvWriter = new CSVWriter(csvFile)) {

                //Sheet sheet = workbook.getSheet("Tech Specs");
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter dataFormatter = new DataFormatter();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    int numberOfCells = row.getLastCellNum();
                    String[] csvData = new String[numberOfCells];

                    for (int cellIndex = 0; cellIndex < numberOfCells; cellIndex++) {
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case STRING:
                                csvData[cellIndex] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    csvData[cellIndex] = cell.getDateCellValue().toString();
                                } else {
                                    csvData[cellIndex] = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                csvData[cellIndex] = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                csvData[cellIndex] = dataFormatter.formatCellValue(cell, formulaEvaluator);
                                break;
                            case BLANK:
                            default:
                                csvData[cellIndex] = "";
                                break;
                        }
                    }
                    csvWriter.writeNext(csvData);
                }
                System.out.println("Excel to CSV file created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] column = csvReader.readNext();
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                JsonObject rowObj = new JsonObject();
                for (int i = 0; i < row.length; i++) {
                    rowObj.addProperty(column[i], row[i]);
                }
                csvJsonArr.add(rowObj);
            }
        }
        return csvJsonArr;
    }

}
