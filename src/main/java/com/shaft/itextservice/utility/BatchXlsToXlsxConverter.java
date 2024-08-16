package com.shaft.itextservice.utility;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BatchXlsToXlsxConverter {
    public static void main(String[] args) {
        String sourceDirPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay_Sales_Product_Specification_03-08-2024\\SubCategoryWiseActiveProductSpecifications_Data";
        String targetDirPath = "D:\\TEJAS MOHITE\\Projects-06-06-2022\\vijay-sales-project-files-05-04-2024\\Vijay_Sales_Product_Specification_03-08-2024\\converted-xls-to-xlsx";

        File sourceDir = new File(sourceDirPath);
        File targetDir = new File(targetDirPath);

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File[] xlsFiles = sourceDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".xls"));

        if (xlsFiles != null) {
            for (File xlsFile : xlsFiles) {
                convertXlsToXlsx(xlsFile, new File(targetDir, xlsFile.getName().replace(".xls", ".xlsx")));
            }
        }
    }

    private static void convertXlsToXlsx(File xlsFile, File xlsxFile) {
        try (FileInputStream fis = new FileInputStream(xlsFile);
             HSSFWorkbook xlsWorkbook = new HSSFWorkbook(fis);
             XSSFWorkbook xlsxWorkbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(xlsxFile)) {

            for (int i = 0; i < xlsWorkbook.getNumberOfSheets(); i++) {
                xlsxWorkbook.createSheet(xlsWorkbook.getSheetName(i));
                xlsxWorkbook.setSheetOrder(xlsxWorkbook.getSheetName(i), i);

                for (int rowIndex = 0; rowIndex <= xlsWorkbook.getSheetAt(i).getLastRowNum(); rowIndex++) {
                    if (xlsWorkbook.getSheetAt(i).getRow(rowIndex) != null) {
                        xlsxWorkbook.getSheetAt(i).createRow(rowIndex);

                        for (int cellIndex = 0; cellIndex < xlsWorkbook.getSheetAt(i).getRow(rowIndex).getLastCellNum(); cellIndex++) {
                            if (xlsWorkbook.getSheetAt(i).getRow(rowIndex).getCell(cellIndex) != null) {
                                xlsxWorkbook.getSheetAt(i).getRow(rowIndex).createCell(cellIndex)
                                        .setCellValue(xlsWorkbook.getSheetAt(i).getRow(rowIndex).getCell(cellIndex).toString());
                            }
                        }
                    }
                }
            }

            xlsxWorkbook.write(fos);
            System.out.println("Converted: " + xlsFile.getName() + " to " + xlsxFile.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
