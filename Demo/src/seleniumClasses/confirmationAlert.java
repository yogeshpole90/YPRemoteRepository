package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class confirmationAlert {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"https://demo.automationtesting.in/Alerts.html");
		
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		
		//String expectedResult = "click the button to display an  alert box:"
	/*	Assert.assertEquals(driver.switchTo().alert().getText(),expectedResult);*/
		
		String ok = driver.switchTo().alert().getText();
		System.out.println(ok);
		Thread.sleep(3000);		
		driver.switchTo().alert().accept();

		driver.switchTo().alert().dismiss();
		//dismiss is hidden in confirmation alert
		
		
		
		driver.switchTo().alert().sendKeys("Click OK");
		
		
		
		
		

	}

}
