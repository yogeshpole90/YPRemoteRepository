package dempo1;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class test2 {

	public static void main(String[] args) throws InterruptedException {
		

System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver-win64 (142)\\chromedriver-win64\\chromedriver.exe");

WebDriver driver = new ChromeDriver();

		driver.get("http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/NonCBS/clearCache");              //Clearing the cache
		Thread.sleep(1000);
		driver.get("http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/LoginPage?tid=139&lang=en");      //Entering the site
		Thread.sleep(2000);																		//Waiting for 1 second
		
		driver.manage().window().maximize();                                    //Maximize window
		Thread.sleep(500);
 
		
		driver.findElement(By.id("loginId")).sendKeys("100");              //Login ID
		Thread.sleep(500);
 
		driver.findElement(By.id("uiPwd")).sendKeys("system$123");				//Login Password
		Thread.sleep(500);
 
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(1000);
 
	    String mainWindowHandle = driver.getWindowHandle();                      //Storing main window's handle
		
		Alert alert = driver.switchTo().alert();
		alert.accept();                                                          //Clicking 'Ok' in alert notification
		Thread.sleep(4000);
		
	    Set<String> allWindowHandles = driver.getWindowHandles();    			 //Storing the handles of all the windows opened currently
	    
	    for (String windowHandle : allWindowHandles) {							
	        if (!windowHandle.equals(mainWindowHandle)) {
	            driver.switchTo().window(windowHandle);                          //Switching control to the new window
	            break;
	        }
	    }	    
	    
		Thread.sleep(3000);
		driver.manage().window().maximize();
		Thread.sleep(500);
		
		String title = driver.getTitle();
		System.out.println("Title: "+title);                                     //Displaying title of screen in console
		
		String url = driver.getCurrentUrl();
		System.out.println("URL: "+url);		                                 //Displaying URL of current screen in console
		
		driver.findElement(By.className("item-nav")).click();                    //Clicking on Burger Icon
		Thread.sleep(500);
		
		driver.findElement(By.id("CUSTOMER")).click();                           //Selection of Menu
		Thread.sleep(500);
		
		driver.findElement(By.id("addButton")).click();                          //clicking on Add Button
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 300);");                            // scroll down by 300 pixels
		
		driver.findElement(By.id("select2-customerCategory-container")).click();                              //Finding dropdown
		WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       //Finding searchbox of dropdown
		searchBox.sendKeys("1-NORMAL");																		  //Searching  for option
		searchBox.sendKeys(Keys.ENTER); 																	  //Pressing ENTER key
		Thread.sleep(500);
		
		WebElement salutation = driver.findElement(By.id("nameTitle"));          //Storing dropdown as a webelement
		Select select = new Select(salutation);								     //Creating 'select' class object of webelement as the blueprint
		select.selectByValue("1");												 //Selecting option by 'Value'
		Thread.sleep(500);
		
		driver.findElement(By.id("memberFName")).sendKeys("Dhanraj");           //-----Entering first name of member
		Thread.sleep(500);
		driver.findElement(By.id("memberLName")).sendKeys("Verma");              //-----Entering last name of member
        Thread.sleep(500);
        driver.findElement(By.id("motherFname")).sendKeys("Vaishali");           //-----Entering first name of mother
		Thread.sleep(500);
		driver.findElement(By.id("motherLname")).sendKeys("Verma");		         //-----Entering last name of mother
		Thread.sleep(500);
 
		js.executeScript("window.scrollBy(0, 500);");         					 // scroll down by 300 pixels
		Thread.sleep(500);
		
		driver.findElement(By.id("memberDOB")).sendKeys("12-06-2001");			 //Entering DOB of member
		Thread.sleep(500);
	
//		WebElement gender = driver.findElement(By.id("memberGender"));           //Selecting Gender
//		Select select1 = new Select(gender);
//		select1.selectByValue("2");
//		Thread.sleep(1000);
 
		WebElement aadhaar = driver.findElement(By.id("idType"));   	    	 //Entering the Aadhaar number
		Thread.sleep(500);
		aadhaar.sendKeys("872346221829");										 //-----Aadhaar Number
		Thread.sleep(1000);
		aadhaar.sendKeys(Keys.TAB);
		Thread.sleep(500);
		
		driver.findElement(By.id("pan")).sendKeys("AJAPS2110L");			     //-----Entering PAN number
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, 500);");         					 //scroll down by 300 pixels
        Thread.sleep(500);
		
		driver.findElement(By.id("nextBtn")).click();							 //Clicking on Next button
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, -500);");    						 // scroll up
		
		WebElement dropdown = driver.findElement(By.id("addressType"));     	 //Address Type
		Select select2 = new Select(dropdown);
		select2.selectByValue("4");
		Thread.sleep(500);
		
		driver.findElement(By.id("address1")).sendKeys("Siliguri Garden Apts."); //Address 1 field
		Thread.sleep(500);
		
		WebElement dropdown1 = driver.findElement(By.id("countryCode"));		 //Country Code dropdown
		Select select3 = new Select(dropdown1);
		select3.selectByValue("IND");
		Thread.sleep(500);
		
		driver.findElement(By.id("select2-stateCode-container")).click();                                      //State Dropdown
		WebElement searchBox1 = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       
		searchBox1.sendKeys("29");																		  
		searchBox1.sendKeys(Keys.ENTER); 																
		Thread.sleep(500);
		
		driver.findElement(By.id("select2-districtCode-container")).click();                                   //District Dropdown
		WebElement searchBox2 = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       
		searchBox2.sendKeys("BGK");																		  
		searchBox2.sendKeys(Keys.ENTER); 																
		Thread.sleep(500);	
		
		js.executeScript("window.scrollBy(0, 300);");     													   // scroll down
		Thread.sleep(500);
		
		driver.findElement(By.id("select2-area-container")).click();                                           //Sub-Division Dropdown
		WebElement searchBox3 = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       
		searchBox3.sendKeys("3");																		  
		searchBox3.sendKeys(Keys.ENTER); 																
		Thread.sleep(500);
		
		driver.findElement(By.id("select2-municipalityBlock-container")).click();                              //Block/Municipality Dropdown
		WebElement searchBox4 = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       
		searchBox4.sendKeys("1");																		  
		searchBox4.sendKeys(Keys.ENTER); 																
		Thread.sleep(500);
		
		WebElement dropdown2 = driver.findElement(By.id("ruralUrban"));		 //Rural/Urban dropdown
		Select select4 = new Select(dropdown2);
		select4.selectByValue("U");
		Thread.sleep(500);	
		
		driver.findElement(By.id("select2-urbanCode-container")).click();                                   //Urban Dropdown
		WebElement searchBox5 = driver.findElement(By.xpath("//input[@class='select2-search__field']"));       
		searchBox5.sendKeys("FCHFY");																		  
		searchBox5.sendKeys(Keys.ENTER); 																
		Thread.sleep(500);
		
		driver.findElement(By.id("btnAddBusiComm")).click();                     //Add record
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, -500);");    						 // scroll up
		Thread.sleep(500);
		
		WebElement dropdown3 = driver.findElement(By.id("addressType"));     	 //Address Type
		Select select5 = new Select(dropdown3);
		select5.selectByValue("3");
		Thread.sleep(500);
		
		WebElement dropdown4 = driver.findElement(By.id("copyAddressFrom"));     	 //Address Type
		Select select6 = new Select(dropdown4);
		select6.selectByValue("4");
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, 500);");    						 // scroll up
		Thread.sleep(500);
		
		driver.findElement(By.id("btnAddBusiComm")).click();                     //Add record
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, 500);");    						 // scroll up
		Thread.sleep(500);
		
		driver.findElement(By.id("emailId")).sendKeys("dv@gmail.com");			 //-----Email
		Thread.sleep(500);
		
		driver.findElement(By.id("mobileNo1")).sendKeys("9847202716");			 //-----Phone number
		Thread.sleep(500);
		
		driver.findElement(By.id("nextBtn")).click();
		Thread.sleep(500);
		
		WebElement dropdown5 = driver.findElement(By.id("occupation"));     	 //Occupation Dropdown
		Select select7 = new Select(dropdown5);
		select7.selectByValue("1");
		Thread.sleep(2000);		
		
		WebElement dropdown6 = driver.findElement(By.id("religion"));     	     //Religion Dropdown
		Select select8 = new Select(dropdown6);
		select8.selectByValue("1");
		Thread.sleep(1000);
		
		WebElement dropdown7 = driver.findElement(By.id("custReason"));     	 //Reason To Become Customer Dropdown
		Select select9 = new Select(dropdown7);
		select9.selectByValue("1");
		Thread.sleep(500);
		
		driver.findElement(By.id("nextBtn")).click();
		Thread.sleep(500);
		
		WebElement dropdown8 = driver.findElement(By.id("proofType"));     	 //Proof Type Dropdown
		Select select10 = new Select(dropdown8);
		select10.selectByValue("1");
		Thread.sleep(500);
		
		WebElement dropdown9 = driver.findElement(By.id("docType"));     	 //Document Type Dropdown
		Select select11 = new Select(dropdown9);
		select11.selectByValue("1");
		Thread.sleep(500);
		
		WebElement fileInput = driver.findElement(By.id("docUpload"));       //Document Upload
		fileInput.sendKeys("C:\\Users\\kaustubh.unecha\\Downloads\\download.png");
		Thread.sleep(500);
		
		driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(500);
		
		WebElement dropdown10 = driver.findElement(By.id("proofType"));     	 //Proof Type Dropdown
		Select select12 = new Select(dropdown10);
		select12.selectByValue("2");
		Thread.sleep(500);
		
		WebElement dropdown11 = driver.findElement(By.id("docType"));     	 //Document Type Dropdown
		Select select13 = new Select(dropdown11);
		select13.selectByValue("1");
		Thread.sleep(500);
		
		WebElement fileInput1 = driver.findElement(By.id("docUpload"));                     //Document Upload
		fileInput1.sendKeys("C:\\Users\\kaustubh.unecha\\Downloads\\download.png");
		Thread.sleep(500);
		
		driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(500);
		
		js.executeScript("window.scrollBy(0, 500);");    					                   // scroll down
 
		driver.findElement(By.id("saveDepositeparamDetails")).click();
		Thread.sleep(2000);
		
		WebElement yesBtn = driver.findElement(By.id("submitForm"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn);				//Yes Button
		Thread.sleep(2000);
 
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileHandler.copy(screenshot, new File("C:\\Users\\kaustubh.unecha\\Downloads\\page6.png"));	   	//-----Screenshot
 
		WebElement fileInput2 = driver.findElement(By.id("photo"));                        //Document Upload
		fileInput2.sendKeys("C:\\Users\\kaustubh.unecha\\Downloads\\download.png");
		Thread.sleep(500);
 
		WebElement fileInput3 = driver.findElement(By.id("sign"));                        //Document Upload
		fileInput3.sendKeys("C:\\Users\\kaustubh.unecha\\Downloads\\download.png");
		Thread.sleep(500);
		
		driver.findElement(By.id("validateFileSize")).click();							  //Add button
		Thread.sleep(3000);
		
		js.executeScript("window.scrollBy(0, 500);");  
		// scroll down
		
		driver.findElement(By.id("commit")).click();									  //Save Button
		Thread.sleep(2000);
		
		WebElement yesBtn1 = driver.findElement(By.id("submitForm"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn1);    //Yes Button
		Thread.sleep(4000);
	}
}
		
//		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileHandler.copy(screenshot1, new File("C:\\Users\\kaustubh.unecha\\Downloads\\page7.png"));    //-----Screenshot
//	}


