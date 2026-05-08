package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DU6_FileUpload extends DU2_Login {

	public void validateFileUpload() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU6 - FILE UPLOAD FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement uploadField = driver.findElement(By.id("documentData"));

		boolean isDisplayed = uploadField.isDisplayed();
		log("File Upload", "Field should be visible", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "File upload field should be visible");

		boolean isEnabled = uploadField.isEnabled();
		log("File Upload", "Field should be enabled", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "File upload field should be enabled");

		String inputType = uploadField.getAttribute("type");
		boolean typeCheck = inputType.equalsIgnoreCase("file");
		log("File Upload", "Input type should be 'file'", "file", inputType, typeCheck);
		sa.assertEquals(inputType.toLowerCase(), "file", "Input type should be file");

		String filePath = "C:\\Users\\Yogesh.Pole\\Music\\COLLATERAL_SEIZED_LETTER.pdf";
		uploadField.sendKeys(filePath);
		Thread.sleep(1000);
		String uploadedVal = uploadField.getAttribute("value");
		boolean uploadCheck = uploadedVal != null && !uploadedVal.isEmpty();
		log("File Upload", "Upload valid PDF file", "File path should be set", uploadedVal, uploadCheck);
		sa.assertTrue(uploadCheck, "File should be uploaded");

		boolean fileNameCheck = uploadedVal.contains("COLLATERAL_SEIZED_LETTER.pdf");
		log("File Upload", "Uploaded file name check", "COLLATERAL_SEIZED_LETTER.pdf", uploadedVal, fileNameCheck);
		sa.assertTrue(fileNameCheck, "Uploaded file name mismatch");

		System.out.println("=================================================");
		System.out.println("DU6 - FILE UPLOAD FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}
