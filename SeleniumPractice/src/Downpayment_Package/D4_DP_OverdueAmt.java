package Downpayment_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class D4_DP_OverdueAmt extends D2_DP_Login {



	public void validateOverdueAmt()
	{
		System.out.println("=================================================");
		System.out.println("D4 - OVERDUE AMOUNT VALIDATION START");
		System.out.println("=================================================");

		WebElement odAmt = driver.findElement(By.id("overdueAmount"));

		boolean isDisplayed = odAmt.isDisplayed();
		log("Overdue Amount", "Field should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Overdue Amount not displayed.");

		boolean isEnabled = odAmt.isEnabled();
		log("Overdue Amount", "Field should be enabled/editable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Overdue Amount is disabled.");

		odAmt.clear(); odAmt.sendKeys("10000");
		String numVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter numeric value '10000'", "10000", numVal, numVal.equals("10000"));
		sa.assertEquals(numVal, "10000", "Not accepting numeric.");

		odAmt.clear(); odAmt.sendKeys("abcd");
		String alphaVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter alphabets 'abcd' - should be rejected", "Empty (rejected)", alphaVal, alphaVal.isEmpty());
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");

		odAmt.clear(); odAmt.sendKeys("@#$%");
		String splVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter special chars '@#$%' - should be rejected", "Empty (rejected)", splVal, splVal.isEmpty());
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");

		odAmt.clear(); odAmt.sendKeys("abc123");
		String mixVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter alphanumeric 'abc123' - should be rejected", "Not abc123", mixVal, !mixVal.equals("abc123"));
		sa.assertNotEquals(mixVal, "abc123", "Accepting alphanumeric.");

		odAmt.clear(); odAmt.sendKeys("-5000");
		String negVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter negative '-5000' - should be rejected", "Not -5000", negVal, !negVal.equals("-5000"));
		sa.assertNotEquals(negVal, "-5000", "Accepting negative.");

		odAmt.clear(); odAmt.sendKeys("1000.50");
		String decVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter decimal '1000.50'", "1000.50", decVal, decVal.equals("1000.50"));

		odAmt.clear(); odAmt.sendKeys("   ");
		String spaceVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter spaces only - should be rejected", "Empty (rejected)", spaceVal.trim(), spaceVal.trim().isEmpty());
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");

		odAmt.clear(); odAmt.sendKeys("00500");
		String zeroVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Enter leading zeros '00500'", "00500 or 500", zeroVal, true);

		odAmt.clear();
		String emptyVal = odAmt.getAttribute("value");
		log("Overdue Amount", "Clear field - should be empty", "Empty", emptyVal, emptyVal.isEmpty());
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared.");

		odAmt.clear(); odAmt.sendKeys("10000");
		log("Overdue Amount", "Final value set '10000' for record save", "10000", odAmt.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("D4 - OVERDUE AMOUNT VALIDATION END");
		System.out.println("=================================================");
	}
}


