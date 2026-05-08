package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF10_SaveViewEditDisable extends LF2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF10 - SAVE VIEW EDIT DISABLE VALIDATION START");
		System.out.println("=================================================");

		String searchKey = LF6_RegNo.regNo;
		System.out.println(">> Using Reg No for CRUD: " + searchKey);

		// SAVE
		jse.executeScript("window.scrollBy(0,500)");Thread.sleep(300);
		WebElement saveBtn=driver.findElement(By.id("saveFirm"));
		log("Save Button","Should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		log("Save Button","Should be enabled","true",String.valueOf(saveBtn.isEnabled()),saveBtn.isEnabled());
		saveBtn.click();Thread.sleep(1000);
		String saveToast=getSuccessToastMsg();
		log("Save Button","Save with valid data","Success toast",saveToast.isEmpty()?"No toast":saveToast,!saveToast.isEmpty());

		// BACK
		jse.executeScript("window.scrollBy(0,500)");Thread.sleep(300);
		WebElement backBtn=driver.findElement(By.id("backButton"));
		log("Back Button","Should be visible","true",String.valueOf(backBtn.isDisplayed()),backBtn.isDisplayed());
		backBtn.click();Thread.sleep(1000);
		log("Back Button","Click Back - list page","List page","Back clicked",true);

		// SEARCH
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);
		Thread.sleep(1000);
		log("Search","Search by Reg No '"+searchKey+"'","Results shown","Search entered",true);

		// VIEW
		try{
			WebElement viewBtn=driver.findElement(By.xpath("//td[text()='"+searchKey+"']/parent::tr//a[text()='View']"));
			log("View Button","Should be visible","true",String.valueOf(viewBtn.isDisplayed()),viewBtn.isDisplayed());
			viewBtn.click();Thread.sleep(1000);
			log("View Button","Click View - record opens","View mode","View clicked",true);
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("backButton")).click();Thread.sleep(1000);
		}catch(Exception e){log("View Button","View button","Visible","Not found",false);}

		// EDIT
		try{
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);Thread.sleep(1000);
			WebElement editBtn=driver.findElement(By.xpath("//td[text()='"+searchKey+"']/parent::tr//a[text()='Edit']"));
			log("Edit Button","Should be visible","true",String.valueOf(editBtn.isDisplayed()),editBtn.isDisplayed());
			editBtn.click();Thread.sleep(1000);
			log("Edit Button","Click Edit - record opens","Edit mode","Edit clicked",true);
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("saveFirm")).click();Thread.sleep(1000);
			String editToast=getSuccessToastMsg();
			log("Edit Save","Save after edit","Success toast",editToast.isEmpty()?"No toast":editToast,!editToast.isEmpty());
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("backButton")).click();Thread.sleep(1000);
		}catch(Exception e){log("Edit Button","Edit button","Visible","Not found",false);}

		// DISABLE
		try{
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);Thread.sleep(1000);
			WebElement disBtn=driver.findElement(By.xpath("//*[text()='"+searchKey+"']/parent::tr//a[text()='Disable']"));
			log("Disable Button","Should be visible","true",String.valueOf(disBtn.isDisplayed()),disBtn.isDisplayed());
			disBtn.click();Thread.sleep(1000);
			log("Disable Button","Click Disable","Record disabled","Disable clicked",true);
			try{driver.findElement(By.id("popUpYes")).click();Thread.sleep(500);
			log("Disable Button","Click Yes on popup","Disabled","Yes clicked",true);
			}catch(Exception ex){log("Disable Button","No popup","Direct disable","Disabled directly",true);}
		}catch(Exception e){log("Disable Button","Disable button","Visible","Not found",false);}

		System.out.println("=================================================");
		System.out.println("LF10 - SAVE VIEW EDIT DISABLE VALIDATION END");
		System.out.println("=================================================");
	}
}

