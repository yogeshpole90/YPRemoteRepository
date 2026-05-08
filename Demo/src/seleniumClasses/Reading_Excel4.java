package seleniumClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reading_Excel4 {

	public static void main(String[] args) throws IOException {
		
		//location specified
		//only address can remember here //box
		File fs = new File("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");
		
		//load Files
		//open gate //byte code cnverter //key
		FileInputStream fis = new FileInputStream(fs);
		
		//Load workbook - poi reads it
		//poi for excel reading //gift
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		System.out.print("Sheet Names :- "+ wb.getSheetName(0));
		System.out.println("get all names: - "+ wb.getAllNames());
		System.out.println( "all pic :- " + wb.getAllPictures());
		System.out.println("get class :- " + wb.getClass());
		
		//sheet loader
		XSSFSheet sh = wb.getSheetAt(3);
		System.out.println("Active cell :-" +sh.getActiveCell());
		
		
		System.out.println("getrow :- " +sh.getRow(0).getCell(1));
		System.out.println(" total rows :- "+ sh.getPhysicalNumberOfRows());
		System.out.println("total cell  :- "+sh.getRow(0).getPhysicalNumberOfCells());

		System.out.println("Last Row :- "+sh.getLastRowNum());
		System.out.println("1*2 : -"+sh.getRow(1).getCell(2).getStringCellValue());
		System.out.println("First Row : - "+sh.getFirstRowNum());
		
	


		
		
		
		
		


	}

}
