package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DU9_Download extends DU2_Login {

	public void validateDownload() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU9 - DOWNLOAD BUTTON VALIDATION START");
		System.out.println("=================================================");

		// TC1: Download button should be displayed
		WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(@onclick,'Download') and contains(@class,'btn-primary')]"));
		boolean downloadDisplayed = downloadBtn.isDisplayed();
		log("Download Button", "Download button should be visible for uploaded document", "true", String.valueOf(downloadDisplayed), downloadDisplayed);
		sa.assertTrue(downloadDisplayed, "Download button should be visible");

		// TC2: Download button should be enabled
		boolean downloadEnabled = downloadBtn.isEnabled();
		log("Download Button", "Download button should be enabled/clickable", "true", String.valueOf(downloadEnabled), downloadEnabled);
		sa.assertTrue(downloadEnabled, "Download button should be enabled");

		// TC3: Download button text should be "Download"
		String btnText = downloadBtn.getText().trim();
		boolean textCheck = btnText.equals("Download");
		log("Download Button", "Download button text should be 'Download'", "Download", btnText, textCheck);
		sa.assertEquals(btnText, "Download", "Download button text mismatch");

		// TC4: Download Doc of Repossession
		WebElement dlRepossession = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Doc_of_Repossession\")]"));
		dlRepossession.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of Repossession' - file should download", "File downloaded", "Download clicked for Doc_of_Repossession", true);

		// TC5: Download Doc of FNF
		WebElement dlFNF = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Doc_of_FNF\")]"));
		dlFNF.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of FNF' - file should download", "File downloaded", "Download clicked for Doc_of_FNF", true);

		// TC6: Download Doc of Partial stmnt
		WebElement dlPartial = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Doc_of_Partial_stmnt\")]"));
		dlPartial.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of Partial stmnt' - file should download", "File downloaded", "Download clicked for Doc_of_Partial_stmnt", true);

		// TC7: Download Doc of PTP
		WebElement dlPTP = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Doc_of_PTP\")]"));
		dlPTP.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of PTP' - file should download", "File downloaded", "Download clicked for Doc_of_PTP", true);

		// TC8: Download Doc of Release Asset
		WebElement dlRelease = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Release_Asset\")]"));
		dlRelease.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of Release Asset' - file should download", "File downloaded", "Download clicked for Release_Asset", true);

		// TC9: Download Doc of Case Write Off
		WebElement dlWriteOff = driver.findElement(By.xpath("//a[contains(@onclick,\"Download('Doc_of_Case_Write_Off\")]"));
		dlWriteOff.click();
		Thread.sleep(2000);
		log("Download Button", "Click Download for 'Doc of Case Write Off' - file should download", "File downloaded", "Download clicked for Doc_of_Case_Write_Off", true);

		// TC10: All Download buttons count should match uploaded documents
		List<WebElement> allDlBtns = driver.findElements(By.xpath("//a[contains(@onclick,'Download') and contains(@class,'btn-primary')]"));
		int dlCount = allDlBtns.size();
		log("Download Button", "Total Download buttons should match total uploaded documents", "6", String.valueOf(dlCount), dlCount == 6);
		sa.assertEquals(dlCount, 6, "Download button count should match uploaded documents");

		System.out.println("=================================================");
		System.out.println("DU9 - DOWNLOAD BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
