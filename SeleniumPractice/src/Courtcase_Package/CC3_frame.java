package Courtcase_Package;

import org.testng.annotations.Test;

public class CC3_frame extends CC2_Login{
	
	@Test
	public void frame()
	{
		driver.switchTo().frame("courtCaseMstListPageFrame");
		
	}
	

}
