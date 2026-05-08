package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshot_1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"https://demoqa.com/progress-bar");
		Thread.sleep(2000);

		WebElement prgbar = driver.findElement(By.xpath("//*[@role='progressbar']"));
		WebElement startbn = driver.findElement(By.id("startStopButton"));
		startbn.click();
		Thread.sleep(2000);
		//
		int value=0;
		value = Integer.parseInt(prgbar.getAttribute("aria-valuenow"));
		//78
		while(value == 79)
		{
			System.out.println("AV of 'aria-valuenow' is " + prgbar.getAttribute("aria-valuenow"));			

			break;
		}

		startbn.click();
		System.out.println("Stopped Progress...");


	}

}
