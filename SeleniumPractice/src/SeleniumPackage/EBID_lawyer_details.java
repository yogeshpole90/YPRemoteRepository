package SeleniumPackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EBID_lawyer_details {
	WebDriver driver ;
	JavascriptExecutor jse ;

	@BeforeClass
	public void setup() throws Exception
	{

		System.setProperty("webdriver.chrome.driver","D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);
		jse = (JavascriptExecutor) driver;

		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("infraadmin");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//burger
		Thread.sleep(800);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//Lawyer details master
		WebElement lawdt = driver.findElement(By.xpath("//*[@id='LAWERDETAILSMST']/a"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", lawdt);
		Thread.sleep(1000);
		lawdt.click();



	}

	@DataProvider
	public Object[][] giver()
	{
		return new Object[][]
				{
			{"0000000016-Yogesh pole","LAWYER1","123","DOCTORATE","10","9999999999","LAW1@G.COM"},
			{"0000000016-Yogesh pole","LAWYER2","456","GRADUATE","12","8888888888","LAW2@G.COM"},
			{"0000000016-Yogesh pole","LAWYER3","789","OTHERS","14","7777777777","LAW3@G.COM"},
				};

	}

	@Test(dataProvider = "giver")
	public void taker(String lawfirmcd1,String lawyerName1,String RefCode1,
			String qualification1,String experience1,String mobileNo11,String emailid1) throws Exception
	{



		//Add Btn
		WebElement add1 = driver.findElement(By.id("addButton"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", add1);
		add1.click();

		//lawFirmCode
		WebElement lawfirmcd = driver.findElement(By.id("lawFirmCode"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", lawfirmcd);
		Select s1 = new Select(lawfirmcd);
		s1.selectByVisibleText(lawfirmcd1);

		//lawyerName
		WebElement lawyerName = driver.findElement(By.id("lawyerName"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", lawyerName);
		lawyerName.sendKeys(lawyerName1);

		//lawyerRefCode
		driver.findElement(By.id("lawyerRefCode")).sendKeys(RefCode1);

		//qualification
		WebElement qualification = driver.findElement(By.id("qualification"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", qualification);
		Select s3 = new Select(qualification);
		s3.selectByVisibleText(qualification1);
		//jse.executeScript("arguments[0].style.backgroundColor='lightblue'", qualification);
		

		//experience
		WebElement experience = driver.findElement(By.id("experience"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", experience);
		experience.clear();
		experience.sendKeys(experience1);

		//mobileNo1
		driver.findElement(By.id("mobileNo1")).sendKeys(mobileNo11);

		//emailid
		driver.findElement(By.id("emailid")).sendKeys(emailid1);

		//ui ss
		Thread.sleep(3000);
		screnshot();

		//save
		jse.executeScript("window.scrollBy(0,1000)");
		WebElement save = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", save);

		save.click();

		//WebElement successmsg = driver.findElement(By.xpath("//*[text()='Lawyer Details Saved Successfully']"));



		//saved ss
		Thread.sleep(3000);

		screnshot();

		//backtolist
		jse.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//*[text()='Back to List']")).click();

		//search
		WebElement search = driver.findElement(By.xpath("//*[@type='search']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", search);

		search.sendKeys(RefCode1);

		//search ss
		Thread.sleep(2000);
		screnshot();

		//View
		driver.findElement(By.xpath("//*[text()='"+RefCode1+"']/parent::tr//td[3]/a")).click();

		//view ss
		Thread.sleep(2000);
		screnshot();

		//
		jse.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		driver.findElement(By.id("backButton")).click();

		//search
		WebElement search1 = driver.findElement(By.xpath("//*[@type='search']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", search1);
		search1.sendKeys(RefCode1);

		//search ss
		Thread.sleep(2000);
		screnshot();

		//Edit
		WebElement edit2 = driver.findElement(By.xpath("//*[text()='"+RefCode1+"']/parent::tr//td[4]/a"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", edit2);
		edit2.click();

		//edit ss
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,1000)");
		WebElement save3 = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", save3);
		save3.click();
		Thread.sleep(3000);
		screnshot();

		//
		jse.executeScript("window.scrollBy(0,1000)");
		WebElement back3 = driver.findElement(By.id("backButton"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", back3);

		back3.click();

		//search
		WebElement search3 = driver.findElement(By.xpath("//*[@type='search']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", search3);
		search3.sendKeys(RefCode1);


		Thread.sleep(2000);
		screnshot();


		//disable
		WebElement disable = driver.findElement(By.xpath("//*[text()='"+RefCode1+"']/parent::tr//td[5]"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", disable);

		disable.click();
		Thread.sleep(3000);
		screnshot();





	}
	public void screnshot() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots\\Lawyer_Details"+ "\\ss"+ System.currentTimeMillis() + ".png");
		FileUtils.copyFile(src, dest);
		System.out.println("Path :- " +dest.getAbsolutePath());
		System.out.println(" =========== individual Flow Completed=============.");



	}
	//@Test
	public void export() throws Exception
	{
		jse = (JavascriptExecutor) driver;

		//download in pdf
		jse.executeScript("window.scrollBy(0,1000)");
		screnshot();
		driver.findElement(By.xpath("(//*[contains(text(),'Pdf')])[1]")).click();
		Thread.sleep(3000);
		screnshot();


		//in csv
		jse.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.xpath("(//*[contains(text(),'csv')])[1]")).click();
		Thread.sleep(3000);
		screnshot();

		//in excel
		jse.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("(//*[contains(text(),'Excel')])[1]")).click();
		Thread.sleep(3000);
		screnshot();




	}
	@Test(enabled =  false)
	public void ss() throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshot"+ "\\ss_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);

		System.out.println("Path of SS Stored is :- " +dest.getAbsolutePath());
		System.out.println("Screenshot taken");






	}





}
