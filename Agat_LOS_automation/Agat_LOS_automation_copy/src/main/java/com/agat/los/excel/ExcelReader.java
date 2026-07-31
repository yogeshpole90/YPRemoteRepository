package com.agat.los.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.agat.los.config.ConfigManager;

public class ExcelReader {

    public static Object[][] readSheet(String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(ConfigManager.get("test.data.path"));
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);

        if (sheet == null) {
            wb.close(); fis.close();
            throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file!");
        }

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                if (row != null && row.getCell(j) != null) {
                    data[i - 1][j] = getCellValue(row.getCell(j));
                } else {
                    data[i - 1][j] = "";
                }
            }
        }
        wb.close(); fis.close();
        return data;
    }

    public static Object[][] getByTcPrefix(String sheetName, String prefix) throws Exception {
        Object[][] allData = readSheet(sheetName);
        List<Object[]> filtered = new ArrayList<>();
        for (Object[] row : allData) {
            if (row[0] != null && row[0].toString().startsWith(prefix)) {
                filtered.add(row);
            }
        }
        return filtered.toArray(new Object[0][]);
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                double num = cell.getNumericCellValue();
                if (num == (long) num) return String.valueOf((long) num);
                return String.valueOf(num);
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}
