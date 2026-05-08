package SeleniumPackage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion_2 {

	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	WebDriverWait wait;
	WebElement id, pwd, logbtn;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1");
		wait = new WebDriverWait(driver, 10);
	}

	@Test(priority = 1)
	public void titleTest() 
	{
		String actualTitle = driver.getTitle();
		String expectedTitle = "Kiya.ai - Lending Solutions";
		sa.assertEquals(actualTitle, expectedTitle, "Title Should be Same.");
		System.out.println("Title Displaying on UI: '" + actualTitle + "'");
		sa.assertAll();
	}

	@Test(priority = 2)
	public void urlTest() 
	{
		String actualURL = driver.getCurrentUrl();
		sa.assertTrue(actualURL.contains("lcs-finairoLending"), "URL is Not correct!");
		System.out.println("URL on UI: " + actualURL);
		sa.assertAll();
	}

	@Test(priority = 3)
	public void loginEnableTest() 
	{
		locateLoginElements();
		sa.assertTrue(id.isEnabled(), "ID is Not Enabled!");
		sa.assertTrue(pwd.isEnabled(), "PWD is Not Enabled!");
		sa.assertTrue(logbtn.isEnabled(), "Login button is Not Enabled!");
		System.out.println("All Login Fields are Enabled.");
		sa.assertAll();
	}

	@Test(enabled = false)
	public void login()
	{

		sa.assertTrue(id.isEnabled(),"ID is NOT Enabled ! ");
		sa.assertTrue(pwd.isEnabled(),"pwd is NOT Enabled ! ");
		sa.assertAll();
		sa.assertEquals(logbtn.isEnabled(), "LogIn Button is Not Enable ! ");
		sa.assertTrue(id.isSelected(),"ID field is Not Selected ! ");
		sa.assertEquals(pwd.isSelected(), "PWD field is Not Selected ! ");

	}
	@Test(priority = 4)
	public void loginDisplayTest() {
		locateLoginElements();
		sa.assertTrue(id.isDisplayed(), "ID is Not Displayed!");
		sa.assertTrue(pwd.isDisplayed(), "PWD is Not Displayed!");
		sa.assertTrue(logbtn.isDisplayed(), "Login button is Not Displayed!");
		System.out.println("All Login Fields are Displayed.");
		sa.assertAll();
	}

	@Test(priority = 5)
	public void emptyLoginErrorTest() {
		locateLoginElements();
		String expectedMsg = "No User Found";

		id.sendKeys(Keys.TAB);
		pwd.sendKeys(Keys.TAB);
		logbtn.click();

		WebElement errormsg = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'msg-toast msg-error')]/em")) );

		String actualMsg = errormsg.getText().trim();
		sa.assertTrue(actualMsg.contains(expectedMsg), "Actual Error Msg is Incorrect!");
		System.out.println("Error Message displayed: " + actualMsg);

		driver.navigate().refresh();
		sa.assertAll();
		//   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='msg-toast']")));


	}

	@Test(priority = 6)
	public void blankPasswordErrorTest() {
		locateLoginElements();

		id.clear();
		id.sendKeys("Dora");
		id.sendKeys(Keys.TAB);
		logbtn.click();


		WebElement errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'msg-toast msg-error')]/em")) );

		String expectedMsg = "Password cannot be blank";
		String actualMsg = errormsg.getText().trim();

		sa.assertTrue(actualMsg.contains(expectedMsg), "Error Msg is Wrong!");
		System.out.println("If Password is blank showing: " + actualMsg);
		sa.assertAll();
	}
	@Test(priority = 7)
	public void logined() throws InterruptedException
	{
		driver.navigate().refresh();

		locateLoginElements();
		id.clear();
		id.sendKeys("Shelly");
		id.sendKeys(Keys.TAB);
		pwd.sendKeys("abcd1234");
		pwd.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		logbtn.click();




	}

	@Test(priority = 50)
	public void quitDriver() {
		driver.quit();
	}

	private void locateLoginElements() {
		id = driver.findElement(By.id("loginId"));
		pwd = driver.findElement(By.id("uiPwd"));
		logbtn = driver.findElement(By.id("userLogin"));
	}
}