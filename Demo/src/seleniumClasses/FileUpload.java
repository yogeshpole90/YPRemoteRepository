package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.get(	"https://the-internet.herokuapp.com/upload");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='file-upload']")).sendKeys("C:\\Users\\Yogesh.Pole\\Music\\FNF_Certificate.pdf");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='button']")).click();
		
		
		
	}

}
