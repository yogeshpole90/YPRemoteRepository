package CaseStatus_Package;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class URL_Connection1 {
	@Test
	public void DB() throws Exception
	{
		int brokenCount=0;
		int validCount=0;

		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com");

		//String url = "https://demoqa.com/links";
		List<WebElement> alllinks = driver.findElements(By.xpath("//div[@id='broken-links']/a"));

		//scroll
		WebElement scroll = driver.findElement(By.xpath("//div[@id='broken-links']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center', behaviour:'smooth'})", scroll);

		for(WebElement link : alllinks)
		{
			String urlString = link.getAttribute("href");
			if(urlString == null || urlString.isEmpty())
			{
				System.out.println("URL is Empty.Unable to hit and Check.");
				continue;

			}
			try 
			{
				URL urlObj = new URL(urlString);
				HttpURLConnection huc = (HttpURLConnection) urlObj.openConnection();
				int respCode = huc.getResponseCode();
				String respMsg = huc.getResponseMessage();




				if(respCode >= 400)
				{
					System.out.println("Broken Link: "+urlString +" ====>> "  +respCode + "_"+ respMsg);
					brokenCount++;
				}
				else {

					System.out.println("Valid Link: "+urlString +"====>> "+respCode+"_"+respMsg);
					validCount++;
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}


		}
		System.out.println("Valid Link: " +validCount +"\n"+"Broken Link: "+brokenCount);

	}
}