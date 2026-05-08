package LOS;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Smart_Summ {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(options);
		//WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:6660/finairoLending-1.0.1/");

		//login
		driver.findElement(By.id("loginId")).sendKeys("IO1");
		driver.findElement(By.id("uiPwd")).sendKeys("ebid#0987");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(3000);
		driver.findElement(By.id("userLogin")).click();

		//burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//sales
		driver.findElement(By.xpath("(//*[@class='dropnav'])[2]")).click();

		//gen
		driver.findElement(By.xpath("(//*[text()=' General'])[2]")).click();

		//smart summary
		driver.findElement(By.xpath("//*[@id='APPLSUMMARY']/a")).click();


		//list page

		WebDriverWait wait = new WebDriverWait(driver, 20);

		// Wait for table row to load
		WebElement lead2640 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dt-authdata']//td[normalize-space()='2763']")));


		// Double click
		Actions act = new Actions(driver);
		act.doubleClick(lead2640).build().perform();

		//link
		WebElement psrlink = driver.findElement(By.xpath("//*[contains(@href,'appId=2586')]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript(
				"var style = document.createElement('style');" +
						"style.innerHTML = '*:focus { border: 3px solid red !important; }';" +
				"document.head.appendChild(style);");

		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", psrlink);
		Thread.sleep(2000);
		psrlink.click();

		//cstmrdtl vertical menu
		WebElement cstmrdtl = driver.findElement(By.xpath("//*[text()='CUSTOMER DETAILS']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", cstmrdtl);
		Thread.sleep(1000);
		cstmrdtl.click();

		//child frame
		driver.switchTo().frame("viewAddressDetailsFrame");

		//table

		//edit
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[normalize-space()='Main Applicant']]//a[contains(@onclick,'EditData')]"))).click();

		//jse
		WebElement edit1 = driver.findElement(By.xpath("//tr[td[normalize-space()='Main Applicant']]//a[contains(@onclick,'EditData')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", edit1);
		edit1.click();

		//addressLine1C
		WebElement add1 = driver.findElement(By.id("addressLine1C"));
		add1.clear();
		add1.sendKeys("Pune ABC 1");
		//addressLine2C
		WebElement add2 = driver.findElement(By.id("addressLine2C"));
		add2.clear();
		add2.sendKeys("Pune ABC 2");
		//addressLine3C
		WebElement add3 = driver.findElement(By.id("addressLine3C"));
		add3.clear();
		add3.sendKeys("Pune ABC 3");

		//country
		WebElement count1 = driver.findElement(By.xpath("//*[contains(@id, 'select2-countryC-container')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", count1);		

		//	Country dd
		driver.findElement(By.id("select2-countryC-container")).click();
		driver.findElement(By.xpath("//*[contains(@class,'select2-search_')]")).sendKeys("Afghanistan");
		//driver.findElement(By.id("select2-countryC-results")).click();

		List<WebElement> allOptions = driver.findElements(By.xpath("//*[contains(@id,'select2-countryC')]/li"));

		for(WebElement option : allOptions)
		{
			//space also counts.
			if(option.getText().equalsIgnoreCase("Afghanistan"))
			{
				option.click();
				break;
			}
		}

		try {

			//city dd
			driver.findElement(By.id("select2-communeCodeC-container")).click();
			//driver.findElement(By.id("select2-search__field")).sendKeys("Bassar");

			// All city options 
			List<WebElement> cityList = driver.findElements(By.xpath("//*[contains(@id,'select2-communeCodeC')]/li"));

			// For loop laga ke match karo
			for (WebElement cityList1 : cityList) {

				if(cityList1.getText().equalsIgnoreCase("Bassar"))
				{
					cityList1.click();
					break;
				}
			}




		}
		catch(Exception e)
		{
			System.out.println(" City Dropdown is unable to select from Drodpown");
		}


		//  P.O.BOX/Postal code
		WebElement pobox = driver.findElement(By.xpath("//*[@name='postalCodeC']"));
		pobox.clear();
		pobox.sendKeys("444203");

		//longutudeC
		driver.findElement(By.id("longutudeC")).sendKeys("38.9N");

		//latetudeC
		driver.findElement(By.id("latetudeC")).sendKeys("48.6N");

		//
		WebElement landPhoneC = driver.findElement(By.id("landPhoneC"));
		landPhoneC.clear();
		landPhoneC.sendKeys("9158970882");

		//preferredConactC
		WebElement preferredConactC = driver.findElement(By.id("preferredConactC"));
		preferredConactC.clear();
		preferredConactC.sendKeys("9158970000");

		//faxNumberC
		WebElement faxNumberC = driver.findElement(By.id("faxNumberC"));
		faxNumberC.clear();
		faxNumberC.sendKeys("25685623");
		//emailC
		try {
			WebElement emailC = driver.findElement(By.id("emailC"));
			emailC.clear();
			emailC.sendKeys("ypole.afg@iocl.com");//iocl.india@iocl.com
		}
		catch(Exception f)
		{
			System.out.println("Entered Email is 'ypole.afg@iocl.com' ");
			System.out.println(" Exception is : "+ f);
		}
		//websiteC
		WebElement websiteC = driver.findElement(By.id("websiteC"));
		websiteC.clear();
		websiteC.sendKeys("kiyaAi.com");


		//saveAddr
		driver.findElement(By.id("saveAddr")).click();


		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		} catch (Exception e) {
			System.out.println("No alert present");
		}


		//======================================================================================================////
		//2. Stakeholder details
		//======================================================================================================////

		//parent frame
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//*[@data-taskid='62']")).click();

		//child frame
		driver.switchTo().frame("viewBasicDetailsFrame");
		WebElement edit2 = driver.findElement(By.xpath("//td[normalize-space()='Indian Oil Corporation Ltd']/parent::tr//a[contains(@onclick,'EditData')]"));		
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", edit2);
		Thread.sleep(1000);
		edit2.click();


		//stakeholder category
		Thread.sleep(3000);
		driver.findElement(By.id("select2-custCategory-container")).click();

		driver.findElement(By.xpath("(//*[@class='select2-search__field'])[4]")).sendKeys("Bank");

		List<WebElement> stakeholderlist = driver.findElements(By.xpath("//*[contains(@id , 'select2-custCategory-result')]"));

		for(WebElement stakeholderlist1 : stakeholderlist)
		{
			if(stakeholderlist1.getText().equalsIgnoreCase("Bank"))
			{
				stakeholderlist1.click();
			}
		}

		//country name
		driver.findElement(By.id("select2-bankOrCompany-container")).click();

		driver.findElement(By.xpath("(//*[contains(@class, 'select2-search__field')])[4]")).sendKeys("New Bank");

		//
		List<WebElement> countlist = driver.findElements(By.xpath("(//*[contains(@id, 'select2-countryName')])"));

		for(WebElement countlist1:countlist)
		{
			if(countlist1.getText().equalsIgnoreCase("New Bank"))

			{
				countlist1.click();
			}
		}

		//companyName
		WebElement companyName = driver.findElement(By.id("companyName"));
		companyName.clear();
		companyName.sendKeys("Test Indian Oil Corporation Ltd");

		//acronymOfStructure
		WebElement acronym = driver.findElement(By.id("acronymOfStructure"));
		acronym.clear();
		acronym.sendKeys("Test IOCL");

		//dateOfCreationOfStructure
		WebElement dateOfCreatOfStr = driver.findElement(By.id("dateOfCreationOfStructure"));
		dateOfCreatOfStr.clear();
		dateOfCreatOfStr.sendKeys("10-10-2010");

		//
		List<WebElement> countOfEstb = driver.findElements(By.xpath("//*[contains(@id, 'select2-country')]/li"));

		for(WebElement countOfEstb1:countOfEstb)
		{
			if(countOfEstb1.getText().equalsIgnoreCase("Afghanistan"))

			{
				countOfEstb1.click();
			}
		}

		driver.findElement(By.id("select2-country-container")).click();
		WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
		searchBox.sendKeys("Brazil");
		searchBox.sendKeys(Keys.ENTER);

		//select2-authorizedCurrency-container
		driver.findElement(By.id("select2-authorizedCurrency-container")).click();

		//
		driver.findElement(By.xpath("(//*[contains(@class, 'select2-search__field')])[4]")).sendKeys("us");

		//
		List<WebElement> currlist = driver.findElements(By.xpath("(//*[contains(@id, 'select2-authorizedCurrency')])"));

		for(WebElement currlist1:currlist)
		{
			if(currlist1.getText().equalsIgnoreCase("US Dollar"))
			{
				currlist1.click();

			}
		}

		//autorizedCapital_txt
		WebElement Authcap = driver.findElement(By.id("autorizedCapital_txt"));
		Authcap.clear();
		Authcap.sendKeys("900000");

		//noOfBranches
		WebElement noOfBran = driver.findElement(By.id("noOfBranches"));
		noOfBran.clear();
		noOfBran.sendKeys("10");

		//
		driver.findElement(By.xpath("(//*[contains(@id, 'select2-proofOfIdentityNonInd')])")).click();

		driver.findElement(By.xpath("(//*[contains(@class, 'select2-search__field')])[4]")).sendKeys("nat");

		List<WebElement> proofidList = driver.findElements(By.xpath("(//*[contains(@role, 'treeitem')])"));

		for(WebElement proofidList1 : proofidList)
		{
			if(proofidList1.getText().equalsIgnoreCase("National Identity Card"))
			{
				proofidList1.click();

			}
		}

		//identityNoNonInd
		WebElement idno = driver.findElement(By.id("identityNoNonInd"));
		idno.clear();
		idno.sendKeys("idno");
		
		//
		driver.findElement(By.id("select2-nationalityOfInterventionNonInd-container")).click();
		
		driver.findElement(By.xpath("(//*[contains(@class, 'select2-search__field')])[4]")).sendKeys("afg");
		
		List<WebElement> listofnationality = driver.findElements(By.xpath("(//*[contains(@role, 'tree')])"));
		for(WebElement listofnationality1 : listofnationality)
		{
			if(listofnationality1.getText().equalsIgnoreCase("Afghanistan"))
			{
				listofnationality1.click();
			}
		}
		
		//
		driver.findElement(By.xpath("(//*[contains(@class, 'select2-selection--multiple')])[2]")).click();
		
		List<WebElement> listofIntervention = driver.findElements(By.xpath("(//*[contains(@id, 'countryOfInterventionNonInd')])"));
		
		for(WebElement listofIntervention1:listofIntervention)
		{
			if(listofIntervention1.getText().equalsIgnoreCase("Afghanistan"))
			{
				listofIntervention1.click();
			}
		}
		
		//servicesOfferedNonInd
		WebElement serviceoff = driver.findElement(By.id("servicesOfferedNonInd"));
		serviceoff.clear();
		serviceoff.sendKeys("Test");
		
		//
		driver.findElement(By.xpath("(//*[contains(@aria-owns, 'sectorOfActivity')])")).click();
		
		List<WebElement> sectorOfAct = driver.findElements(By.id("select2-sectorOfActivity-results"));
		
		for(WebElement sectorOfAct1 : sectorOfAct)
		{
			if(sectorOfAct1.getText().equalsIgnoreCase("AGRICULTURE"))
			{
				sectorOfAct1.click();
			}
		}
		
		//specialInformation
		WebElement splinfo = driver.findElement(By.id("specialInformation"));
		splinfo.clear();
		splinfo.sendKeys("Test 1");
		
		//remarks
		WebElement remark = driver.findElement(By.id("remarks"));
		remark.clear();
		remark.sendKeys("Tested on 27-02");
		
		//table
		WebElement edit = driver.findElement(By.xpath("//*[@data-toggle='modal']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", edit);
		
		
		//
		driver.findElement(By.xpath("//*[contains(@id,'-ratingCompany')]")).click();
		driver.findElement(By.xpath("(//*[contains(@class,'select2-search__field')])[4]")).sendKeys("fit");
		
		List<WebElement> listofratings = driver.findElements(By.xpath("(//*[contains(@id,'-ratingCompany-results')])/li"));
		
		for(WebElement listofratings1:listofratings)
		{
			if(listofratings1.getText().equalsIgnoreCase("Fitch Ratings"))
			{
				listofratings1.click();
			}
		}
		
		//updateCountrybt
		driver.findElement(By.id("updateCountrybt")).click();
		
		driver.findElement(By.id("saveBasic")).click();


























	}

}
