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

public class KFIC_color {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.93:7723/lcs-finairoLending-1.0.1/");
		Thread.sleep(2000);
		
		
		driver.findElement(By.id("languageCode")).sendKeys(Keys.TAB);
		WebElement id = driver.findElement(By.id("loginId"));
		id.sendKeys("LO1");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		WebElement pwd = driver.findElement(By.id("uiPwd"));
		pwd.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		WebElement login = driver.findElement(By.id("userLogin"));
		
		//color
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", id);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", pwd);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", login);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", login);
		//login.click();
		Thread.sleep(4000);
		login.click();

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);
		//legal list sub menu click
		driver.findElement(By.xpath("//li[@id='LEGALLIST']/a")).click();
		
		//click on case
		WebElement case1 = driver.findElement(By.xpath("//tr[@class='odd']/td[2]"));

		Actions act = new Actions(driver);
		act.doubleClick(case1).build().perform();
		
	//	JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement legalprocess = driver.findElement(By.xpath("//nav[contains(@class , 'section-nav')]/ul/li[5]/a"));
		
		jse.executeScript("arguments[0].scrollIntoView(true)", legalprocess);
		legalprocess.click();
		//frame opened
	    //need to swicth into that
		int totalFrames = driver.findElements(By.tagName("iframe")).size();
		System.out.println(totalFrames);
		WebDriver ss = driver.switchTo().frame(0);
		
		//loan acc no
		WebElement loanacc = driver.findElement(By.xpath("//select[@id='loanAcNo']"));
		
		Select loanacc1 = new Select(loanacc);
		jse.executeScript("arguments[0].scrollIntoView(true)", loanacc1);
		loanacc1.selectByVisibleText("01300030001135171");
		
		//Law Firm
		WebElement firmcode1 = driver.findElement(By.xpath("//select[@id='lawFirmCodeSelect']"));
		Select firmcode = new Select(firmcode1);
		jse.executeScript("arguments[0].scrollIntoView(true)", firmcode);
		firmcode.selectByVisibleText("Testt");
		
		
		
		//checklist
		WebElement checklist = driver.findElement(By.xpath("//select[@id='selectedDocuments']"));
		Select checklist1 = new Select(checklist);
		jse.executeScript("arguments[0].scrollIntoView(true)", checklist1);
		checklist1.selectByVisibleText("Civil ID");
		checklist1.selectByVisibleText("Commercial License");
		checklist1.selectByVisibleText("Acknowledgment Template");
		checklist1.selectByVisibleText("Final Contract");

		
		//lateChargesApplication
		WebElement Late = driver.findElement(By.xpath("//select[@id='lateChargesApplication']"));
		Select Late1 = new Select(Late);
		jse.executeScript("arguments[0].scrollIntoView(true)", Late1);
		Late1.selectByVisibleText("Allocation Date");

		//claimed amount
		//driver.findElement(By.xpath("//input[@id='claimedAmount']")).clear();
		//driver.findElement(By.xpath("//input[@id='claimedAmount']")).sendKeys("100000");

		//Remarks
		driver.findElement(By.xpath("//textarea[@id='remarks']")).sendKeys("Tested on 19-01-2026");
		driver.findElement(By.xpath("//button[@id='allocateBtn']")).click();
		
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-600)");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		//jse.executeScript("window.scrollBy(0,-00)");

		Thread.sleep(3000);
		//View Format
		WebElement view1 = driver.findElement(By.xpath("//table[@id='allocation-history-table']//tbody/tr[1]//a[text()='View']"));
		jse.executeScript("arguments[0].scrollIntoView(true)", view1);
		//jse.executeScript("window.scrollBy(1000,0)");
		Thread.sleep(3000);

		

		
		
		
		
		
		
		
		
		
		


	}

}
