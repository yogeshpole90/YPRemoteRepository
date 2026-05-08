package CaseStudy_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class CS6_OverdueDetails extends CS2_Setup {

	@Test
	public void validateOverdueDetails() throws Exception
	{
		System.out.println("========== OVERDUE DETAILS ==========");

		WebElement overdueSection = driver.findElement(By.xpath("//*[text()='OVERDUE DETAILS']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", overdueSection);
		Thread.sleep(1000);

		String[] ids = {
			"overdueDays", "overdueAmount", "outstandingAmount",
			"chgOvdAmt", "firstNpaDate", "writeOffDate", "writeOffRecoveredAmt"
		};

		String[] labels = {
			"Days Past Due (DPD)", "Overdue Amount", "Outstanding Amount",
			"Charges Overdue Amount", "First NPL Date", "Write-Off Date", "Write-Off Recovered Amount"
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

		System.out.println("========== OVERDUE DETAILS END ==========\n");
	}
}
