package com.shaft.itextservice.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

///utility.
public class MdmExcelToCsv {

    public static void main(String[] args) {
        /*String excelFilePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Prod-Spec-Files-23-08-2024\\Format Change\\Specs in XLS\\TABLETS.xlsx"; // Change this to your Excel file path
        String csvFilePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Prod-Spec-Files-23-08-2024\\Format Change\\Specs in XLS\\test\\" + System.currentTimeMillis() + ".csv";   // Change this to your desired CSV file path*/

        String excelFilePath = "C:\\Users\\tejas.mohite\\Downloads\\ProductSpecifications-OTG_Flashdrive_Data_03-Aug-2024.xlsx";  // Change this to your Excel file path
        String csvFilePath = "C:\\Users\\tejas.mohite\\Downloads\\csv\\" + System.currentTimeMillis() + ".csv";   // Change this to your desired CSV file path

        try (FileInputStream excelFile = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(excelFile);
             OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(Paths.get(csvFilePath)), StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            //Sheet sheet = workbook.getSheet("Tech Specs");
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM-yyyy"); // Format for "Oct-2022"
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy"); // Format for "22-Oct-2021"


            while (rowIterator.hasNext()) {
                //Row row = rowIterator.next();
                //Iterator<Cell> cellIterator = row.cellIterator();
                DataFormatter dataFormatter = new DataFormatter();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

                List<String> csvDataList = new ArrayList<>();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    int numberOfCells = row.getLastCellNum();
                    String[] csvData = new String[numberOfCells];

                    for (int cellIndex = 0; cellIndex < numberOfCells; cellIndex++) {
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                if (isDateFormattedString(cellValue)) {
                                    // Parse string formatted date
                                    csvData[cellIndex] = parseDateFromString(cellValue, dateFormat2);
                                } else {
                                    csvData[cellIndex] = cellValue;
                                }
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // Format numeric date cells
                                    csvData[cellIndex] = dateFormat1.format(cell.getDateCellValue());
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

                // Remove trailing empty cells
                /*while (!csvDataList.isEmpty() && csvDataList.get(csvDataList.size() - 1).isEmpty()) {
                    csvDataList.remove(csvDataList.size() - 1);
                }*/

                // Write the row to CSV if it's not empty
                /*if (!csvDataList.isEmpty()) {
                    *//*csvWriter.writeNext(csvDataList.stream()
                            .map(data -> "\"" + data.replace("\"", "\"\"") + "\"") // Escaping quotes
                            .toArray(String[]::new));*//*
                    csvWriter.writeNext(csvDataList.toArray(new String[0]));
                }*/
            }

            csvWriter.close();
            System.out.println("Excel to CSV file created successfully!");

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
            e.printStackTrace();
        }
    }

    /*private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] column = csvReader.readNext();
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                JsonObject rowObj = new JsonObject();
                for (int i = 0; i < row.length; i++) {
                    rowObj.addProperty(column[i], row[i].trim());
                }
                csvJsonArr.add(rowObj);
            }
        }
        return csvJsonArr;
    }*/

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
