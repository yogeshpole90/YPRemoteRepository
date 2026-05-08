package SeleniumPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion3 {

	@Test
	public void testcase5()
	{
		String expectedtitle ="Goooooogle";
		String actualtitle = "Google";

		try 
		{
			Assert.assertEquals(actualtitle, expectedtitle, "Actual & Expected are not Matching..");

		}
		catch (AssertionError e) 
		{
			System.out.println("Assertion Error is :- " + e); //only error description
			e.printStackTrace();//print error // complete error description

		}
		System.out.println("we are checking Equal Assertion...");


	}

}
