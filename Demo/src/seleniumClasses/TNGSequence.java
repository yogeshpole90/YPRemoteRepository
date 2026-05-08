package seleniumClasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TNGSequence {

	//main mtd not required
	@Test 
	public void a2()
	{
		System.out.println("a2 test method");
	}
	@BeforeTest
	public void b()
	{
		System.out.println("Before test method");
	}
	@BeforeSuite
	public void c()
	{
		System.out.println("BBefore suite method");
	}
	@AfterMethod
	public void d()
	{
		System.out.println("After Method Method");
	}
	@BeforeClass
	public void e()
	{
		System.out.println("BeforeClass Method");
	}
	@AfterTest
	public void f() {
		System.out.println("AfterTest method");
	}
	@AfterClass
	public void g()
	{
		System.out.println("AfterClass Method");
	}
	@BeforeMethod
	public void h()
	{
		System.out.println("before mtd");
	}
	@AfterSuite
	public void i()
	{
		System.out.println("after suite");
	}
	
	@Test
	public void a1()
	{
		System.out.println("a1 Test mtd");
	}
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
