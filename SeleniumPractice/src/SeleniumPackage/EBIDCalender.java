package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBIDCalender {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);

		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);

		//inbox
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//case double click
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("366");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text() = '366']"));
		Actions act =new Actions(driver);
		act.doubleClick(case1).build().perform();

		//javascript class
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement calender = driver.findElement(By.xpath("//nav[contains(@class , 'section-nav')]/ul/li[4]/a"));

		jse.executeScript("arguments[0].scrollIntoView({ block:'center'});",calender);

		//jse.executeScript("arguments[0].scrollIntoView(true)", calender);
		Thread.sleep(3000);
		calender.click();



		//click on check box  = View All
		Thread.sleep(3000);
		List<WebElement> allboxes = driver.findElements(By.xpath("//*[@type='checkbox']/following-sibling::span[2]"));

		WebElement ptpbox = driver.findElement(By.xpath("//*[text()='PTP']"));

		jse.executeScript( "arguments[0].scrollIntoView({block:'center'})",  ptpbox);

		for(WebElement allboxes1:allboxes)
		{
			System.out.println("allboxes : -" +allboxes1.getText());
		}
		allboxes.get(2).click();
		allboxes.get(4).click();
		allboxes.get(5).click();
		allboxes.get(6).click();
		//allboxes.get(7).click();


		//*[text()='02:00 - 02:30']


		//date selection in calender
		WebElement date = driver.findElement(By.xpath("(//div[contains(@class ,'tui-full-calendar-time-date-s')])[4]"));

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(date));
		date.click();

		//popup
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@placeholder = 'Subject']")).sendKeys("Selenium PTP");
		driver.findElement(By.xpath("//input[@placeholder = 'Location']")).sendKeys("Selenium Pune");
		driver.findElement(By.xpath("//input[@placeholder = 'Description']")).sendKeys("Selenium Pune");


		//start date picker clicked
		WebElement startDate = driver.findElement(By.xpath("//input[contains(@placeholder ,'Start date')]"));
		startDate.click();
		//startDate.sendKeys()
		startDate.sendKeys(Keys.CONTROL,"a");
		//startDate.sendKeys(Keys.CONTROL, "a");
		startDate.sendKeys(Keys.DELETE);//existing delete
		startDate.sendKeys("2026-03-03 08:30");

		//click on End date 
		WebElement enddate = driver.findElement(By.xpath("//input[contains(@placeholder ,'End date')]"));
		enddate.click();
		enddate.sendKeys(Keys.CONTROL+"a");
		enddate.sendKeys(Keys.DELETE);
		//as per date , ptp record will display in calendar..change the date and check record.
		enddate.sendKeys("2026-03-03 09:00");//2026-03-04 09:00

		//enddate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'ic-checkbox')]")).click();

		Thread.sleep(2000);
		WebElement save1 = driver.findElement(By.xpath("//div[contains(@class,'popup')]//button[normalize-space()='Save']"));
		save1.click();










	}

}
