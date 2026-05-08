package seleniumClasses;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TNGFlow {
	
	@Test
	public void a()
	{
		System.out.println("a test method");
	}
	@Test(invocationCount = 2 , priority = -1)
	public void b()
	{
		System.out.println("b test mtd ");
	
	}//47
	@BeforeMethod
	public void c()
	{
		System.out.println("before method = c");
		
	}







}
