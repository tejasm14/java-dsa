package com.shaft.itextservice.utility;

import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ExcelToCsv {

    public static void main(String[] args) {
        //String excelFilePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csvutility-files\\all-product-specification-files\\excel-files\\allcategories\\Hair Dryers.xlsx"; // Change this to your Excel file path
        //String csvFilePath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\csvutility-files\\all-product-specification-files\\excel-files\\allcategories\\csv\\output" + System.currentTimeMillis() + ".csv";   // Change this to your desired CSV file path

        String excelFilePath = "C:\\Users\\tejas.mohite\\Downloads\\PowerBank.xls"; // Change this to your Excel file path
        String csvFilePath = "C:\\Users\\tejas.mohite\\Downloads\\files-prod-spec\\a-plus\\output" + System.currentTimeMillis() + ".csv";   // Change this to your desired CSV file path

        try {
            // Ensure the directory exists
            Path pathToFile = Paths.get(csvFilePath);
            Files.createDirectories(pathToFile.getParent());

            try (FileInputStream excelFile = new FileInputStream(excelFilePath);
                 Workbook workbook = new HSSFWorkbook(excelFile);
                 FileWriter csvFile = new FileWriter(csvFilePath);
                 CSVWriter csvWriter = new CSVWriter(csvFile)) {

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
    }
}
