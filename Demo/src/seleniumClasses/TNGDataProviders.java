package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TNGDataProviders {
	WebDriver driver;//important

	@BeforeClass
	public void setEnv() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get("https://www.facebook.com");

	}

	@DataProvider
	public String[][] dataSet()
	{
		String arr[][] = new String[3][2];
		//array size [3][2] = 3 baar input dena hy,aur 2 field hy
		//[4][5] = 4 times imput dena hy.aur 5 fields hy.

		arr[0][0] ="Email_1";
		arr[0][1] = "Pass_1";

		arr[1][0]="Email_2";
		arr[1][1]  = "Pass_2";

		arr[2][0] = "Email_3" ;
		arr[2][1]  = "Pass_3";

		return arr;

	}


	
	@Test(dataProvider = "dataSet")
	public void enterCredentials(String UserName, String  Password) throws Exception
	{
		
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='email']")).clear();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='pass']")).clear();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='email']")).sendKeys(UserName);
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(Password);
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@name='login']")).click();
	
	Thread.sleep(2000);
	driver.navigate().back();
	Thread.sleep(2000);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	

}
