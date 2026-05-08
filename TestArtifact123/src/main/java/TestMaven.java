import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestMaven {

	public static void main(String[] args) throws Exception {

		System.out.println("Maven Learning");
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		Thread.sleep(1000);
		
		//navigation
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1/");
		
		//Login
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		driver.findElement(By.id("userLogin")).click();
		
		//burger
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		
		//inbox
		driver.findElement(By.xpath("//li[@id='COLLECTORLIST']/a")).click();
		
		//case
		WebElement case1 = driver.findElement(By.xpath("//td[text()='10811']"));
		Actions act = new Actions(driver);
		act.doubleClick(case1).build().perform();
		
		//remedial actions
		WebElement rem = driver.findElement(By.xpath("//ul[contains(@class , 'border-0')]/li[8]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center', behavior:'smooth' })", rem);
		Thread.sleep(2000);
		rem.click();
		
		//PTP
		WebElement ptp = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		Thread.sleep(1000);
		act.doubleClick(ptp).build().perform();
		
		//switch
		driver.switchTo().frame("fetchPTPMstTabFrame");
		
		//locate in child frame
		WebElement paymod = driver.findElement(By.xpath("//select[@id='paymentMode']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", paymod);
		
		Thread.sleep(1000);
		Select s1  =new Select(paymod);
		s1.selectByVisibleText("Cheque");
		
		//cheque date
		driver.findElement(By.id("chequeDate")).sendKeys("19-02-2026");
		
		//chequeNumber
		driver.findElement(By.id("chequeNumber")).sendKeys("CN178789898");
		
		//chequeAmt
		driver.findElement(By.id("chequeAmt")).sendKeys("10000");
		
		//add
		driver.findElement(By.id("add")).click();
		
		//saveData
		driver.findElement(By.id("saveData")).click();
		
		//alert
		String alert1 = driver.switchTo().alert().getText();
		System.out.println("Alert telling that :- "+alert1);
		
		//accept
		driver.switchTo().alert().accept();
		
		//Scroll Till Top
		//swicth to parent and take ss
		driver.switchTo().parentFrame();
		
        jse.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);
		
		
		
		//Screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("D:\\Screenshots\\SS_" + System.currentTimeMillis() + ".png");

		FileUtils.copyFile(src, dest);
		
		
		
		
		
		
		
		

		
	}

}
