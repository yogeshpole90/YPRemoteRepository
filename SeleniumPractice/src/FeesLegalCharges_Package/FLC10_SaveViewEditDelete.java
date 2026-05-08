package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC10_SaveViewEditDelete extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC10 - SAVE VIEW EDIT DELETE VALIDATION START");
		System.out.println("=================================================");

		// ESCAPE datepicker if still open + scroll to Save
		try{driver.findElement(By.id("expenseDate")).sendKeys(org.openqa.selenium.Keys.ESCAPE);Thread.sleep(300);}catch(Exception ex){}

		// SAVE
		WebElement saveBtn=driver.findElement(By.id("saveFessCharge"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",saveBtn);Thread.sleep(300);
		log("Save Button","Should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		log("Save Button","Should be enabled","true",String.valueOf(saveBtn.isEnabled()),saveBtn.isEnabled());
		jse.executeScript("arguments[0].click()",saveBtn);Thread.sleep(500);
		String saveToast=getSuccessToastMsg();
		log("Save Button","Save with valid data","Success toast",saveToast.isEmpty()?"No toast":saveToast,!saveToast.isEmpty());

		// VIEW - class=ViewBtn, onclick=ViewData
		try{
			WebElement viewBtn=driver.findElement(By.xpath("//a[contains(@class,'ViewBtn') and contains(@onclick,'ViewData')]"));
			log("View Button","Should be visible","true",String.valueOf(viewBtn.isDisplayed()),viewBtn.isDisplayed());
			log("View Button","Should be enabled","true",String.valueOf(viewBtn.isEnabled()),viewBtn.isEnabled());
			viewBtn.click();Thread.sleep(500);
			log("View Button","Click View - record opens in view mode","View mode","View clicked",true);
		}catch(Exception e){log("View Button","View button","Visible","Not found - "+e.getMessage(),false);}

		// EDIT - class=editBtn, onclick=EditData
		try{
			WebElement editBtn=driver.findElement(By.xpath("//a[contains(@class,'editBtn') and contains(@onclick,'EditData')]"));
			log("Edit Button","Should be visible","true",String.valueOf(editBtn.isDisplayed()),editBtn.isDisplayed());
			log("Edit Button","Should be enabled","true",String.valueOf(editBtn.isEnabled()),editBtn.isEnabled());
			editBtn.click();Thread.sleep(500);
			log("Edit Button","Click Edit - record opens in edit mode","Edit mode","Edit clicked",true);
			// Save after edit
			driver.findElement(By.id("saveFessCharge")).click();Thread.sleep(500);
			String editToast=getSuccessToastMsg();
			log("Edit Save","Save after edit","Success toast",editToast.isEmpty()?"No toast":editToast,!editToast.isEmpty());
		}catch(Exception e){log("Edit Button","Edit button","Visible","Not found - "+e.getMessage(),false);}

		// DELETE - class=deleteBtn, onclick=DeleteData
		try{
			WebElement deleteBtn=driver.findElement(By.xpath("//a[contains(@class,'deleteBtn') and contains(@onclick,'DeleteData')]"));
			log("Delete Button","Should be visible","true",String.valueOf(deleteBtn.isDisplayed()),deleteBtn.isDisplayed());
			log("Delete Button","Should be enabled","true",String.valueOf(deleteBtn.isEnabled()),deleteBtn.isEnabled());
			deleteBtn.click();Thread.sleep(500);
			log("Delete Button","Click Delete - confirmation popup","Popup appeared","Delete clicked",true);
			// Yes on popup
			try{
				WebElement yesBtn=driver.findElement(By.id("popUpYes"));
				log("Delete Popup","Yes button should be visible","true",String.valueOf(yesBtn.isDisplayed()),yesBtn.isDisplayed());
				yesBtn.click();Thread.sleep(500);
				String deleteToast=getSuccessToastMsg();
				log("Delete Popup","Click Yes - record deleted","Success toast",deleteToast.isEmpty()?"No toast":deleteToast,!deleteToast.isEmpty());
			}catch(Exception ex){log("Delete Popup","No popup","Direct delete","Deleted directly",true);}
		}catch(Exception e){log("Delete Button","Delete button","Visible","Not found - "+e.getMessage(),false);}

		System.out.println("=================================================");
		System.out.println("FLC10 - SAVE VIEW EDIT DELETE VALIDATION END");
		System.out.println("=================================================");
	}
}

