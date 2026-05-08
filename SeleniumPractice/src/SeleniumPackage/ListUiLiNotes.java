package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ListUiLiNotes {

	public static void main(String[] args) {
		
		//calender
		//click on End date 
		/*
		 * WebElement enddate =
		 * driver.findElement(By.xpath("//input[contains(@placeholder ,'End date')]"));
		 * enddate.click(); enddate.sendKeys(Keys.CONTROL+"a");
		 * enddate.sendKeys(Keys.DELETE); enddate.sendKeys("2026-01-21 12:30");
		 * 
		 * enddate.sendKeys(Keys.TAB);
		 */
		// TODO Auto-generated method stub
		/*Handle Lis > click on drodown > create list > click on element
		 * 
		 / alternate simple: 1.click dropdown
		  * 2. use xpath/text and locate
		  * 3.   click = driver.findElement(By.xpath("(//span[text()='Next Court Case'])[2]")).click();
		 *
		 * sample code
		 * 
		 * driver.findElement(By.id("dropdown")).click(); ✔ Step 2: All <li> fetch karo
		 * java Copy code List<WebElement> options =
		 * driver.findElements(By.xpath("//ul/li")); ✔ Step 3: Required option select
		 * karo java Copy code for(WebElement opt : options) {
		 * if(opt.getText().equals("India")) { opt.click(); break; } }
		 */

	}

}
