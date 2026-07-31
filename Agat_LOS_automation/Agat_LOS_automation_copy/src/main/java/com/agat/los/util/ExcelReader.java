package com.agat.los.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

    public static void main(String[] args) throws Exception {
        String path = "src/test/resources/testdata/Eligibility Calculations 3 (1).xlsx";

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            for (int s = 0; s < wb.getNumberOfSheets(); s++) {
                Sheet sheet = wb.getSheetAt(s);
                System.out.println("\n========== SHEET: " + sheet.getSheetName() + " ==========");

                for (Row row : sheet) {
                    StringBuilder line = new StringBuilder();
                    for (Cell cell : row) {
                        String val = getCellValue(cell, evaluator);
                        if (!val.isBlank()) {
                            line.append("[").append(cell.getAddress()).append("] ")
                                .append(val).append("\t");
                        }
                    }
                    if (!line.isEmpty()) System.out.println(line);
                }
            }
        }
    }

    private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) return "";
        try {
            String formula = cell.getCellType() == CellType.FORMULA
                    ? " {=" + cell.getCellFormula() + "}" : "";

            CellType type = cell.getCellType() == CellType.FORMULA
                    ? evaluator.evaluateFormulaCell(cell)
                    : cell.getCellType();

            return switch (type) {
                case NUMERIC -> {
                    double d = cell.getNumericCellValue();
                    yield (d == Math.floor(d) ? String.valueOf((long) d) : String.valueOf(d)) + formula;
                }
                case STRING  -> cell.getStringCellValue() + formula;
                case BOOLEAN -> String.valueOf(cell.getBooleanCellValue()) + formula;
                default      -> cell.toString() + formula;
            };
        } catch (Exception e) {
            return "ERR:" + e.getMessage();
        }
    }
}
