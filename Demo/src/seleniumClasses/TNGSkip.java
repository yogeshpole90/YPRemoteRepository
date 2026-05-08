package seleniumClasses;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class TNGSkip {
	//test run =total annotations run 
	//total tests run =total how many times run
	//invocation count = 0 ,zero times exe = then skips
	@Test(enabled =  false)
	public void a()
	{
		System.out.println("a method");
		
	}
	@Test(invocationCount = 5)
	public void b()
	{
		System.out.println("b mtd");
	}

	@Test
	public void c()
	{
		System.out.println("below is skip exception");
		throw new SkipException("Skipped");
	//	System.out.println("skip exception used above"); //not reachable
	}
}
