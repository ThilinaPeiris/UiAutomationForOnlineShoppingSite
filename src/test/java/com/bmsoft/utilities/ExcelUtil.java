package com.bmsoft.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    public static final String testDataExcelFileName = "testdata.xlsx";
    public static final String currentDir = System.getProperty("user.dir");
    public static String testDataExcelPath = null;

    private static XSSFWorkbook excelWBook; //Excel WorkBook
    private static XSSFSheet excelWSheet; //Excel Sheet
    private static XSSFCell cell; //Excel cell
    private static XSSFRow row; //Excel row
    public static int rowNumber; //Row Number
    public static int columnNumber; //Column Number

    public static void setExcelFileSheet(String sheetName) {

        if (Platform.getCurrent().toString().contains("WIN")) {
            testDataExcelPath = currentDir + "\\src\\test\\resources\\testData\\";
        }else if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "/src/test/resources/testData/";
        } else {
            testDataExcelPath = currentDir + "/src/test/resources/testData/"; //for linux
        }

        FileInputStream ExcelFile = null;
        try {
            ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setCellData(String value, int RowNum, int ColNum) {
        row = excelWSheet.getRow(RowNum);
        cell = row.getCell(ColNum);
        if (cell == null) {
            cell = row.createCell(ColNum);
        }
        cell.setCellValue(value);
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCellData(int RowNum, int ColNum) {
        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public static XSSFRow getRowData(int RowNum) {
        row = excelWSheet.getRow(RowNum);
        return row;
    }
}