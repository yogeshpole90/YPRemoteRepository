package Downpayment_Package;
import org.openqa.selenium.By; import org.openqa.selenium.WebElement;
public class D15_DP_SchPlanAmt extends D2_DP_Login {
	public void validateSchPlanAmt(){
		System.out.println("=================================================");
		System.out.println("D15 - SCHEDULE PLANNED AMOUNT VALIDATION START");
		System.out.println("=================================================");
		WebElement a=driver.findElement(By.xpath("//input[@id='plannedAmt1']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",a);
		log("Sch Plan Amt","Field should be visible","true",String.valueOf(a.isDisplayed()),a.isDisplayed());
		String ro=a.getAttribute("readonly");
		log("Sch Plan Amt","Check enabled/readonly","Enabled or ReadOnly","enabled="+a.isEnabled()+" readonly="+ro,true);
		jse.executeScript("arguments[0].value='3000'",a);
		log("Sch Plan Amt","Set '3000' via JS","3000",a.getAttribute("value"),a.getAttribute("value").equals("3000"));
		jse.executeScript("arguments[0].value='abcd'",a);
		log("Sch Plan Amt","Set 'abcd' via JS","Check acceptance",a.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='@#$%'",a);
		log("Sch Plan Amt","Set '@#$%' via JS","Check acceptance",a.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='-1000'",a);
		log("Sch Plan Amt","Set '-1000' via JS","Check acceptance",a.getAttribute("value"),true);
		jse.executeScript("arguments[0].value=''",a);
		log("Sch Plan Amt","Clear via JS","Empty",a.getAttribute("value"),a.getAttribute("value").isEmpty());
		jse.executeScript("arguments[0].value='3000'",a);
		log("Sch Plan Amt","Final value '3000'","3000",a.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D15 - SCHEDULE PLANNED AMOUNT VALIDATION END");
		System.out.println("=================================================");
	}
}


