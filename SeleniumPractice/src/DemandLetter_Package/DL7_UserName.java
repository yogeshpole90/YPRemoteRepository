package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DL7_UserName extends DL2_Login {



	public void validateUserName() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL7 - USER NAME FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement userName = driver.findElement(By.id("userName"));

		// TC1: UserName field should be displayed
		boolean isDisplayed = userName.isDisplayed();
		log("User Name", "User Name field should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "User Name field should be visible");

		// TC2: UserName field should be enabled/editable
		boolean isEnabled = userName.isEnabled();
		log("User Name", "User Name field should be enabled/editable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "User Name field should be enabled");

		// TC3: UserName field should be empty initially
		String initialVal = userName.getAttribute("value");
		boolean emptyCheck = initialVal == null || initialVal.isEmpty();
		log("User Name", "User Name field should be empty initially", "Empty", "'" + initialVal + "'", emptyCheck);

		// TC4: Enter text in UserName field
		userName.clear();
		userName.sendKeys("Dora");
		Thread.sleep(500);
		String enteredVal = userName.getAttribute("value");
		boolean enterCheck = enteredVal.equals("Dora");
		log("User Name", "Enter text 'Dora' in User Name field", "Dora", enteredVal, enterCheck);
		sa.assertEquals(enteredVal, "Dora", "User Name value mismatch");

		// TC5: Clear and check empty
		userName.clear();
		Thread.sleep(500);
		String clearedVal = userName.getAttribute("value");
		boolean clearCheck = clearedVal.isEmpty();
		log("User Name", "Clear User Name field - should become empty", "Empty", "'" + clearedVal + "'", clearCheck);

		// TC6: Maxlength check
		String maxLen = userName.getAttribute("maxlength");
		boolean maxCheck = "60".equals(maxLen);
		log("User Name", "User Name maxlength should be 60", "60", maxLen, maxCheck);
		sa.assertEquals(maxLen, "60", "Maxlength should be 60");

		// TC7: Re-enter for save
		userName.sendKeys("Dora");
		Thread.sleep(500);

		System.out.println("=================================================");
		System.out.println("DL7 - USER NAME FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


