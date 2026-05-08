package seleniumClasses;

import org.testng.annotations.Test;

public class TNGPriority {
//zero and no priority is same =apha order execution will be done
//-1 > 0 > no priority > 1 >2 > 3
	@Test(priority = -10)
	public void a()
	{
		System.out.println("a test mtd");
	}
	@Test(priority = -20)
	public void b()
	{
		System.out.println("b test mtd");
	}
		
	@Test(priority = 0)
	public void z()
	{
		System.out.println("z test mtd");
	}
		
	@Test(priority = 2)
	public void d()
	{
		System.out.println("d test mtd");
	}
		
	@Test
	public void a3()
	{
		System.out.println("a3 test mtd");
	}
	@Test
	public void e()
	{
		System.out.println("e test mtd");
	}
		
	@Test
	public void s()
	{
		System.out.println("s test mtd");
	}
		
	@Test(priority = 1)
	public void f()
	{
		System.out.println("f test mtd");
		
	}
		
		

		
	

}
