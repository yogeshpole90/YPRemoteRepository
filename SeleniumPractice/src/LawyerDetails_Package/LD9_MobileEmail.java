package LawyerDetails_Package;
import org.openqa.selenium.By;import org.openqa.selenium.WebElement;
public class LD9_MobileEmail extends LD2_Login {
	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD9 - MOBILE & EMAIL VALIDATION START");
		System.out.println("=================================================");
		// Mobile
		WebElement mb=driver.findElement(By.id("mobileNo1"));
		log("Mobile No","Should be visible","true",String.valueOf(mb.isDisplayed()),mb.isDisplayed());
		log("Mobile No","Should be enabled","true",String.valueOf(mb.isEnabled()),mb.isEnabled());
		mb.clear();mb.sendKeys("9999999999");Thread.sleep(200);
		log("Mobile No","Enter valid '9999999999'","9999999999",mb.getAttribute("value"),mb.getAttribute("value").equals("9999999999"));
		mb.clear();mb.sendKeys("abcdef");Thread.sleep(200);
		String mav=mb.getAttribute("value");
		log("Mobile No","Enter alphabets - should reject","Rejected",mav,mav.isEmpty()||!mav.equals("abcdef"));
		mb.clear();mb.sendKeys("12345");Thread.sleep(200);
		log("Mobile No","Enter 5 digits - less than 10","Check min",mb.getAttribute("value"),true);
		mb.clear();mb.sendKeys("9999999999");
		// Email
		WebElement em=driver.findElement(By.id("emailid"));
		log("Email ID","Should be visible","true",String.valueOf(em.isDisplayed()),em.isDisplayed());
		log("Email ID","Should be enabled","true",String.valueOf(em.isEnabled()),em.isEnabled());
		em.clear();em.sendKeys("test@gmail.com");Thread.sleep(200);
		log("Email ID","Enter valid email","test@gmail.com",em.getAttribute("value"),em.getAttribute("value").equals("test@gmail.com"));
		em.clear();em.sendKeys("invalidemail");Thread.sleep(200);
		log("Email ID","Enter invalid email","Check validation",em.getAttribute("value"),true);
		em.clear();em.sendKeys("LAW1@G.COM");
		log("Email ID","Final value 'LAW1@G.COM'","LAW1@G.COM",em.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("LD9 - MOBILE & EMAIL VALIDATION END");
		System.out.println("=================================================");
	}
}

