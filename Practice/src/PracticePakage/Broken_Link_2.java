package PracticePakage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Broken_Link_2 {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com/");

		// Scroll to Broken Links section
		WebElement broken = driver.findElement(By.xpath("//*[text()='Broken Links']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", broken);

		// Get all links containing 'Errorcode'
		List<WebElement> links = driver.findElements(By.xpath("//a[contains(text(),'Errorcode')]"));
		System.out.println("Total Links Found: " + links.size());

		// Loop through each link
		for (WebElement link : links) 
		{
			String url = link.getAttribute("href");
			

			URL url1 = new URL(url);
			HttpURLConnection linkurl = (HttpURLConnection) url1.openConnection();
			
			//use Get method
			linkurl.setRequestMethod("Get");
			linkurl.setConnectTimeout(5000);
			linkurl.setReadTimeout(5000);
			
			//response code 
			int respcode = linkurl.getResponseCode();
			
			if(respcode >= 200 && respcode < 300 )
			{
				System.out.println(url + " URL is Valid...");
			}
			else 
			{
				System.out.println(url + " URL is Broken/Invalid...");
			}
			
			//ChromeOptions options = new ChromeOptions();
			
			
			
			
					

		}

	}
}