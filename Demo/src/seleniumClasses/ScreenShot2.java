package seleniumClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

public class ScreenShot2 {

	public static void main(String[] args) throws Exception {	

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Open Facebook
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);

		// Enter wrong credentials for demo
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("uiPwd")).clear();

		driver.findElement(By.id("uiPwd")).sendKeys("abcde@123456");
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(3000);
		

		boolean login = driver.getPageSource().contains("User");//
		
		if(login==true)
		{
			// Take Screenshot
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);

			// Destination file (folder must exist)
			File dest = new File("D:\\Screenshots\\EBID1.png"); // folder D:\Screenshots pehle se bana hona chahiye
			FileUtils.copyFile(src, dest);

			System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
			
		}

		else {
			System.out.println("Login Successfully done - No SS Taken");
		}
	}


}
