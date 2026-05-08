package Legal_Diary_Pkg;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class LD_ExcelUtil {

	static XSSFWorkbook wb;
	static XSSFSheet sheet;

	public static void initExcel(String filePath, String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
	}

	public static String getCellData(int row, int col) {
		try {
			XSSFCell cell = sheet.getRow(row).getCell(col);
			if (cell == null) return "";
			DataFormatter df = new DataFormatter();
			return df.formatCellValue(cell).trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static int getRowCount() {
		return sheet.getLastRowNum();
	}
}
