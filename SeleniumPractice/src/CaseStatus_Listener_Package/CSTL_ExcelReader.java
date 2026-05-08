package CaseStatus_Listener_Package;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;

public class CSTL_ExcelReader {

	static String filePath = "D:\\Excel_File_For_Selenium\\EBID_TestCases_Yogesh.xlsx";
	static String sheetName = "CaseStatus_TestData";

	public static Object[][] readExcelData() throws Exception {
		FileInputStream fis = new FileInputStream(filePath);//pipe - navigate
		XSSFWorkbook wb = new XSSFWorkbook(fis);//all sheet data store in memory
		XSSFSheet sheet = wb.getSheet(sheetName);//specific sheet

		int rowCount = sheet.getLastRowNum();    //9   // last row (excluding header)
		int colCount = sheet.getRow(0).getLastCellNum();//5 // total columns

		Object[][] data = new Object[rowCount][colCount];
		//can take String array. but object is universal memory.

		for (int i = 1; i <= rowCount; i++) {       // i=1 → skip header
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				//sheet.getRow(i).getCell(j);
				if (row.getCell(j) != null) {
					//store data in data variable - data[0] = "Test";
					data[i - 1][j] = row.getCell(j).toString();
				} else {
					data[i - 1][j] = "";
				}
			}
		}

		wb.close();
		fis.close();
		return data;
	}
}
