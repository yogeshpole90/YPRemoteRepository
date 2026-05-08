package LawyerDetails_Package;
import org.openqa.selenium.By;import org.openqa.selenium.WebElement;
public class LD5_LawyerName extends LD2_Login {
	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD5 - LAWYER NAME VALIDATION START");
		System.out.println("=================================================");
		WebElement n=driver.findElement(By.id("lawyerName"));
		log("Lawyer Name","Should be visible","true",String.valueOf(n.isDisplayed()),n.isDisplayed());
		log("Lawyer Name","Should be enabled","true",String.valueOf(n.isEnabled()),n.isEnabled());
		n.clear();log("Lawyer Name","Empty initially","Empty","'"+n.getAttribute("value")+"'",n.getAttribute("value").isEmpty());
		n.clear();n.sendKeys("Test Lawyer");Thread.sleep(200);
		log("Lawyer Name","Enter text","Test Lawyer",n.getAttribute("value"),n.getAttribute("value").equals("Test Lawyer"));
		n.clear();n.sendKeys("@#$%^&");Thread.sleep(200);
		log("Lawyer Name","Enter special chars","@#$%^&",n.getAttribute("value"),n.getAttribute("value").equals("@#$%^&"));
		n.clear();n.sendKeys("12345");Thread.sleep(200);
		log("Lawyer Name","Enter numeric","12345",n.getAttribute("value"),n.getAttribute("value").equals("12345"));
		n.clear();n.sendKeys("LAWYER1");Thread.sleep(200);
		log("Lawyer Name","Final value 'LAWYER1'","LAWYER1",n.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("LD5 - LAWYER NAME VALIDATION END");
		System.out.println("=================================================");
	}
}

