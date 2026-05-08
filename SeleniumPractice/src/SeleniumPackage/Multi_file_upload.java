package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Multi_file_upload {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com/");
		
		//multipleFilesInput
		WebElement multifile = driver.findElement(By.id("multipleFilesInput"));
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'})", multifile);
		
		String blockpay = "C:\\Users\\Yogesh.Pole\\Music\\BLOCK_PAYMENT.pdf";
		String cancel = "C:\\Users\\Yogesh.Pole\\Music\\CANCELLED_CHEQUE_ICICI.pdf";
		String fnf = "C:\\Users\\Yogesh.Pole\\Music\\finalsettllement.pdf";
		
		Thread.sleep(2000);
		multifile.sendKeys(blockpay + "\n"+ cancel +"\n"+ fnf);
		
		driver.findElement(By.xpath("//*[text()='Upload Multiple Files']")).click();
		
		
		
		
		
		

	}

}
