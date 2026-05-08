package CaseStudy_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class CS8_CollateralDetails extends CS2_Setup {

	@Test
	public void validateCollateralDetails() throws Exception
	{
		System.out.println("========== COLLATERAL DETAILS ==========");

		WebElement collateralSection = driver.findElement(By.xpath("//*[text()='COLLATERAL DETAILS']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", collateralSection);
		Thread.sleep(1000);

		String[] ids = {
			"dealerName", "carPrice1", "maker", "model",
			"mfd", "color", "regNumber", "chassisNo"
		};

		String[] labels = {
			"Dealer Name", "Car Price", "Car Maker", "Car Model",
			"Year Of Manufacture", "Colour", "Registration Number", "Chassis Number"
		};

		for (int i = 0; i < ids.length; i++)
		{
			try
			{
				String val = driver.findElement(By.id(ids[i])).getAttribute("value");
				boolean hasData = val != null && !val.trim().isEmpty();
				log(labels[i], "Field should have data (id=" + ids[i] + ")", "Non-empty", hasData ? val : "EMPTY", hasData);
				if (!hasData) sa.fail("BUG: " + labels[i] + " (" + ids[i] + ") is empty");
			}
			catch (NoSuchElementException e)
			{
				log(labels[i], "Field should exist (id=" + ids[i] + ")", "Found", "NOT FOUND", false);
				sa.fail("BUG: " + labels[i] + " (" + ids[i] + ") not found");
			}
		}

		System.out.println("========== COLLATERAL DETAILS END ==========\n");
	}
}
