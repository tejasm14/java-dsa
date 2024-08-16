package com.shaft.itextservice.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class DropDownExcel {

    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Creating a list of options for the dropdown
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};

        // Creating a CellRangeAddressList for the data validation
        CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0); // Adjust as per your requirements
        DataValidationHelper validationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(options);
        DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);

        // Allowing multiple selections
        dataValidation.setSuppressDropDownArrow(true);
        dataValidation.setShowErrorBox(true);
        dataValidation.setShowPromptBox(true);
        dataValidation.setEmptyCellAllowed(true);

        dataValidation.createPromptBox("Multi-select Dropdown", "Please select multiple options separated by commas.");
        dataValidation.createErrorBox("Error", "Please enter multiple options separated by commas.");

        sheet.addValidationData(dataValidation);

        // Writing to Excel file
        try (FileOutputStream fileOut = new FileOutputStream("D:\\TEJAS MOHITE\\Projects-06-06-2022\\SHAFT-excel-service-git-repo\\MultiSelectDropdownExample.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File created.");
    }

}
