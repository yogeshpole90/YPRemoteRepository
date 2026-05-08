package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * A6_Numeric - Numeric Field Validation for 'Police Action Amount'
 * 
 * Validates:
 * 1. Displayed          2. Enabled           3. Accepts Numeric
 * 4. Rejects Alphabets  5. Rejects Alphanumeric
 * 6. Rejects Special Chars  7. Max Length Check
 * 8. Negative Number    9. Decimal Value     10. Empty/Blank
 * 11. Spaces Only       12. Leading Zeros
 */
public class A6_ActAmt extends A1_LoginSetup {

	public void numeric()
	{
		WebElement amtField = driver.findElement(By.id("policeActionAmount"));

		// Case 1: Field is Displayed
		System.out.println("============================================");
		sa.assertTrue(amtField.isDisplayed(), "Amount field not displayed.");
		System.out.println("Case 1 : Field is Displayed.");

		// Case 2: Field is Enabled
		System.out.println("============================================");
		sa.assertTrue(amtField.isEnabled(), "Amount field is disabled.");
		System.out.println("Case 2 : Field is Enabled.");

		// Case 3: Accepts Numeric Value
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("120");
		String numVal = amtField.getAttribute("value");
		sa.assertEquals(numVal, "120", "Not accepting numeric value.");
		System.out.println("Case 3 : Accepts Numeric → " + numVal);

		// Case 4: Rejects Alphabets
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("abcd");
		String alphaVal = amtField.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets in numeric field.");
		System.out.println("Case 4 : Alphabets rejected → value: '" + alphaVal + "'");

		// Case 5: Rejects Alphanumeric Mix
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("abcd1234");
		String alphaNumVal = amtField.getAttribute("value");
		sa.assertNotEquals(alphaNumVal, "abcd1234", "Accepting alphanumeric mix.");
		System.out.println("Case 5 : Alphanumeric rejected → value: '" + alphaNumVal + "'");

		// Case 6: Rejects Special Characters
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("@#$%^&");
		String splVal = amtField.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special characters.");
		System.out.println("Case 6 : Special chars rejected → value: '" + splVal + "'");

		// Case 7: Max Length Validation
		System.out.println("============================================");
		String maxLenAttr = amtField.getAttribute("maxlength");
		if (maxLenAttr != null) {
			int maxLen = Integer.parseInt(maxLenAttr);
			sa.assertTrue(maxLen <= 60, "Max length exceeds 60.");
			System.out.println("Case 7 : Max Length → " + maxLen);
		} else {
			System.out.println("Case 7 : No maxlength attribute found.");
		}

		// Case 8: Negative Number
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("-500");
		String negVal = amtField.getAttribute("value");
		sa.assertNotEquals(negVal, "-500", "Accepting negative number.");
		System.out.println("Case 8 : Negative number → value: '" + negVal + "'");

		// Case 9: Decimal Value
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("100.50");
		String decVal = amtField.getAttribute("value");
		System.out.println("Case 9 : Decimal value → value: '" + decVal + "'");

		// Case 10: Empty/Blank Field
		System.out.println("============================================");
		amtField.clear();
		String emptyVal = amtField.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared properly.");
		System.out.println("Case 10 : Empty field → value: '" + emptyVal + "'");

		// Case 11: Spaces Only
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("   ");
		String spaceVal = amtField.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("Case 11 : Spaces only → value: '" + spaceVal + "'");

		// Case 12: Leading Zeros
		System.out.println("============================================");
		amtField.clear();
		amtField.sendKeys("00123");
		String leadZero = amtField.getAttribute("value");
		System.out.println("Case 12 : Leading zeros → value: '" + leadZero + "'");

		System.out.println("============================================");
		System.out.println("A6_ActAmt - All cases executed.");
	}

}
