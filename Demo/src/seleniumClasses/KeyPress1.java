package seleniumClasses;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.CopyUtils;
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

public class KeyPress1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/");
		
		WebElement keyprs = driver.findElement(By.xpath("//a[text()='Key Presses']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", keyprs);
		
		keyprs.click();
		
		//input
		WebElement input = driver.findElement(By.xpath("//input[@id='target']"));
		
		Thread.sleep(1000);
		input.sendKeys(Keys.ENTER);
		
		//hover
		System.out.println("before back URL = " + driver.getCurrentUrl());

		driver.switchTo().parentFrame();
		Thread.sleep(3000);
		driver.navigate().back();
		driver.navigate().back();
		System.out.println("After back URL = " + driver.getCurrentUrl());

		Thread.sleep(3000);
		WebElement hover = driver.findElement(By.xpath("//a[text()='Hovers']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", hover);
		hover.click();

		//Actions
		Actions act = new Actions(driver);
		
		
		WebElement img1 = driver.findElement(By.xpath("(//img[@alt='User Avatar'])[1]"));
		act.moveToElement(img1).build().perform();

		Thread.sleep(2000);
		WebElement img2 = driver.findElement(By.xpath("(//img[@alt='User Avatar'])[2]"));
		act.moveToElement(img2).build().perform();
		
		Thread.sleep(2000);
		WebElement img3 = driver.findElement(By.xpath("(//img[@alt='User Avatar'])[3]"));
		act.moveToElement(img3).build().perform();
		//clicked
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[text()='View profile'])[3]")).click();
		
		//ss
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String folder = "D:\\Screenshots\\";
		String fname = "ss_" + System.currentTimeMillis() + ".png";  // add .png extension
		File dest = new File(folder + fname);
		FileUtils.copyFile(src, dest);
		System.out.println("ss taken-");
		System.out.println("Path of SS File is :- "+dest.getAbsolutePath());
		

		
		
		
		
		

		
		

	}

}
