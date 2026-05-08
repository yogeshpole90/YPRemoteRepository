package seleniumClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TNGDependency {

	@Test
	public void a()
	{
		Assert.assertTrue(3>12);
		System.out.println("a mtd");
		
	}
	

	@Test(dependsOnMethods = {"a()"})
	public void b()
	{
		System.out.println("b method");
		/*
		 * mtd skips as a() mtd is failed it it is passed then only dependent b () mtd
		 * will executes and passed
		 */
	}
	
}
