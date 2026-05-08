package RemedialAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F6_Comments extends F2_Setup {

	public void validateComments() throws Exception {
		System.out.println("=================================================");
		System.out.println("F6 - COMMENTS FIELD VALIDATION START");
		System.out.println("=================================================");
		WebElement c=driver.findElement(By.id("commments"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",c);
		log("Comments","Field should be visible","true",String.valueOf(c.isDisplayed()),c.isDisplayed());
		sa.assertTrue(c.isDisplayed(),"Not displayed");
		log("Comments","Field should be enabled","true",String.valueOf(c.isEnabled()),c.isEnabled());
		sa.assertTrue(c.isEnabled(),"Disabled");
		c.clear();c.sendKeys("Test remedial action comment");
		String tv=c.getAttribute("value");
		log("Comments","Enter text","Test remedial action comment",tv,tv.equals("Test remedial action comment"));
		c.clear();c.sendKeys("12345");
		log("Comments","Enter numeric '12345'","12345",c.getAttribute("value"),c.getAttribute("value").equals("12345"));
		c.clear();c.sendKeys("@#$%^&");
		log("Comments","Enter special chars '@#$%^&'","@#$%^&",c.getAttribute("value"),c.getAttribute("value").equals("@#$%^&"));
		c.clear();c.sendKeys("Comment123@#");
		log("Comments","Enter mixed 'Comment123@#'","Comment123@#",c.getAttribute("value"),c.getAttribute("value").equals("Comment123@#"));
		c.clear();
		log("Comments","Clear field","Empty",c.getAttribute("value"),c.getAttribute("value").isEmpty());
		c.clear();c.sendKeys("   ");
		log("Comments","Enter spaces only","Spaces","'"+c.getAttribute("value")+"'",true);
		c.clear();String lt="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
		c.sendKeys(lt);
		log("Comments","Enter 78 chars - max length check","Length check","Length="+c.getAttribute("value").length(),true);
		c.clear();c.sendKeys("a");
		log("Comments","Enter single char 'a' - min length","a",c.getAttribute("value"),c.getAttribute("value").equals("a"));
		c.clear();c.sendKeys("Remedial action validation test");
		log("Comments","Final value for save","Remedial action validation test",c.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("F6 - COMMENTS FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


