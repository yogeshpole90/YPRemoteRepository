package seleniumClasses;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

		
		public class ReadingExcel1 {

			public static void main(String[] args) throws Exception {

		        FileInputStream fis =
		                new FileInputStream("D:\\Excel_File_For_Selenium\\DemoSheet.xlsx");

		        XSSFWorkbook wb = new XSSFWorkbook(fis);
		        XSSFSheet sh = wb.getSheet("ID_Info");

		        System.out.println("Sheet Name : " + sh.getSheetName());

		        int rows = sh.getLastRowNum();
		        int columns = sh.getRow(0).getLastCellNum();

		        DataFormatter formatter = new DataFormatter();

		        for (int i = 0; i <= rows; i++) {

		            Row row = sh.getRow(i);
		            if (row == null) continue;

		            for (int j = 0; j < columns; j++) {

		                Cell cell = row.getCell(j);
		                if (cell == null) {
		                    System.out.print(" | ");
		                    continue;
		                }

		                String value = formatter.formatCellValue(cell);
		                System.out.print(value + " | ");
		            }
		            System.out.println();
		        }

		        wb.close();
		        fis.close();
		    }
		


	}

