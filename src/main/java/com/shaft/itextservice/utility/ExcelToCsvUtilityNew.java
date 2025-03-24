/*
* This is new utility for converting vijay sales xlsx files to the MDM compatible files
* DATE : 09-09-2024
*
*
* */

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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelToCsvUtilityNew {

    public static void main(String[] args) {
        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csvutility-files\\all-product-specification-files\\excel-files\\allcategories";
        //String csvFolderPath = folderPath + "\\csv";

        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Product-Specification-files-06-08-2024\\New folder - amaan";
        //String csvFolderPath = folderPath + "\\csv";

        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay-Sales-Prod-Spec-Files-23-08-2024\\Format Change\\Specs in XLS";
        //String csvFolderPath = folderPath + "\\csv2";

        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\vijay-sales-prod-spec-28-08-2024\\28 Aug\\Duplicate Tech Specs  (Correct Files)";
        //String csvFolderPath = folderPath + "\\csv";

        //String folderPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\vijay-sales-prod-spec-28-08-2024\\28 Aug\\Tech Specs Remaining (28 Aug)";
        //String csvFolderPath = folderPath + "\\csv";

        String folderPath = "C:\\Users\\tejas.mohite\\Downloads\\Product_Catlog_Shared_SKUs_17Oct2024\\xlsx";

        String csvFolderPath = folderPath + "\\csv1";

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
                 OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(pathToFile), StandardCharsets.UTF_8);
                 CSVWriter csvWriter = new CSVWriter(writer)) {

                //Sheet sheet = workbook.getSheet("Tech Specs");
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter dataFormatter = new DataFormatter();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Row> rowIterator = sheet.iterator();

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM-yyyy"); // Format for "Oct-2022"
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy"); // Format for "22-Oct-2021"

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
                                    csvData[cellIndex] = dateFormat2.format(cell.getDateCellValue());
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

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(output2CsvPath)), StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(outputStreamWriter);
        writer.writeAll(dataList);
        writer.close();
        System.out.println("CSV file generated: " + output2CsvPath);
    }

    private static JsonArray readCsvtoJsonArray(String filePath) throws IOException {
        JsonArray csvJsonArr = new JsonArray();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
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
