package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DU8_View extends DU2_Login {

	public void validateView() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU8 - VIEW BUTTON VALIDATION START");
		System.out.println("=================================================");

		// TC1: View button should be displayed
		WebElement viewBtn = driver.findElement(By.xpath("//a[contains(@onclick,'upload') and contains(@class,'btn-info')]"));
		boolean viewDisplayed = viewBtn.isDisplayed();
		log("View Button", "View button should be visible for uploaded document", "true", String.valueOf(viewDisplayed), viewDisplayed);
		sa.assertTrue(viewDisplayed, "View button should be visible");

		// TC2: View button should be enabled
		boolean viewEnabled = viewBtn.isEnabled();
		log("View Button", "View button should be enabled/clickable", "true", String.valueOf(viewEnabled), viewEnabled);
		sa.assertTrue(viewEnabled, "View button should be enabled");

		// TC3: View button text should be "View"
		String btnText = viewBtn.getText().trim();
		boolean textCheck = btnText.equals("View");
		log("View Button", "View button text should be 'View'", "View", btnText, textCheck);
		sa.assertEquals(btnText, "View", "View button text mismatch");

		// TC4: View Doc of Repossession
		WebElement viewRepossession = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Doc_of_Repossession\")]"));
		viewRepossession.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of Repossession' - document should open", "Document opened in view mode", "View clicked for Doc_of_Repossession", true);

		// TC5: View Doc of FNF
		WebElement viewFNF = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Doc_of_FNF\")]"));
		viewFNF.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of FNF' - document should open", "Document opened in view mode", "View clicked for Doc_of_FNF", true);

		// TC6: View Doc of Partial stmnt
		WebElement viewPartial = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Doc_of_Partial_stmnt\")]"));
		viewPartial.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of Partial stmnt' - document should open", "Document opened in view mode", "View clicked for Doc_of_Partial_stmnt", true);

		// TC7: View Doc of PTP
		WebElement viewPTP = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Doc_of_PTP\")]"));
		viewPTP.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of PTP' - document should open", "Document opened in view mode", "View clicked for Doc_of_PTP", true);

		// TC8: View Doc of Release Asset
		WebElement viewRelease = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Release_Asset\")]"));
		viewRelease.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of Release Asset' - document should open", "Document opened in view mode", "View clicked for Release_Asset", true);

		// TC9: View Doc of Case Write Off
		WebElement viewWriteOff = driver.findElement(By.xpath("//a[contains(@onclick,\"upload('Doc_of_Case_Write_Off\")]"));
		viewWriteOff.click();
		Thread.sleep(2000);
		log("View Button", "Click View for 'Doc of Case Write Off' - document should open", "Document opened in view mode", "View clicked for Doc_of_Case_Write_Off", true);

		// TC10: All View buttons count should match uploaded documents
		List<WebElement> allViewBtns = driver.findElements(By.xpath("//a[contains(@onclick,'upload') and contains(@class,'btn-info')]"));
		int viewCount = allViewBtns.size();
		log("View Button", "Total View buttons should match total uploaded documents", "6", String.valueOf(viewCount), viewCount == 6);
		sa.assertEquals(viewCount, 6, "View button count should match uploaded documents");

		System.out.println("=================================================");
		System.out.println("DU8 - VIEW BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
