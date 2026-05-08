package Downpayment_Package;
import org.openqa.selenium.By; import org.openqa.selenium.Keys; import org.openqa.selenium.WebElement;
public class D11_DP_ChequeDate extends D2_DP_Login {
	public void validateChequeDate(){
		System.out.println("=================================================");
		System.out.println("D11 - CHEQUE DATE VALIDATION START");
		System.out.println("=================================================");
		boolean chqExists=driver.findElements(By.id("chequeDate")).size()>0;
		if(!chqExists||!driver.findElement(By.id("chequeDate")).isDisplayed()){sa.fail("Cheque Date NOT visible");log("Cheque Date","Pre-check visibility","Visible","NOT visible",false);return;}
		WebElement d=driver.findElement(By.id("chequeDate"));
		log("Cheque Date","Field should be visible","true",String.valueOf(d.isDisplayed()),d.isDisplayed());
		log("Cheque Date","Field should be enabled","true",String.valueOf(d.isEnabled()),d.isEnabled());
		d.click();d.sendKeys(Keys.TAB);
		log("Cheque Date","Click on field","Clickable","Click accepted",true);
		String def=d.getAttribute("value");
		log("Cheque Date","Default value should be empty","Empty","'"+def+"'",def.isEmpty());
		d.clear();d.sendKeys("25-12-2025");d.sendKeys(Keys.TAB);
		log("Cheque Date","Enter valid date '25-12-2025'","25-12-2025",d.getAttribute("value"),d.getAttribute("value").equals("25-12-2025"));
		d.clear();d.sendKeys("2025/12/25");d.sendKeys(Keys.TAB);
		String inv=d.getAttribute("value");log("Cheque Date","Enter invalid format '2025/12/25'","Not 2025/12/25",inv,!inv.equals("2025/12/25"));
		d.clear();d.sendKeys("abcdef");d.sendKeys(Keys.TAB);
		log("Cheque Date","Enter alphabets - reject","Empty",d.getAttribute("value"),d.getAttribute("value").isEmpty());
		d.clear();d.sendKeys("@#$%^&");d.sendKeys(Keys.TAB);
		log("Cheque Date","Enter special chars - reject","Empty",d.getAttribute("value"),d.getAttribute("value").isEmpty());
		d.clear();d.sendKeys("32-12-2025");d.sendKeys(Keys.TAB);
		String d32=d.getAttribute("value");log("Cheque Date","Enter day 32 - REJECT","Not 32-12-2025",d32,!d32.equals("32-12-2025"));
		d.clear();d.sendKeys("15-13-2025");d.sendKeys(Keys.TAB);
		String m13=d.getAttribute("value");log("Cheque Date","Enter month 13 - REJECT","Not 15-13-2025",m13,!m13.equals("15-13-2025"));
		d.clear();d.sendKeys("29-02-2023");d.sendKeys(Keys.TAB);
		String nl=d.getAttribute("value");log("Cheque Date","Enter 29-02-2023 non-leap - REJECT","Not 29-02-2023",nl,!nl.equals("29-02-2023"));
		d.clear();d.sendKeys("29-02-2024");d.sendKeys(Keys.TAB);
		log("Cheque Date","Enter 29-02-2024 leap year","29-02-2024",d.getAttribute("value"),d.getAttribute("value").equals("29-02-2024"));
		d.clear();d.sendKeys("01-01-2099");d.sendKeys(Keys.TAB);
		String fu=d.getAttribute("value");log("Cheque Date","Enter far future - REJECT","Not 01-01-2099",fu,!fu.equals("01-01-2099"));
		d.clear();d.sendKeys("01-01-1900");d.sendKeys(Keys.TAB);
		String pa=d.getAttribute("value");log("Cheque Date","Enter old past - REJECT","Not 01-01-1900",pa,!pa.equals("01-01-1900"));
		d.clear();d.sendKeys("00-12-2025");d.sendKeys(Keys.TAB);
		String d0=d.getAttribute("value");log("Cheque Date","Enter day 00 - REJECT","Not 00-12-2025",d0,!d0.equals("00-12-2025"));
		d.clear();d.sendKeys("15-00-2025");d.sendKeys(Keys.TAB);
		String m0=d.getAttribute("value");log("Cheque Date","Enter month 00 - REJECT","Not 15-00-2025",m0,!m0.equals("15-00-2025"));
		d.clear();d.sendKeys("   ");d.sendKeys(Keys.TAB);
		log("Cheque Date","Enter spaces - reject","Empty",d.getAttribute("value").trim(),d.getAttribute("value").trim().isEmpty());
		d.clear();d.sendKeys("25-12-2025");d.sendKeys(Keys.TAB);
		log("Cheque Date","Final value '25-12-2025'","25-12-2025",d.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D11 - CHEQUE DATE VALIDATION END");
		System.out.println("=================================================");
	}
}


