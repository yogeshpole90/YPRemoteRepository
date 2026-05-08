package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBIDDoc {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		
		//Click Document vertical menu
		Thread.sleep(3000);
		WebElement doc = driver.findElement(By.xpath("//ul[contains(@class,'d-block border-0')]/li[7]/a"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({behavior : 'smooth' ,block : 'center'})", doc);
		
		
		doc.click();
		
		//enter document sub section
		//dropdown - select
		//iframe
		driver.switchTo().frame("documentUploadPageFrame");
		Thread.sleep(3000);
		 WebElement actname = driver.findElement(By.xpath("//select[@id='actionName']")); //select[@id='actionName']
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'})", actname);

		 Select actname1 = new Select(actname);
		actname1.selectByVisibleText("Full & Final Settlement");
		
		Thread.sleep(2000);
		driver.findElement(By.id("documentName")).sendKeys("FNF Document 1");
		
		driver.findElement(By.id("documentData")).sendKeys("C:\\Users\\Yogesh.Pole\\Music\\FNF_Certificate.pdf");
		
		driver.findElement(By.id("saveData")).click();
		/*
		 * Thread.sleep(3000); WebElement view = driver.findElement(By.
		 * xpath("(//td[text() = 'FNF Document 1'])/following-sibling::td[4]/a"));
		 * WebDriverWait wait1 =new WebDriverWait(driver,10);
		 * wait1.until(ExpectedConditions.elementToBeClickable(view));
		 * Thread.sleep(3000);
		 * jse.executeScript("arguments[0].scrollIntoView({block : 'center'})", view);
		 * view.click();
		 */
		
		
		//delete
		Thread.sleep(3000);
		/*
		 * WebElement delete = driver.findElement(By.
		 * xpath("//td[text() ='FNF Document 1']/following-sibling::td[6]/a"));
		 * WebDriverWait wait1 =new WebDriverWait(driver,10);
		 * wait1.until(ExpectedConditions.elementToBeClickable(delete));
		 * Thread.sleep(3000);
		 * jse.executeScript("arguments[0].scrollIntoView({block : 'center'})", delete);
		 * delete.click();
		 */
		
		
		//download
		WebElement download = driver.findElement(By.xpath("//td[text() ='FNF Document 1']/following-sibling::td[5]/a"));
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.elementToBeClickable(download));
		
		download.click();
		
		Thread.sleep(2000);
		
		
		
		
		
		
		

	}

}
