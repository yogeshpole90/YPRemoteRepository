package com.ebid.lcs.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class ReadExcel {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Yogesh.Pole\\Downloads\\Target & Incentive FSD_V1.0.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(fis);

        for (int s = 0; s < wb.getNumberOfSheets(); s++) {
            Sheet sheet = wb.getSheetAt(s);
            System.out.println("\n========== SHEET: " + sheet.getSheetName() + " ==========\n");
            for (Row row : sheet) {
                StringBuilder sb = new StringBuilder();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING: sb.append(cell.getStringCellValue()); break;
                        case NUMERIC: sb.append(cell.getNumericCellValue()); break;
                        case BOOLEAN: sb.append(cell.getBooleanCellValue()); break;
                        default: sb.append(""); break;
                    }
                    sb.append("\t|\t");
                }
                System.out.println(sb.toString());
            }
        }
        wb.close();
        fis.close();
    }
}
