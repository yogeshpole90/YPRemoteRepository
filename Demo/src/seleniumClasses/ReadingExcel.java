package seleniumClasses;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {

	public static void main(String[] args) throws Exception {
		//specify the Location
		File src = new File("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");
		
		//load file
		FileInputStream fis = new FileInputStream(src);
		
		
		//Load Workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//Load Worksheet
		XSSFSheet sh = wb.getSheet("ID_Info");
		
		
		//get the name of loaded sheet
		System.out.println("sheetName :-"+sh.getSheetName());
		
		//print username from excel sheet
		System.out.println("Id :- "+sh.getRow(1).getCell(4).getStringCellValue());
		
		//print  from excel sheet
		System.out.println("PWD :- "+sh.getRow(2).getCell(4).getStringCellValue());
		
		System.out.println("sh : "+sh.getFirstRowNum());//index
		System.out.println(sh.getLastRowNum());//index of last row
		
		//if above blank rows are there,this also calculated in table
		System.out.println("Total No. of Rows :- "+sh.getPhysicalNumberOfRows());
		
		//in 6th row how many columns are there
		System.out.println("cell :"+sh.getRow(3).getPhysicalNumberOfCells());
		
		int rows = sh.getLastRowNum();
		System.out.println(rows);
		int columns = sh.getRow(1).getLastCellNum();
		System.out.println(columns);
		
		//Print all cells 
		for(int i=0;i<=rows ; i++)
		{
			for(int j=0;j<columns;j++)
			{
				System.out.println(sh.getRow(i).getCell(j).getStringCellValue());
			}
	

	}

   }
}