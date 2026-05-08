package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CC5C_FilingNumber extends CC2_Login {

	private void dismissAlert() {
		try { driver.switchTo().alert().accept(); } catch (Exception e) { }
	}

	public void validateFilingNumber() throws Exception
	{
		WebElement fn = driver.findElement(By.id("filingNumber"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fn);
		Thread.sleep(500);

		log("Filing Number", "Displayed", "true", String.valueOf(fn.isDisplayed()), fn.isDisplayed());
		sa.assertTrue(fn.isDisplayed(), "Filing Number not displayed");

		log("Filing Number", "Enabled", "true", String.valueOf(fn.isEnabled()), fn.isEnabled());
		sa.assertTrue(fn.isEnabled(), "Filing Number disabled");

		String ml = fn.getAttribute("maxlength");
		log("Filing Number", "Maxlength", "60", ml, "60".equals(ml));

		fn.clear(); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Empty initially", "Empty", "'" + fn.getAttribute("value") + "'", fn.getAttribute("value").isEmpty());

		fn.clear(); dismissAlert(); fn.sendKeys("FIL12345"); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Enter alphanumeric 'FIL12345'", "FIL12345", fn.getAttribute("value"), fn.getAttribute("value").equals("FIL12345"));

		fn.clear(); dismissAlert(); fn.sendKeys("999888"); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Enter numeric '999888'", "999888", fn.getAttribute("value"), fn.getAttribute("value").equals("999888"));

		fn.clear(); dismissAlert(); fn.sendKeys("ABCDEF"); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Enter alphabets 'ABCDEF'", "ABCDEF", fn.getAttribute("value"), fn.getAttribute("value").equals("ABCDEF"));

		fn.clear(); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Clear field", "Empty", "'" + fn.getAttribute("value") + "'", fn.getAttribute("value").isEmpty());

		fn.clear(); dismissAlert(); fn.sendKeys("CC2025FIL001"); dismissAlert(); Thread.sleep(200);
		log("Filing Number", "Final value 'CC2025FIL001'", "CC2025FIL001", fn.getAttribute("value"), fn.getAttribute("value").equals("CC2025FIL001"));

		System.out.println("=================================================");
		System.out.println("CC5C_FilingNumber - All cases executed.");
	}
}
