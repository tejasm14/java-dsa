package com.shaft.itextservice.testitext;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFormulaExcel {

    public static void main(String[] args) {

        File file = new File("D:\\TEJAS MOHITE\\Projects-06-06-2022\\excel-read\\STOCKHISTORY-PIDILITIND1.xlsx");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook baeuldungWorkBook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : baeuldungWorkBook) {
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();
                for (int index = firstRow + 1; index <= lastRow; index++) {
                    Row row = sheet.getRow(index);
                    for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        CellType cellType = cell.getCellType().equals(CellType.FORMULA)
                                ? cell.getCachedFormulaResultType() : cell.getCellType();
                        if (cellType.equals(CellType.STRING)) {
                            System.out.print(cell.getStringCellValue() + " | ");
                        }
                        if (cellType.equals(CellType.NUMERIC)) {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + " | ");
                            } else {
                                System.out.print(cell.getNumericCellValue() + " | ");
                            }
                        }
                        if (cellType.equals(CellType.BOOLEAN)) {
                            System.out.print(cell.getBooleanCellValue() + " | ");
                        }
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
