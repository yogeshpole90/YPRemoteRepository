package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Progress_bar2 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/progress-bar");

		int value=0;
		driver.findElement(By.id("startStopButton")).click();


		while(value < 79)
		{
			WebElement progbar = driver.findElement(By.xpath("//div[@role='progressbar']"));
			System.out.println(progbar.getAttribute("aria-valuenow"));
			value = Integer.parseInt(progbar.getAttribute("aria-valuenow"));
		
			System.out.println("Current value: " + value);
		}
		driver.findElement(By.id("startStopButton")).click();
		System.out.println("Progress Bar Stopped");


	}

}
