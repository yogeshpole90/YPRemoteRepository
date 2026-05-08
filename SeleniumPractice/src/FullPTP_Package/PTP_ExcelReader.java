package FullPTP_Package;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PTP_ExcelReader {

	// ========== FILE & SHEET CONFIG ==========
	static final String FILE_PATH = "test-data\\Global_TestData.xlsx";
	static final String SHEET_NAME = "FullPTP";

	// ========== COLUMN INDEX CONSTANTS ==========
	public static final class Cols {
		public static final int TC_ID = 0;
		public static final int FIELD_NAME = 1;
		public static final int INPUT = 2;
		public static final int EXPECTED = 3;
		public static final int DESCRIPTION = 4;
		public static final int CHECK_TYPE = 5;
	}

	// ========== TC_ID PREFIX CONSTANTS ==========
	public static final class TC {
		public static final String OVERDUE_AMT = "OD_";
		public static final String PTP_DATE = "DT_";
		public static final String REMARKS = "RM_";
		public static final String PTP_TYPE = "TY_";
		public static final String PAY_MODE = "PM_";
		public static final String PLANNED_AMT = "PA_";
		public static final String REM_AMT = "RA_";
		public static final String PLANNED_DATE = "PD_";
	}

	// ========== READ ALL DATA ==========
	public static Object[][] readAll() throws Exception {
		FileInputStream fis = new FileInputStream(FILE_PATH);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet(SHEET_NAME);

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
		wb.close();
		fis.close();
		return data;
	}

	// ========== FILTER BY TC_ID PREFIX ==========
	public static Object[][] getByTcPrefix(String prefix) throws Exception {
		Object[][] allData = readAll();
		List<Object[]> filtered = new ArrayList<>();

		for (Object[] row : allData) {
			if (row[Cols.TC_ID] != null && row[Cols.TC_ID].toString().startsWith(prefix)) {
				filtered.add(row);
			}
		}
		return filtered.toArray(new Object[0][]);
	}

	// ========== CELL VALUE HELPER ==========
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
