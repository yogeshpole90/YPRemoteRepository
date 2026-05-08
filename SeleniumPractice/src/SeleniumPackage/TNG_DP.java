package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;



public class TNG_DP{


	WebDriver driver;

	@BeforeClass
	public void test() throws Exception
	{

		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

		driver.findElement(By.id("loginId")).sendKeys("infraadmin");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();

		//burger 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//action doc map
		driver.findElement(By.xpath("//*[@id='ACTIONDOCMAP']/a")).click();



	}

}
