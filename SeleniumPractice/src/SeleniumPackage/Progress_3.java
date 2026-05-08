package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Progress_3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/progressbar/");
		
		//++++++++++++++ 1++++++++++++++++ +
		//*[text()='Default functionality']
		driver.findElement(By.xpath("//*[text()='Default functionality']")).click();
		
		//switch to frame
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frame1);
		
		Thread.sleep(2000);
		WebElement defbar = driver.findElement(By.id("progressbar"));
		System.out.println("AV is :- " +defbar.getAttribute("aria-valuenow"));
		
		if(defbar.isDisplayed() == true)
		{
			System.out.println("defbar is visible " );
		}
		
		
		// +++++++++++++++++++ New Progress bar +++++++++++++++++++++
		
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		
		driver.findElement(By.xpath("//*[text()='Custom Label']")).click();
		
		//bar Frame
		driver.switchTo().frame(frame1);
		WebElement custom = driver.findElement(By.xpath("//*[@id='progressbar']"));
		int value=0;
		value =Integer.parseInt(custom.getAttribute("aria-valuenow"));

		System.out.println(value);



		
		
		
		
		
		
		
		
		
		
	


		
		
		
		

	}

}
