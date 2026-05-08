package testCase;

import org.testng.annotations.Test;

import pageObjects.NavigationPage;
import testBase.BaseClass;

public class TC002_NavigationTest extends BaseClass{

	
	@Test
	public void verifyNavigation()
	{
		try 
		{
			logger.info("======TC002-Navigation Verification Started==========");
			logger.info("Driver is: " + driver);
			NavigationPage navPage = new NavigationPage(driver);
			navPage.clickBurger();
			navPage.clickAllCaseList();
			navPage.search(p.getProperty("casenumber"));
			navPage.clickCase();
		}
		catch(Exception e)
		{
			logger.info("Exception > " + e);
			logger.debug("Debug Logs stored.");
		}


	}

}
