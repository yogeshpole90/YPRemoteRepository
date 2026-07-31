package com.agat.los.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final String FILE_PATH = "src/test/resources/testdata/TestData.xlsx";
    private static Workbook workbook;

    static {
        try {
            workbook = new XSSFWorkbook(new FileInputStream(FILE_PATH));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load TestData.xlsx: " + e.getMessage());
        }
    }

    /**
     * Get all data from a sheet as Map (column header → value) from row index 1 (first data row)
     */
    public static Map<String, String> getData(String sheetName) {
        return getData(sheetName, 0);
    }

    /**
     * Get data from a sheet as Map (column header → value) from specific data row index (0-based)
     */
    public static Map<String, String> getData(String sheetName, int dataRowIndex) {
        Map<String, String> data = new HashMap<>();
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        Row headerRow = sheet.getRow(0);
        Row dataRow = sheet.getRow(dataRowIndex + 1); // +1 to skip header

        if (headerRow == null || dataRow == null) return data;

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String key = getCellValue(headerRow.getCell(i));
            String value = getCellValue(dataRow.getCell(i));
            if (!key.isEmpty()) {
                data.put(key, value);
            }
        }
        return data;
    }

    /**
     * Get a single value by sheet name and column header (from first data row)
     */
    public static String get(String sheetName, String columnName) {
        Map<String, String> data = getData(sheetName);
        return data.getOrDefault(columnName, "");
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                double val = cell.getNumericCellValue();
                if (val == Math.floor(val)) yield String.valueOf((long) val);
                else yield String.valueOf(val);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> {
                try { yield cell.getStringCellValue().trim(); }
                catch (Exception e) { yield String.valueOf((long) cell.getNumericCellValue()); }
            }
            default -> "";
        };
    }
}
