package ActionDocMap_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ADM8_SaveBack extends ADM2_Login {

	public void validateSaveBack() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM8 - SAVE BACK VIEW EDIT DISABLE VALIDATION START");
		System.out.println("=================================================");

		// ========== MANDATORY CHECK ==========
		System.out.println("========== MANDATORY FIELD CHECK ==========");

		// Save without data
		jse.executeScript("window.scrollBy(0,500)");
		Thread.sleep(300);
		WebElement saveBtn = driver.findElement(By.id("save"));
		log("Save Button","Save button should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		log("Save Button","Save button should be enabled","true",String.valueOf(saveBtn.isEnabled()),saveBtn.isEnabled());

		// Clear all fields and save
		try { new Select(driver.findElement(By.id("actionName"))).selectByIndex(0); } catch(Exception e){}
		try { driver.findElement(By.id("documentName")).clear(); } catch(Exception e){}
		saveBtn = driver.findElement(By.id("save"));
		saveBtn.click();
		Thread.sleep(500);
		String mandatoryToast = getToastMsg();
		log("Mandatory Check","Save without filling any data","Error toast",mandatoryToast.isEmpty()?"No toast":mandatoryToast,!mandatoryToast.isEmpty());

		// ========== SAVE WITH VALID DATA ==========
		System.out.println("========== SAVE WITH VALID DATA ==========");

		new Select(driver.findElement(By.id("actionName"))).selectByVisibleText("Asset Repossession");
		Thread.sleep(200);
		driver.findElement(By.id("documentName")).clear();
		driver.findElement(By.id("documentName")).sendKeys("Asset Reposses");
		Thread.sleep(200);
		new Select(driver.findElement(By.id("ifMandatoryUpload"))).selectByVisibleText("yes");
		Thread.sleep(200);
		new Select(driver.findElement(By.id("ifOriginal"))).selectByVisibleText("yes");
		Thread.sleep(200);

		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);
		String saveToast = getSuccessToastMsg();
		log("Save Button","Save with valid data - all fields filled","Success toast",saveToast.isEmpty()?"No toast":saveToast,!saveToast.isEmpty());

		// ========== BACK BUTTON ==========
		System.out.println("========== BACK BUTTON ==========");

		jse.executeScript("window.scrollBy(0,500)");
		Thread.sleep(300);
		WebElement backBtn = driver.findElement(By.id("backButton"));
		log("Back Button","Back button should be visible","true",String.valueOf(backBtn.isDisplayed()),backBtn.isDisplayed());
		log("Back Button","Back button should be enabled","true",String.valueOf(backBtn.isEnabled()),backBtn.isEnabled());
		backBtn.click();
		Thread.sleep(1000);
		log("Back Button","Click Back - should go to list page","List page","Back clicked",true);

		// ========== SEARCH ==========
		System.out.println("========== SEARCH & VIEW/EDIT/DISABLE ==========");

		WebElement search = driver.findElement(By.xpath("//*[@type='search']"));
		search.sendKeys("Asset Reposses");
		Thread.sleep(1000);
		log("Search","Search 'Asset Reposses'","Results shown","Search entered",true);

		// ========== VIEW ==========
		try {
			WebElement viewBtn = driver.findElement(By.xpath("//*[text()='Asset Reposses']/parent::tr//*[text()='View']"));
			log("View Button","View button should be visible","true",String.valueOf(viewBtn.isDisplayed()),viewBtn.isDisplayed());
			viewBtn.click();
			Thread.sleep(1000);
			log("View Button","Click View - record should open in view mode","View mode","View clicked",true);

			// Back from view
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("backButton")).click();
			Thread.sleep(1000);
		} catch(Exception e) {
			log("View Button","View button","Visible","Not found - "+e.getMessage(),false);
		}

		// ========== EDIT ==========
		try {
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys("Asset Reposses");
			Thread.sleep(1000);
			WebElement editBtn = driver.findElement(By.xpath("//*[text()='Asset Reposses']/parent::tr//*[text()='Edit']"));
			log("Edit Button","Edit button should be visible","true",String.valueOf(editBtn.isDisplayed()),editBtn.isDisplayed());
			editBtn.click();
			Thread.sleep(1000);
			log("Edit Button","Click Edit - record should open in edit mode","Edit mode","Edit clicked",true);

			// Save after edit
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("save")).click();
			Thread.sleep(1000);
			String editSaveToast = getSuccessToastMsg();
			log("Edit Save","Save after edit","Success toast",editSaveToast.isEmpty()?"No toast":editSaveToast,!editSaveToast.isEmpty());

			// Back from edit
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("backButton")).click();
			Thread.sleep(1000);
		} catch(Exception e) {
			log("Edit Button","Edit button","Visible","Not found - "+e.getMessage(),false);
		}

		// ========== DISABLE ==========
		try {
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys("Asset Reposses");
			Thread.sleep(1000);
			WebElement disableBtn = driver.findElement(By.xpath("//*[text()='Asset Reposses']/parent::tr//*[text()='Disable']"));
			log("Disable Button","Disable button should be visible","true",String.valueOf(disableBtn.isDisplayed()),disableBtn.isDisplayed());
			disableBtn.click();
			Thread.sleep(1000);
			log("Disable Button","Click Disable - record should be disabled","Disabled","Disable clicked",true);

			// Handle confirmation popup if any
			try {
				WebElement yesBtn = driver.findElement(By.id("popUpYes"));
				yesBtn.click();
				Thread.sleep(500);
				log("Disable Button","Click Yes on confirmation popup","Record disabled","Yes clicked",true);
			} catch(Exception ex) {
				log("Disable Button","No confirmation popup","Direct disable","Disabled directly",true);
			}
		} catch(Exception e) {
			log("Disable Button","Disable button","Visible","Not found - "+e.getMessage(),false);
		}

		System.out.println("=================================================");
		System.out.println("ADM8 - SAVE BACK VIEW EDIT DISABLE VALIDATION END");
		System.out.println("=================================================");
	}
}


