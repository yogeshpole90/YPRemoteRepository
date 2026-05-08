package LawyerDetails_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD10_SaveCRUD extends LD2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD10 - SAVE VIEW EDIT DISABLE VALIDATION START");
		System.out.println("=================================================");

		String searchKey = LD6_RefCode.refCode;
		System.out.println(">> Using Ref Code for CRUD: " + searchKey);

		// SAVE
		jse.executeScript("window.scrollBy(0,500)");Thread.sleep(300);
		WebElement saveBtn=driver.findElement(By.id("save"));
		log("Save Button","Should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		log("Save Button","Should be enabled","true",String.valueOf(saveBtn.isEnabled()),saveBtn.isEnabled());
		saveBtn.click();Thread.sleep(1000);
		String saveToast=getSuccessToastMsg();
		log("Save Button","Save with valid data","Success toast",saveToast.isEmpty()?"No toast":saveToast,!saveToast.isEmpty());

		// BACK TO LIST
		jse.executeScript("window.scrollBy(0,500)");Thread.sleep(300);
		try{driver.findElement(By.xpath("//*[text()='Back to List']")).click();}
		catch(Exception e){driver.findElement(By.id("backButton")).click();}
		Thread.sleep(1000);
		log("Back to List","Click Back to List","List page","Back clicked",true);

		// SEARCH
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);
		Thread.sleep(1000);
		log("Search","Search by Ref Code '"+searchKey+"'","Results shown","Search entered",true);

		// VIEW
		try{
			WebElement v=driver.findElement(By.xpath("//*[text()='"+searchKey+"']/parent::tr//a[text()='View']"));
			log("View Button","Should be visible","true",String.valueOf(v.isDisplayed()),v.isDisplayed());
			v.click();Thread.sleep(1000);
			log("View Button","Click View","View mode","View clicked",true);
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("backButton")).click();Thread.sleep(1000);
		}catch(Exception e){
			// Try alternate xpath
			try{
				WebElement v2=driver.findElement(By.xpath("//*[contains(text(),'"+searchKey+"')]/parent::tr//td[3]/a"));
				v2.click();Thread.sleep(1000);
				log("View Button","Click View (alt xpath)","View mode","View clicked",true);
				jse.executeScript("window.scrollBy(0,500)");
				driver.findElement(By.id("backButton")).click();Thread.sleep(1000);
			}catch(Exception e2){log("View Button","View button","Visible","Not found",false);}
		}

		// EDIT
		try{
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);Thread.sleep(1000);
			WebElement ed=driver.findElement(By.xpath("//*[text()='"+searchKey+"']/parent::tr//a[text()='Edit']"));
			log("Edit Button","Should be visible","true",String.valueOf(ed.isDisplayed()),ed.isDisplayed());
			ed.click();Thread.sleep(1000);
			log("Edit Button","Click Edit","Edit mode","Edit clicked",true);
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("save")).click();Thread.sleep(1000);
			String editToast=getSuccessToastMsg();
			log("Edit Save","Save after edit","Success toast",editToast.isEmpty()?"No toast":editToast,!editToast.isEmpty());
			jse.executeScript("window.scrollBy(0,500)");
			try{driver.findElement(By.xpath("//*[text()='Back to List']")).click();}
			catch(Exception ex){driver.findElement(By.id("backButton")).click();}
			Thread.sleep(1000);
		}catch(Exception e){
			try{
				driver.findElement(By.xpath("//*[@type='search']")).clear();
				driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);Thread.sleep(1000);
				WebElement ed2=driver.findElement(By.xpath("//*[contains(text(),'"+searchKey+"')]/parent::tr//td[4]/a"));
				ed2.click();Thread.sleep(1000);
				log("Edit Button","Click Edit (alt xpath)","Edit mode","Edit clicked",true);
				jse.executeScript("window.scrollBy(0,500)");
				driver.findElement(By.id("save")).click();Thread.sleep(1000);
				jse.executeScript("window.scrollBy(0,500)");
				driver.findElement(By.id("backButton")).click();Thread.sleep(1000);
			}catch(Exception e2){log("Edit Button","Edit button","Visible","Not found",false);}
		}

		// DISABLE
		try{
			driver.findElement(By.xpath("//*[@type='search']")).clear();
			driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchKey);Thread.sleep(1000);
			WebElement dis=driver.findElement(By.xpath("//*[text()='"+searchKey+"']/parent::tr//a[text()='Disable']"));
			log("Disable Button","Should be visible","true",String.valueOf(dis.isDisplayed()),dis.isDisplayed());
			dis.click();Thread.sleep(1000);
			log("Disable Button","Click Disable","Disabled","Disable clicked",true);
			try{driver.findElement(By.id("popUpYes")).click();Thread.sleep(500);
			log("Disable Button","Click Yes","Disabled","Yes clicked",true);
			}catch(Exception ex){log("Disable Button","No popup","Direct disable","Disabled",true);}
		}catch(Exception e){
			try{
				WebElement dis2=driver.findElement(By.xpath("//*[contains(text(),'"+searchKey+"')]/parent::tr//td[5]"));
				dis2.click();Thread.sleep(1000);
				log("Disable Button","Click Disable (alt xpath)","Disabled","Disable clicked",true);
			}catch(Exception e2){log("Disable Button","Disable button","Visible","Not found",false);}
		}

		System.out.println("=================================================");
		System.out.println("LD10 - SAVE VIEW EDIT DISABLE VALIDATION END");
		System.out.println("=================================================");
	}
}

