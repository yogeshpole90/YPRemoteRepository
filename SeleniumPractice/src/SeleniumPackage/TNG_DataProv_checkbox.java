package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TNG_DataProv_checkbox {
	
	WebDriver driver;
	JavascriptExecutor jse;
	@BeforeClass
	public void test1() throws Exception
	{
	System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	 driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Thread.sleep(1000);
	driver.get(	"https://www.test.edzotech.com/?utm_source=chatgpt.com");
	Thread.sleep(2000);
	
	}
	
	@DataProvider
	public Object[][] giver()
	{
		
		return new Object[][]
				{
			{"aa","ss@gmail.com","bb","Male","Reading","United States","01-05-2000"},
			{"cc","tt@gmail.com","dd","Female","Traveling","Canada","01-05-2000"},
			{"ee","ef@gmail.com","ff","Male","Coding","India","01-05-2000"},
				};
		
	}
	@Test (dataProvider = "giver")
	public void taker(String name1,String email1,String pass1,String gen1,String hobb1,
			String country1,String birthdate1) throws Exception
	{
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys(name1);
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(email1);
		
		WebElement pwd = driver.findElement(By.id("password"));
		pwd.sendKeys(pass1);
		
		 List<WebElement> gen = driver.findElements(By.xpath("(//*[@name='gender'])"));
		 for(WebElement g:gen)
		 {
			 if(g.getAttribute("value").equalsIgnoreCase(gen1))
			 {
				 g.click();
				 break;
				 
			 }
		 }
		
		 List<WebElement> hobb = driver.findElements(By.xpath("(//*[@name='hobbies'])"));
		
		 for(WebElement h:hobb)
		 {
			 if(h.getAttribute("value").equalsIgnoreCase(hobb1))
			 {
				 h.click();
				 break;
			 }
		 }
		 
		 WebElement country = driver.findElement(By.id("country"));
		 Select s1 = new Select(country);
		 s1.selectByVisibleText(country1);
		 
		 
		 //birthdate
		 WebElement birthdate = driver.findElement(By.id("birthdate"));
		 birthdate.sendKeys(birthdate1);
		 
		 //submit-btn
		 Thread.sleep(2000);
		 driver.findElement(By.id("submit-btn")).click();
		 
		 Thread.sleep(2000);
		//clear
		name.clear();
		email.clear();
		pwd.clear();
		//gen.clear();
		//country.clear();
		//cannot clear radio/check box
		birthdate.clear();
		
		
	}
	
	

}
