package seleniumClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG2 {
	
	@Test
	public void assertCheck()
	{
		String expected = "I am Good .";
		String Actual = "I am Good";
		try//pakdo error ko
		{
		Assert.assertEquals(Actual, expected);
		}
		catch(AssertionError a)//handle karo
		{
			System.out.println(" a print "+a);
		}
		System.out.println("Both are equal");//exe if assert is passed
	}

	public static void main(String[] args) {
		System.out.println("Main Methods");//exe if use java appliaction 
		//skips when executes through TestNG Option
		
	}
}
