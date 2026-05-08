package seleniumClasses;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel3 {

	public static void main(String[] args) throws Exception {

		//file Location specified
		File fs = new File("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");
		
		//load file - for java > can read into bytes only not excel sheet
		FileInputStream fis = new FileInputStream(fs);
		
		//Load Workbook - POI reads it as cell/row/excel sheet. 
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//Load Sheet = index =2 (3rd sheet) will load..on this only we can perform operation.
		XSSFSheet sh = wb.getSheetAt(2);
		
		//get sheet name, chronologically
		//1st sheet in workbook
		System.out.println("1st Sheet name : - " + wb.getSheetName(0));

		//2nd sheet in Wb
		System.out.println("2nd Sheet name : - " + wb.getSheetName(1));
		
	

		//3rd Sheet in WB
		System.out.println("3rd Sheet name : - " + wb.getSheetName(2));

		//4th Sheet in WB
		System.out.println("4th Sheet name : - " + wb.getSheetName(3));
		
		//Ctrl is in
		System.out.println("Active Cell :- " + sh.getActiveCell());
		
		//Total Rows : - Blank Row = Not Consider
		int totalrow = sh.getPhysicalNumberOfRows();
		System.out.println("Total Rows :- "+ totalrow);

		//Total Cells
		int totalcol = sh.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total Col :-"+ totalcol);
		
		//First cell = number
		System.out.println("First cell No. :- "+sh.getRow(0).getFirstCellNum());
		
		//Get String value // Image pasted in header cannot print by poi
		System.out.println("Value of Table :- "+sh.getRow(1).getCell(0).getStringCellValue());
		
		//System.out.println("Value of Table :- " + sh.getRow(0).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));

		//Get string Values
		System.out.println("Print Cell values :-"+sh.getRow(8).getCell(0).getStringCellValue());
		
		//Print
		System.out.println("print values :-" + sh.getRow(12).getCell(0).getStringCellValue());
		
		 //mew sheet ' index 3
		 XSSFSheet sh1 = wb.getSheetAt(3);
		 System.out.println("sh1 name is:- " + sh1.getSheetName());
		 
		 
		 int totalrow1 = sh1.getPhysicalNumberOfRows();
		 int totalcol1 = sh1.getRow(0).getPhysicalNumberOfCells();
		 System.out.println(totalrow1);
		 System.out.println(totalcol1);
		//print all values from sheet
		for(int i =0;i<totalrow1;i++)
		{
			for(int j=0;j<totalcol1;j++)
			{
				System.out.println("All Values of Table :-"+sh1.getRow(i).getCell(j).getStringCellValue());
			}
		}
			
		
		
	}

}
