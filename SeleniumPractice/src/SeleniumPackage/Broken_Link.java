package SeleniumPackage;

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

public class Broken_Link {
	public static void main(String[] args) {
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
		for (WebElement link : links) {
			String url = link.getAttribute("href");

			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty or Not Configured");
				continue;
			}

			try {
				// Create connection
				HttpURLConnection con = (HttpURLConnection) (new URL (url).openConnection());
				con.setRequestMethod("GET");
				con.connect();
				int responseCode = con.getResponseCode();

				if (responseCode >= 400) {
					System.out.println(url + " is Broken Link -> Response Code: " + responseCode);
				} else {
					System.out.println(url + " is Valid Link -> Response Code: " + responseCode);
				}
			} catch (Exception e) {
				System.out.println(url + " -> Exception: " + e.getMessage());
			}
		}

		driver.quit();
	}




}