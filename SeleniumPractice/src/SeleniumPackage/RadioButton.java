package SeleniumPackage;

import java.awt.event.WindowStateListener;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RadioButton {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		
		driver.manage().window().maximize();
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		driver.navigate().to("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='loginId']")).sendKeys("Shelly");
        driver.findElement(By.xpath("//input[@id='uiPwd']")).clear();
        Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='uiPwd']")).sendKeys("abcde@12345");
		driver.findElement(By.xpath("//button[@id='userLogin']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class = 'item-nav']/div[1]")).click();
		
		//a[@class ='item-nav']/div[1]
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@target ='_self']")).click();
		
	//	Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[@id='allCollectorList']/b[1]")).click();
		
		Thread.sleep(4000);
		WebElement case1 = driver.findElement(By.xpath("//td[@class='sorting_1']"));
		
		
		Actions Y = new Actions(driver); //Actions class
		
		Y.doubleClick(case1).perform();
		
		
		Thread.sleep(4000);
		//js executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		
		Thread.sleep(4000);
		
		//click on Calender
		driver.findElement(By.xpath("//ul[@class ='lst-section-nav nav nav-tabs d-block border-0']/li[3]/a[1]")).click();
		//ul[@class='lst-section-nav nav nav-tabs d-block border-0']/li[7]/a[1]
		
       Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,600)");
		//box of checkboxes
		//List<WebElement> visit = driver.findElements(By.xpath("//label/input[@type='checkbox']"));
		//List<WebElement> visit = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//working
		List<WebElement> visit = driver.findElements(By.xpath("//div[@id='calendarList']/div"));
		//div[@id='calendarList']
		int size1 = visit.size();
		 System.out.println("Total Checkbox " + size1);
		
		Thread.sleep(3000);
		for(WebElement size2 : visit )
		{
			System.out.println(size2.getText());//working
		}
		
		
		boolean ss = visit.get(2).isDisplayed();
		boolean tt = visit.get(2).isEnabled();
		boolean uu = visit.get(2).isSelected();
		System.out.println(ss);//t
		System.out.println(tt);//t
		System.out.println(uu);//f
		
		visit.get(2).click();
		boolean vv = visit.get(2).isSelected();
		System.out.println(uu);//t

		
		
	//	List<WebElement> dd = driver.findElements(By.xpath("//select[@id='actionId']/option"));
		
	  // WebElement ptp = driver.findElement(By.xpath("//a[normalize-space()='Promise to pay']"));
	  //a[normalize-space()='Promise to pay']
	   //a[contain(@class = 'nav-link active')]
		//Y.doubleClick(ptp).perform();
	   //js.executeScript("window.scrollBy(0,700)");
	   
		//System.out.println("Dropdown values are "+dd.size());
		
		
	 //input[@type='checkbox']
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
