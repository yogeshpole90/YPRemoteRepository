package SeleniumPackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken_Links_2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com/");

		JavascriptExecutor jse =(JavascriptExecutor) driver;
		// Scroll to Broken Links section
		WebElement broken = driver.findElement(By.xpath("//*[text()='Broken Links']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", broken);

		// Get all links containing 'Errorcode'
		List<WebElement> links = driver.findElements(By.xpath("//a[contains(text(),'Errorcode')]"));
		System.out.println("Total Links Found: " + links.size());

		for(WebElement link:links)
		{
			String getattri = link.getAttribute("href");
			URL url =new URL(getattri); //address mil gaya
			URLConnection uc = url.openConnection();//know on door
			HttpURLConnection response = (HttpURLConnection) url.openConnection();//response from door
		
		
			try 
			{
				int responsecode = response.getResponseCode();
				//System.out.print("responsecode :- "+responsecode +" , ");

				System.out.println();

				if(responsecode>=400)
				{
					System.out.println("Invalid code is ...: "+ responsecode);
					System.out.println("URL is:- "+url.toString());

				}
				else
				{
					System.out.println("Valid code is : " + responsecode );
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//./screenshot = under project level
			//ITResult = failure of test cases




		}






	}

}
