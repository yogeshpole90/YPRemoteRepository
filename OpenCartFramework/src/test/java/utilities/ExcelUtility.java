package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;

    String path;

    // Constructor
    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get Row Count
    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int rowCount = (sheet != null) ? sheet.getLastRowNum() : 0;

        workbook.close();
        fi.close();

        return rowCount;
    }

    // Get Cell Count (columns)
    public int getCellCount(String sheetName, int rownum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null || sheet.getRow(rownum) == null) {
            workbook.close();
            fi.close();
            return 0;
        }

        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // Get Cell Data
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        String data = "";

        if (sheet != null && sheet.getRow(rownum) != null) {

            row = sheet.getRow(rownum);
            cell = row.getCell(colnum);

            try {
                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        data = cell.getStringCellValue();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        data = String.valueOf(cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.BOOLEAN) {
                        data = String.valueOf(cell.getBooleanCellValue());
                    } else {
                        data = "";
                    }
                }
            } catch (Exception e) {
                data = "";
            }
        }

        workbook.close();
        fi.close();

        return data;
    }

    // Set Cell Data
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

        File xlfile = new File(path);

        // If file doesn't exist, create it
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // If sheet doesn't exist, create it
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }

        sheet = workbook.getSheet(sheetName);

        // If row doesn't exist, create it
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }

        row = sheet.getRow(rownum);

        // Create cell and set data
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // Utility: Create Sheet explicitly (optional)
    public void createSheet(String sheetName) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheet(sheetName) == null) {
            workbook.createSheet(sheetName);
        }

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}