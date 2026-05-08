package RemedialAction;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F8_SaveView extends F2_Setup {

	public void validateSaveView() throws Exception {
		System.out.println("=================================================");
		System.out.println("F8 - SAVE VIEW VALIDATION START");
		System.out.println("=================================================");

		// Save button
		WebElement saveBtn=driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",saveBtn);
		Thread.sleep(300);
		log("Save Button","Save button should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		log("Save Button","Save button should be enabled","true",String.valueOf(saveBtn.isEnabled()),saveBtn.isEnabled());
		saveBtn.click();
		Thread.sleep(500);

		// Success toast
		String successToast=getSuccessToastMsg();
		log("Save Button","Save click - success toast","Success toast",successToast.isEmpty()?"No toast":successToast,!successToast.isEmpty());

		Thread.sleep(1000);

		// Reset button - re-find to avoid stale
		try {
			WebElement resetBtn=driver.findElement(By.xpath("//*[text()='Reset']"));
			log("Reset Button","Reset button visible & enabled after save","true","displayed="+resetBtn.isDisplayed()+" enabled="+resetBtn.isEnabled(),resetBtn.isDisplayed()&&resetBtn.isEnabled());
		} catch(Exception e) {
			log("Reset Button","Reset button after save","Visible","Not found - "+e.getMessage(),false);
		}

		// Save button - re-find to avoid stale
		try {
			WebElement saveBtn2=driver.findElement(By.id("save"));
			log("Save Button","Save button visible & enabled after save","true","displayed="+saveBtn2.isDisplayed()+" enabled="+saveBtn2.isEnabled(),saveBtn2.isDisplayed()&&saveBtn2.isEnabled());
		} catch(Exception e) {
			log("Save Button","Save button after save","Visible","Not found - "+e.getMessage(),false);
		}

		// Initiate button - re-find to avoid stale
		try {
			WebElement initiateBtn=driver.findElement(By.id("losCall"));
			log("Initiate Button","Initiate button visible & enabled after save","true","displayed="+initiateBtn.isDisplayed()+" enabled="+initiateBtn.isEnabled(),initiateBtn.isDisplayed()&&initiateBtn.isEnabled());
		} catch(Exception e) {
			log("Initiate Button","Initiate button after save","Visible","Not found - "+e.getMessage(),false);
		}

		// View buttons
		List<WebElement> viewBtns=driver.findElements(By.xpath("//*[text()='View']"));
		log("View Button","View button should exist","Count > 0",String.valueOf(viewBtns.size()),viewBtns.size()>0);
		sa.assertTrue(viewBtns.size()>0,"No View button");

		if(viewBtns.size()>0){
			WebElement viewBtn=viewBtns.get(viewBtns.size()-1);
			viewBtn.click();
			Thread.sleep(1000);
			log("View Button","Click last View button","Record displayed","View clicked",true);

			// Check fields after view - re-find to avoid stale
			try {
				String actVal=driver.findElement(By.id("actionId")).getAttribute("value");
				String cmtVal=driver.findElement(By.id("commments")).getAttribute("value");
				log("View Data","Action Name after View","Not empty",actVal.isEmpty()?"EMPTY":actVal,!actVal.isEmpty());
				log("View Data","Comments after View","Not empty",cmtVal.isEmpty()?"EMPTY":cmtVal,!cmtVal.isEmpty());
			} catch(Exception e) {
				log("View Data","Fields after View","Data populated","Error: "+e.getMessage(),false);
			}
		}

		System.out.println("=================================================");
		System.out.println("F8 - SAVE VIEW VALIDATION END");
		System.out.println("=================================================");
	}
}


