package Listener_Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Login_2 {

	WebDriver driver;

	@Test(priority = 1)
	public void testLogin() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.10.230.16:8181/lcs-finairoLending-1.0.1");
		driver.manage().window().maximize();

		WebElement id = driver.findElement(By.id("loginId"));
		id.clear();
		id.sendKeys("Shelly",Keys.TAB);

		WebElement pwd = driver.findElement(By.id("uiPwd"));
		pwd.clear();
		pwd.sendKeys("abcd1234",Keys.TAB);

		Thread.sleep(1000);
		WebElement logg = driver.findElement(By.id("userLogin"));
		//logg.click();

		
		boolean actID = id.isDisplayed();
		Extent_Report_1.logResult("ID", "Check Displaying ?", "true", String.valueOf(actID) , actID);


	}
	//Test 2 - Fail
	@Test(priority = 2)
	public void pageTitle()
	{
		String ExpTitle = "LCS";
		String ActTitle = driver.getTitle();
		Extent_Report_1.logResult("Title", "Verify title ?", ExpTitle, ActTitle,ExpTitle.equals(ActTitle));
	
	}
	
	// TEST 3 — SKIP hoga ⏭️
	@Test(priority = 3)
	public void testLogout() {
		throw new SkipException("Feature not ready — skipping");
	}




}
