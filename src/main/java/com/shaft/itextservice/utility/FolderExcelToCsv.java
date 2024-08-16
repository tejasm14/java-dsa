package com.shaft.itextservice.utility;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class FolderExcelToCsv {

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
        try (FileInputStream excelFile = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(excelFile);
             FileWriter csvFile = new FileWriter(csvFilePath);
             CSVWriter csvWriter = new CSVWriter(csvFile)) {

            Sheet sheet = workbook.getSheetAt(2); // Adjust the sheet index as needed
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

            csvWriter.flush();
            System.out.println("Excel to CSV file created successfully for file: " + csvFilePath);

        } catch (IOException e) {
            System.err.println("Error processing Excel file: " + excelFilePath);
            throw e;
        }
    }
}
