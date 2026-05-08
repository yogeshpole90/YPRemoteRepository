package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;   // ❗ missing import fix
import utilities.ExcelUtility;              // ❗ make sure correct package

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = ".\\testData\\Global_TestData.xlsx"; // taking xl file from testData
        //C:\Users\Yogesh.Pole\eclipse-workspace\OpenCartFramework\
        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

        int totalrows = xlutil.getRowCount("LoginSDET");
        int totalcols = xlutil.getCellCount("LoginSDET", 1);

        // ❗ safety: avoid negative/zero issues
        if (totalrows <= 0 || totalcols <= 0) {
            return new String[0][0];
        }

        String logindata[][] = new String[totalrows][totalcols];
        // created for two dimensional array which can store data

        for (int i = 1; i <= totalrows; i++)  // read the data from xl storing in two dimensional array
        {
            for (int j = 0; j < totalcols; j++)  // i is rows j is col
            {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
          //i START FROM 1.ZERO POSITION SHLD NOT WASTE
            }
        }

        return logindata; // returning two dimensional array
    }
    
    //DataProvider2(name= "name")
    
    //DataProvider3()
}



