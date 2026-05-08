package SeleniumPackage;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion2 {
	WebDriver driver;
	@Test(priority = 1)
	public void testcase1()
	{

		int a= 10;
		int b=20;
		String expectedtitle = "Google";
		String actualtitle= "Google";

		Assert.assertEquals(actualtitle, expectedtitle, "Actual & Expected are not Matching..");
		//if matched then message not print.
		System.out.println("syso1 :Equals Assertion passed");




	}
	@Test(priority = 2)
	public void testCase2()
	{
		//========================================================
		String actualproj = "EBID Project";
		String expectedproj = "EBID Project";

		Assert.assertEquals(actualproj, expectedproj, "Project is Mismatched");
		System.out.println("syso2 :Equals assertion passed...");



	}
	@Test(priority = 3)
	public void testcase3()
	{
		int a=10;
		int b=20;
		Assert.assertFalse(a<b,"AssertFalse condition gets failed...");
		//as assertin failed, execution stops here
		//further line of code will not execute for same mtd.
		System.out.println("syso3 : falsecond gets false");


	}
	@Test
	public void testcase4()
	{
		//Assertio are written in Testcase3() mtd, 
		//hence scope of assertion mtd is limited to testcase3() mtd only.
		System.out.println("Hello Assertion...");
		System.out.println("new test mtd gets executed even above 3 failed.as it is new..");
	}




}
