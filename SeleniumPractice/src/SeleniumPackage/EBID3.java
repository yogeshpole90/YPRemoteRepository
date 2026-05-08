package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class EBID3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);
		
		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);
		//inbox list clicked
		driver.findElement(By.xpath("//li[@id='COLLECTORLIST']/a")).click();
		
		//locate case
		WebElement case2 = driver.findElement(By.xpath("//tr/td[text() = '21207']"));

		//actions class
		Actions act = new Actions(driver);
		act.doubleClick(case2).build().perform();
		
		Thread.sleep(3000);
		WebElement remedial = driver.findElement(By.xpath("//ul[contains(@class ,'lst-section')]/li[8]/a"));
		
		//click on remedial action menu
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior:'smooth' , block : 'center'})", remedial);
		Thread.sleep(3000);
		remedial.click();
		
		//
		WebElement action = driver.findElement(By.xpath("//select[@id='actionId']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'})", action);
	//	action.click();
		
		
		Select action1= new Select(action);
		action1.selectByVisibleText("Full & Final Settlement");
		
		
		

		
		
		
		
		
	}

}
