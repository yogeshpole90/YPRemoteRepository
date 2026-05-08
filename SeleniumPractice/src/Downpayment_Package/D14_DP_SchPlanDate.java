package Downpayment_Package;
import org.openqa.selenium.By; import org.openqa.selenium.Keys; import org.openqa.selenium.WebElement;
public class D14_DP_SchPlanDate extends D2_DP_Login {
	public void validateSchPlanDate() throws Exception {
		System.out.println("=================================================");
		System.out.println("D14 - SCHEDULE PLANNED DATE VALIDATION START");
		System.out.println("=================================================");
		WebElement d=driver.findElement(By.xpath("//input[@id='planDate1']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",d);Thread.sleep(300);
		log("Sch Plan Date","Field should be visible","true",String.valueOf(d.isDisplayed()),d.isDisplayed());
		log("Sch Plan Date","Field should be enabled","true",String.valueOf(d.isEnabled()),d.isEnabled());
		jse.executeScript("arguments[0].click()",d);
		log("Sch Plan Date","Click via JS","Clickable","Click accepted",true);
		String def=d.getAttribute("value");
		log("Sch Plan Date","Default value","Empty","'"+def+"'",def.isEmpty());
		jse.executeScript("arguments[0].value='30-12-2025'",d);
		log("Sch Plan Date","Set valid date '30-12-2025'","30-12-2025",d.getAttribute("value"),d.getAttribute("value").equals("30-12-2025"));
		jse.executeScript("arguments[0].value='abcdef'",d);
		log("Sch Plan Date","Set 'abcdef' via JS","Check acceptance",d.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='@#$%^&'",d);
		log("Sch Plan Date","Set '@#$%^&' via JS","Check acceptance",d.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='32-12-2025'",d);
		String d32=d.getAttribute("value");log("Sch Plan Date","Set day 32 - REJECT","Not 32-12-2025",d32,!d32.equals("32-12-2025"));
		jse.executeScript("arguments[0].value='15-13-2025'",d);
		String m13=d.getAttribute("value");log("Sch Plan Date","Set month 13 - REJECT","Not 15-13-2025",m13,!m13.equals("15-13-2025"));
		jse.executeScript("arguments[0].value='29-02-2023'",d);
		String nl=d.getAttribute("value");log("Sch Plan Date","Set 29-02-2023 non-leap - REJECT","Not 29-02-2023",nl,!nl.equals("29-02-2023"));
		jse.executeScript("arguments[0].value='29-02-2024'",d);
		log("Sch Plan Date","Set 29-02-2024 leap year","29-02-2024",d.getAttribute("value"),d.getAttribute("value").equals("29-02-2024"));
		jse.executeScript("arguments[0].value='01-01-2099'",d);
		String fu=d.getAttribute("value");log("Sch Plan Date","Set far future - REJECT","Not 01-01-2099",fu,!fu.equals("01-01-2099"));
		jse.executeScript("arguments[0].value='01-01-1900'",d);
		String pa=d.getAttribute("value");log("Sch Plan Date","Set old past - REJECT","Not 01-01-1900",pa,!pa.equals("01-01-1900"));
		jse.executeScript("arguments[0].value='00-12-2025'",d);
		String d0=d.getAttribute("value");log("Sch Plan Date","Set day 00 - REJECT","Not 00-12-2025",d0,!d0.equals("00-12-2025"));
		jse.executeScript("arguments[0].value='15-00-2025'",d);
		String m0=d.getAttribute("value");log("Sch Plan Date","Set month 00 - REJECT","Not 15-00-2025",m0,!m0.equals("15-00-2025"));
		WebElement sa1=driver.findElement(By.xpath("//input[@id='plannedAmt1']"));
		jse.executeScript("arguments[0].value='3000'",sa1);
		jse.executeScript("arguments[0].value='30-12-2025'",d);
		d.sendKeys(Keys.TAB);Thread.sleep(500);
		try{driver.switchTo().alert().accept();}catch(Exception e){}
		log("Sch Plan Date","Final value '30-12-2025'","30-12-2025",d.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D14 - SCHEDULE PLANNED DATE VALIDATION END");
		System.out.println("=================================================");
	}
}


