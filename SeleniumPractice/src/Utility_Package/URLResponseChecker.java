package Utility_Package;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class URLResponseChecker {

	WebDriver driver;
	int totalLinks = 0, validLinks = 0, brokenLinks = 0, skippedLinks = 0;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void checkAllLinks() {
		String url = "https://www.google.com"; // change URL here
		driver.get(url);

		System.out.println("==========================================================");
		System.out.println("URL RESPONSE CODE CHECKER");
		System.out.println("Page: " + url);
		System.out.println("==========================================================");

		// Get all <a> tags
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("Total <a> tags found: " + allLinks.size());
		System.out.println("==========================================================");

		for (WebElement link : allLinks) {
			String href = link.getAttribute("href");
			totalLinks++;

			// Skip null, empty, javascript, mailto, tel links
			if (href == null || href.isEmpty() || href.startsWith("javascript")
					|| href.startsWith("mailto") || href.startsWith("tel") || href.startsWith("#")) {
				System.out.println("[SKIPPED] " + href);
				skippedLinks++;
				continue;
			}

			checkResponseCode(href);
		}

		// Also check all <img> tags
		System.out.println("\n==========================================================");
		System.out.println("CHECKING IMAGE URLs");
		System.out.println("==========================================================");

		List<WebElement> allImages = driver.findElements(By.tagName("img"));
		for (WebElement img : allImages) {
			String src = img.getAttribute("src");
			totalLinks++;
			if (src == null || src.isEmpty()) {
				System.out.println("[SKIPPED] Empty image src");
				skippedLinks++;
				continue;
			}
			checkResponseCode(src);
		}

		// Summary
		System.out.println("\n==========================================================");
		System.out.println("SUMMARY");
		System.out.println("==========================================================");
		System.out.println("Total URLs Checked : " + totalLinks);
		System.out.println("Valid (200)        : " + validLinks + " ✅");
		System.out.println("Broken (4xx/5xx)   : " + brokenLinks + " ❌");
		System.out.println("Skipped            : " + skippedLinks + " ⏭️");
		System.out.println("==========================================================");
	}

	private void checkResponseCode(String linkUrl) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(linkUrl).openConnection();
			conn.setRequestMethod("HEAD");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.connect();

			int code = conn.getResponseCode();
			String msg = conn.getResponseMessage();
			conn.disconnect();

			if (code >= 200 && code < 400) {
				System.out.println("[" + code + " " + msg + "] ✅ " + linkUrl);
				validLinks++;
			} else {
				System.out.println("[" + code + " " + msg + "] ❌ BROKEN → " + linkUrl);
				brokenLinks++;
			}
		} catch (Exception e) {
			System.out.println("[ERROR] ❌ " + linkUrl + " → " + e.getMessage());
			brokenLinks++;
		}
	}

	@AfterClass
	public void teardown() {
		if (driver != null) driver.quit();
	}
}
