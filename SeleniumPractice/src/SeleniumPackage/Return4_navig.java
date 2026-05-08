package SeleniumPackage;

import org.openqa.selenium.By;

public class Return4_navig extends Return3_setup  {
	
	
	public void navigation()
	{
		//Burger Icon
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		
		//All Cases List 
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();
		
		
	}
	
	

}
