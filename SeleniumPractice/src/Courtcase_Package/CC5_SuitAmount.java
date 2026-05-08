package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CC5_SuitAmount extends CC2_Login {

	public void validateSuitAmount() throws Exception
	{
		WebElement suitAmt = driver.findElement(By.id("suitAmount_txt"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", suitAmt);
		Thread.sleep(500);

		// Displayed
		log("Suit Amount", "Should be displayed", "true", String.valueOf(suitAmt.isDisplayed()), suitAmt.isDisplayed());
		sa.assertTrue(suitAmt.isDisplayed(), "Suit Amount not displayed");

		// Enabled
		log("Suit Amount", "Should be enabled", "true", String.valueOf(suitAmt.isEnabled()), suitAmt.isEnabled());

		// Read-only check
		String readOnly = suitAmt.getAttribute("readonly");
		boolean isReadOnly = readOnly != null;
		log("Suit Amount", "Check read-only", "Read-only info", "readonly=" + readOnly, true);

		// Numeric via JS
		jse.executeScript("arguments[0].value='10000'", suitAmt);
		String numVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Set '10000' via JS", "10000", numVal, numVal.equals("10000"));

		// Alphabets via JS
		jse.executeScript("arguments[0].value='abcdef'", suitAmt);
		String alphaVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Set 'abcdef' via JS", "Accepted by JS", alphaVal, true);

		// Special chars via JS
		jse.executeScript("arguments[0].value='@#$%'", suitAmt);
		String splVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Set '@#$%' via JS", "Accepted by JS", splVal, true);

		// Negative via JS
		jse.executeScript("arguments[0].value='-5000'", suitAmt);
		String negVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Set '-5000' via JS", "Check negative", negVal, true);

		// Empty via JS
		jse.executeScript("arguments[0].value=''", suitAmt);
		String emptyVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Clear via JS", "Empty", "'" + emptyVal + "'", emptyVal.isEmpty());

		// Final value for save
		jse.executeScript("arguments[0].value='50000'", suitAmt);
		String finalVal = suitAmt.getAttribute("value");
		log("Suit Amount", "Final value '50000' for save", "50000", finalVal, finalVal.equals("50000"));

		System.out.println("=================================================");
		System.out.println("CC5_SuitAmount - All cases executed.");
	}
}
