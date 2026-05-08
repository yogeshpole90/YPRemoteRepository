package SeleniumPackage;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT_1 {

	// Excel Read Method (NO @Test here)
	public static Object[][] getExcelData() throws Exception {

		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {
		    for (int j = 0; j < cols; j++) {

		        if (sheet.getRow(i) != null && sheet.getRow(i).getCell(j) != null) {
		            data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
		        } else {
		            data[i - 1][j] = "";
		        }
		    }
		}

		//workbook.close();
		//fis.close();

		return data;
	}

	// DataProvider
	@DataProvider
	public Object[][] ptpData() throws Exception {
		return getExcelData();
		 
	}

	// Real Test Method
	@Test(dataProvider = "ptpData")
	public void testPTP(String amount, String date, String type,
			String due, String collected,
			String mode, String chqDate,
			String chqNo, String extra) {

		System.out.println(amount + " | " + mode);
		
		
		
	}
}