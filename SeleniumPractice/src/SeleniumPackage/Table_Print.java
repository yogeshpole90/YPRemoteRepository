package SeleniumPackage;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Table_Print {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://testautomationpractice.blogspot.com/");

		// 🔹 Header Print
		List<WebElement> headers = driver.findElements(
				By.xpath("//table[@id='productTable']//thead/tr/th"));

		for (WebElement head : headers) {
			System.out.print(head.getText() + "   ");
		}
		System.out.println();
		System.out.println("--------------------------------------");

		// 🔹 Rows Print
		List<WebElement> rows = driver.findElements(
				By.xpath("//table[@id='productTable']//tbody/tr"));

		for (WebElement row : rows) {
			System.out.println(row.getText());
		}

		//driver.quit();
	}
}



