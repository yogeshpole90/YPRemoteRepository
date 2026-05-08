package seleniumClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//Specify Location
		File fs = new File("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");
		
		//File Load
		FileInputStream fis = new FileInputStream(fs);
		
		//Excel Workbook parsing
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//Excel Sheet Parsing
		XSSFSheet sh = wb.getSheet("ID_Info");
		
		//Name of Loaded  Sheet
		System.out.println("SheetName :- " + sh.getSheetName());
		
		//Total No. of Rows
		//if u move table / copy paste table then that rows also counted.even data is blank
		//selenium counted it
		int row = sh.getPhysicalNumberOfRows();
		System.out.println("Total Rows :- "+ row);
		
		//good approach to find last row
		int lastrow = sh.getLastRowNum();
		System.out.println("lastrow :- " +lastrow);
		
		//
		CellAddress columns = sh.getActiveCell();
		System.out.println("Active Cell :-"+ columns);
		
		//gives u number of last cell
		short lastcell = sh.getRow(0).getLastCellNum();
		System.out.println("lastcell :- "+ lastcell);
		
		//gives u starting position of first cell
		short firstcell = sh.getRow(3).getFirstCellNum();
		System.out.println("firstcell :-" + firstcell);
		
		//Total Columns
		int totalcol = sh.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Totalcol :-"+totalcol);
		
		
		
		
		
		
		
		

	}

}
