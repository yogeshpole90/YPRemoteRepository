import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOI_1 {

	public static void main(String[] args) throws Exception {
		//file Location specified
		File fs = new File("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");

		//load file - for java > can read into bytes only not excel sheet
		FileInputStream fis = new FileInputStream(fs);

		//wb
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		
		//sheet
		//XSSFSheet sh2 = wb.getSheetAt(3);
		
		System.out.println(wb.getSheetName(0));
		System.out.println(wb.getSheetName(1));
		System.out.println(wb.getSheetName(2));
		System.out.println(wb.getSheetName(3));
		
		//Ctrl goes into sheet , selected
		XSSFSheet sh2 = wb.getSheetAt(3);
		
		int actsheet = wb.getActiveSheetIndex();
		System.out.println("actsheet index is :- " + actsheet);
		
		//active cell
		CellAddress actcel = sh2.getActiveCell();
		System.out.println("actcel :- " +actcel);

		//total cell
		int totcel = sh2.getRow(0).getPhysicalNumberOfCells();
		System.out.println("totcell is :- "+ totcel);
		
		//total row
		int totrow = sh2.getPhysicalNumberOfRows();
		System.out.println("totrow is :- " + totrow);
		
		//
		String value1 = sh2.getRow(2).getCell(2).getStringCellValue();
		System.out.println("value1 data is :- "+ value1);
		
		
		//switch to other sheet
		XSSFSheet sh3 = wb.getSheetAt(0);
		System.out.println("New Sheet is :-"+sh3.getSheetName());
		
		//totrow0
		int totrow0 = sh3.getPhysicalNumberOfRows();
		System.out.println("totrow0 is :- "+ totrow0);
		
		//totcel0
		//as per zero index row,cell number displaying correctly
		int totcel0 = sh3.getRow(0).getPhysicalNumberOfCells();
		System.out.println("totcel0 is :- "+ totcel0);
		
		//71
		String value2 = sh3.getRow(5).getCell(1).getStringCellValue();
		System.out.println("value2 is :- "+ value2);
		
		//
		int lastcel = sh3.getRow(0).getLastCellNum();
		System.out.println("lastcel :-" + lastcel);
		
		//
		int firstcel = sh3.getRow(0).getFirstCellNum();
		System.out.println("firstcel : - " + firstcel);
		
		
		
		


	}

}
