package SeleniumPackage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomerCreation {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/LoginPage?tid=139&lang=en");
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='loginId']")).sendKeys("SM969");
		driver.findElement(By.xpath("//input[@id='uiPwd']")).sendKeys("system$123");
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='userLogin']")).click();
		
		Thread.sleep(4000);
		Set<String> allhandles = driver.getWindowHandles();
		System.out.println(allhandles);
		allhandles.size();
		//window = it + next
		//iterator 
		Iterator<String> it = allhandles.iterator();//iterator/set gives unique
		String window1 = it.next();//next() current value + pointer on next
		String window2 = it.next();//indexing not use in iterator

		System.out.println(window1);
		System.out.println(window2);

		Thread.sleep(2000);
		/*
		 * driver.switchTo().window(window1); System.out.println(window1);
		 */
		Thread.sleep(4000);
		driver.switchTo().window(window2);
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@id='CUSTOMER']/a")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='addButton']")).click();
		
		//dropdown
		Thread.sleep(2000);
		  WebElement cate = driver.findElement(By.xpath("//select[@id='customerCategory']"));
		  //store all option in on WE
		  Select drop = new Select(cate);
		  drop.selectByIndex(7);//8-student
		  
		  Thread.sleep(1000);
		  WebElement salu = driver.findElement(By.xpath("//select[@id='nameTitle']"));
		  Select salu1 = new Select(salu);
		  salu1.selectByIndex(1);
		  
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//input[@id='memberFName']")).sendKeys("Selenium");
		  driver.findElement(By.xpath("//input[@id='memberMName']")).sendKeys("Yogesh");
		  driver.findElement(By.xpath("//input[@id='memberLName']")).sendKeys("Pole");

		  //mother 
		  driver.findElement(By.xpath("//input[@id='motherFname']")).sendKeys("Selenium");
		  driver.findElement(By.xpath("//input[@id='motherMname']")).sendKeys("Test");
		  driver.findElement(By.xpath("//input[@id='motherLname']")).sendKeys("Test");

		WebElement dob = driver.findElement(By.xpath("//input[@id='memberDOB']"));
		  dob.sendKeys("15-05-2000");
		  Thread.sleep(2000);
		  dob.sendKeys(Keys.ENTER);
		  
			/*//gender = autofill / disable
			 * WebElement gen = driver.findElement(By.xpath("//input[@id='memberGender']"));
			 * 
			 * Select gen1 = new Select(gen); gen1.selectByIndex(1);
			 */
		  
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//input[@id='idType']")).sendKeys("876500008765");
		  driver.findElement(By.xpath("//input[@id='pan']")).sendKeys("ASDPP6767Y");
		  
		  //next button
		 WebElement next = driver.findElement(By.xpath("//button[@id='nextBtn']"));
		  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  jse.executeScript("arguments[0].scrollIntoView(true)", next);
		  next.click();
		  
		  
		  //address
		  Thread.sleep(5000);
		 WebElement add = driver.findElement(By.xpath("//select[@id='addressType']"));
		  jse.executeScript("arguments[0].scrollIntoView(true)", add);
		  
		  Select add1 = new Select(add);
		  add1.selectByIndex(3);
		  
		  driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("add1");

		  driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("add2");

		  driver.findElement(By.xpath("//input[@id='address3']")).sendKeys("add3");
		  
		  //country code
		 WebElement country = driver.findElement(By.xpath("//select[@id='countryCode']"));
		  Select country1 = new Select(country);
		  country1.selectByVisibleText("BAH-BAHAMAS");
		  
		  
		 WebElement state = driver.findElement(By.xpath("//select[@id='stateCode']"));
		  Select state1 = new Select(state);
		  state1.selectByVisibleText("01-NEW PROVIDENCE");
		  
			 WebElement district = driver.findElement(By.xpath("//select[@id='districtCode']"));
			  Select district1 = new Select(district);
			  district1.selectByVisibleText("CEN-CENTRAL NEW PROVIDENCE");
			  
			  
				
				  WebElement subdiv = driver.findElement(By.xpath("//select[@id='area']"));
				  Select subdiv1 = new Select(subdiv); 
				  subdiv1.selectByVisibleText("1-NASSAU") ;
				 
				
				  WebElement municipality = driver.findElement(By.xpath("//select[@id='municipalityBlock']")); Select
				  municipality1 = new Select(municipality); 
				  municipality1.selectByVisibleText("A-MONTAGUE");
				 
		  
			  WebElement rural = driver.findElement(By.xpath("//select[@id='ruralUrban']"));
			  Select rural1 = new Select(rural);
			  rural1.selectByVisibleText("R-RURAL");
			  

			  WebElement vill = driver.findElement(By.xpath("//select[@id='villCode']"));
			  Select vill1 = new Select(vill);
			  vill1.selectByVisibleText("01-EAST");
			  
			//  driver.findElement(By.xpath("//input[@id='pinCode']")).sendKeys("123123");
			  Thread.sleep(3000);
		  driver.findElement(By.id("btnAddBusiComm")).click();
		  
		  
		Thread.sleep(2000);  
		driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("selenium@g.com");
		driver.findElement(By.xpath("//input[@id='mobileNo1']")).sendKeys("9876000098");
		
		WebElement next2 = driver.findElement(By.xpath("//button[@id='nextBtn']"));
		jse.executeScript("arguments[0].scrollIntoView(true)", next2);
		next2.click();

		
		//new page
		WebElement occu = driver.findElement(By.xpath("//select[@id='occupation']"));
		jse.executeScript("arguments[0].scrollIntoView(true)", occu);
		
		Select occu1 = new Select(occu);
		occu1.selectByVisibleText("1-BUSINESS");
		
		
		
		WebElement reli = driver.findElement(By.id("religion"));
		jse.executeScript("arguments[0].scrollIntoView(true)", reli);

		Select reli1 = new Select(reli);
		reli1.selectByVisibleText("1-HINDU");
		
		WebElement custReason = driver.findElement(By.id("custReason"));
		jse.executeScript("arguments[0].scrollIntoView(true)", custReason);

		Select custReason1 = new Select(custReason);
		custReason1.selectByVisibleText("1-APPROACHED BY BANK");
		
		
		//next button
		driver.findElement(By.id("nextBtn")).click();
		
		
		//new page
		Thread.sleep(4000);
		WebElement proof = driver.findElement(By.id("proofType"));
		Select proof1 = new Select(proof);
		proof1.selectByVisibleText("1-ADDRESS PROOF");
		
		WebElement docType1 = driver.findElement(By.id("docType"));
		Select docType2 = new Select(docType1);
		docType2.selectByVisibleText("1-ADHAAR CARD");
		
		 driver.findElement(By.id("docUpload")).sendKeys("C:\\Users\\Yogesh.Pole\\Music\\images (3).jpg");

		 driver.findElement(By.id("btnAdd")).click();
		 Thread.sleep(3000);
		
		 //identity
			jse.executeScript("arguments[0].scrollIntoView(true)", proof);
			proof1.selectByVisibleText("2-IDENTITY PROOF");
			
		 docType2.selectByVisibleText("1-AADHAAR");

			
			WebElement doc2 = driver.findElement(By.id("docUpload"));
			jse.executeScript("arguments[0].scrollIntoView(true)", doc2);
			doc2.sendKeys("C:\\Users\\Yogesh.Pole\\Music\\images (3).jpg");

			 driver.findElement(By.id("btnAdd")).click();
			 
			 Thread.sleep(2000);
			 
			 //save
			WebElement save = driver.findElement(By.id("saveDepositeparamDetails"));
			jse.executeScript("arguments[0].scrollIntoView(true)", save);
			save.click();

			//Confirmation - YES
		driver.findElement(By.xpath("//a[@id='submitForm']")).click();	 
		
		
		
		
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  

		
		  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		


	}

}
