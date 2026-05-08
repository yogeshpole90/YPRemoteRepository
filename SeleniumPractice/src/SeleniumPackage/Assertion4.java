package SeleniumPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion4 {

	@Test
	public void testcase5()
	{

		int i = 30;
		int j =30;
		try
		{
			Assert.assertTrue(i>j);

		}
		catch(AssertionError a)
		{
			System.out.println("error a :- "+a);
		}

		System.out.println("Assertion False..");

		if(i==j)
		{
			System.out.println("Test Case is Passed.");
		}
		else
		{
			System.out.println("Test case is failed.");
		}
	
		
	}


}
