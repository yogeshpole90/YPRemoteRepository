package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DU5_DocumentName extends DU2_Login {

	public void validateDocumentName() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU5 - DOCUMENT NAME FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement docName = driver.findElement(By.id("documentName"));

		boolean isDisplayed = docName.isDisplayed();
		log("Document Name", "Field should be visible", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Document Name field should be visible");

		boolean isEnabled = docName.isEnabled();
		log("Document Name", "Field should be enabled", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Document Name field should be enabled");

		// TC3: Field should be empty initially
		String initialVal = docName.getAttribute("value");
		boolean emptyCheck = initialVal == null || initialVal.isEmpty();
		log("Document Name", "Field should be empty initially", "Empty", "'" + initialVal + "'", emptyCheck);
		sa.assertTrue(emptyCheck, "Document Name should be empty initially");

		// TC4: Enter text
		docName.clear();
		docName.sendKeys("Test Document Name");
		Thread.sleep(500);
		String enteredVal = docName.getAttribute("value");
		boolean enterCheck = enteredVal.equals("Test Document Name");
		log("Document Name", "Enter text", "Test Document Name", enteredVal, enterCheck);
		sa.assertEquals(enteredVal, "Test Document Name", "Document Name value mismatch");

		// TC5: Clear field
		docName.clear();
		Thread.sleep(500);
		String clearedVal = docName.getAttribute("value");
		boolean clearCheck = clearedVal.isEmpty();
		log("Document Name", "Clear field", "Empty", "'" + clearedVal + "'", clearCheck);
		sa.assertTrue(clearCheck, "Document Name should be empty after clear");

		// TC6: Enter special characters
		docName.clear();
		docName.sendKeys("@#$%^&*()!~");
		Thread.sleep(500);
		String splVal = docName.getAttribute("value");
		boolean splCheck = splVal.equals("@#$%^&*()!~");
		log("Document Name", "Enter special characters", "@#$%^&*()!~", splVal, splCheck);
		sa.assertEquals(splVal, "@#$%^&*()!~", "Special characters should be accepted");

		// TC7: Enter numeric value
		docName.clear();
		docName.sendKeys("1234567890");
		Thread.sleep(500);
		String numVal = docName.getAttribute("value");
		boolean numCheck = numVal.equals("1234567890");
		log("Document Name", "Enter numeric value", "1234567890", numVal, numCheck);
		sa.assertEquals(numVal, "1234567890", "Numeric value should be accepted");

		// TC8: Enter alphanumeric value
		docName.clear();
		docName.sendKeys("Doc_Upload_123");
		Thread.sleep(500);
		String alphaVal = docName.getAttribute("value");
		boolean alphaCheck = alphaVal.equals("Doc_Upload_123");
		log("Document Name", "Enter alphanumeric value", "Doc_Upload_123", alphaVal, alphaCheck);
		sa.assertEquals(alphaVal, "Doc_Upload_123", "Alphanumeric value should be accepted");

		// TC9: Re-enter valid name for save
		docName.clear();
		docName.sendKeys("Doc of Repossession");
		Thread.sleep(500);
		String finalVal = docName.getAttribute("value");
		boolean finalCheck = finalVal.equals("Doc of Repossession");
		log("Document Name", "Re-enter valid name for save", "Doc of Repossession", finalVal, finalCheck);
		sa.assertEquals(finalVal, "Doc of Repossession", "Final document name value mismatch");

		System.out.println("=================================================");
		System.out.println("DU5 - DOCUMENT NAME FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}
