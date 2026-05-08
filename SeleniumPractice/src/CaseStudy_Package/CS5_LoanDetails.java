package CaseStudy_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class CS5_LoanDetails extends CS2_Setup {

	@Test
	public void validateLoanDetails() throws Exception
	{
		System.out.println("========== LOAN DETAILS ==========");

		WebElement loanSection = driver.findElement(By.xpath("//*[text()='LOAN DETAILS']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", loanSection);
		Thread.sleep(1000);

		String[] ids = {
			"caseStatus", "loanAcNo", "product", "subproduct",
			"loanStartDate", "loanEndDate", "lastPaymentDate",
			"loanAmount", "loanType", "repaymentMode", "bucket"
		};

		String[] labels = {
			"Loan Status", "Loan Account No", "Product", "Sub Product",
			"Loan Start Date", "Loan End Date", "Last Payment Date",
			"Loan Amount", "Loan Type", "Repayment Mode", "Bucket"
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

		System.out.println("========== LOAN DETAILS END ==========\n");
	}
}
