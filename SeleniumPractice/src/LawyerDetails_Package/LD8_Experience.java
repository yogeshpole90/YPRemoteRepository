package LawyerDetails_Package;
import org.openqa.selenium.By;import org.openqa.selenium.WebElement;
public class LD8_Experience extends LD2_Login {
	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD8 - EXPERIENCE FIELD VALIDATION START");
		System.out.println("=================================================");
		WebElement ex=driver.findElement(By.id("experience"));
		log("Experience","Should be visible","true",String.valueOf(ex.isDisplayed()),ex.isDisplayed());
		log("Experience","Should be enabled","true",String.valueOf(ex.isEnabled()),ex.isEnabled());
		ex.clear();ex.sendKeys("10");Thread.sleep(200);
		log("Experience","Enter numeric '10'","10",ex.getAttribute("value"),ex.getAttribute("value").equals("10"));
		ex.clear();ex.sendKeys("abcd");Thread.sleep(200);
		String av=ex.getAttribute("value");
		log("Experience","Enter alphabets 'abcd' - should reject","Empty or rejected",av,av.isEmpty()||!av.equals("abcd"));
		ex.clear();ex.sendKeys("@#$%");Thread.sleep(200);
		String sv=ex.getAttribute("value");
		log("Experience","Enter special chars - should reject","Empty or rejected",sv,sv.isEmpty()||!sv.equals("@#$%"));
		ex.clear();ex.sendKeys("-5");Thread.sleep(200);
		log("Experience","Enter negative '-5'","Check acceptance",ex.getAttribute("value"),true);
		ex.clear();ex.sendKeys("0");Thread.sleep(200);
		log("Experience","Enter zero '0'","0",ex.getAttribute("value"),true);
		ex.clear();ex.sendKeys("99");Thread.sleep(200);
		log("Experience","Enter '99' - boundary","99",ex.getAttribute("value"),true);
		ex.clear();ex.sendKeys("10");Thread.sleep(200);
		log("Experience","Final value '10' for save","10",ex.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("LD8 - EXPERIENCE FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}

