package SeleniumPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Assertion_Spelling{


	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1");
		driver.manage().window().maximize();

		// Page ka poora text
		WebElement body = driver.findElement(By.tagName("body"));
		String allText = body.getText();

		System.out.println("Complete Page Text:\n");
		System.out.println(allText);

		driver.quit();
	}

}
