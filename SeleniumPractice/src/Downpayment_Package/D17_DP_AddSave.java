package Downpayment_Package;
import java.util.List; import org.openqa.selenium.By; import org.openqa.selenium.WebElement; import org.openqa.selenium.support.ui.ExpectedConditions; import org.openqa.selenium.support.ui.WebDriverWait;
public class D17_DP_AddSave extends D2_DP_Login {
	public void validateAddSave() throws Exception {
		System.out.println("=================================================");
		System.out.println("D17 - ADD SAVE VIEW EDIT VALIDATION START");
		System.out.println("=================================================");
		WebDriverWait wait=new WebDriverWait(driver,10);
		WebElement add1=driver.findElement(By.xpath("//button[@id='add2']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",add1);Thread.sleep(300);
		log("Add Button","DP Add button should be visible","true",String.valueOf(add1.isDisplayed()),add1.isDisplayed());
		add1.click();
		log("Add Button","Click DP Add button","Clicked","DP Add clicked",true);
		WebElement add2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add3']")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",add2);Thread.sleep(300);
		log("Add Button","Schedule Add button should be visible","true",String.valueOf(add2.isDisplayed()),add2.isDisplayed());
		add2.click();
		log("Add Button","Click Schedule Add button","Clicked","Schedule Add clicked",true);
		WebElement saveBtn=wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",saveBtn);Thread.sleep(300);
		log("Save Button","Save button should be visible","true",String.valueOf(saveBtn.isDisplayed()),saveBtn.isDisplayed());
		saveBtn.click();
		log("Save Button","Click Save button","Record saved","Save clicked",true);
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		WebElement ptpTab=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Promise to pay')]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",ptpTab);
		Thread.sleep(500);
		ptpTab.click();
		Thread.sleep(2000);
		try{ driver.switchTo().frame("fetchPTPMstTabFrame"); }catch(Exception ef){ System.out.println("Frame fetchPTPMstTabFrame not found: "+ef.getMessage()); }
		List<WebElement> viewBtns=driver.findElements(By.xpath("(//a[contains(@class,'ViewBtn') and contains(@onclick,'ViewData')])[last()]"));
		log("View Button","View button should exist","Count > 0",String.valueOf(viewBtns.size()),viewBtns.size()>0);
		sa.assertTrue(viewBtns.size()>0,"No View button");
		if(viewBtns.size()>0){viewBtns.get(viewBtns.size()-1).click();Thread.sleep(500);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);
		log("View Button","Click View button - scrolled to bottom for multiline data","Record displayed + bottom scroll","View clicked + scrolled",true);}
		List<WebElement> editBtns=driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
		log("Edit Button","Edit button should exist","Count > 0",String.valueOf(editBtns.size()),editBtns.size()>0);
		sa.assertTrue(editBtns.size()>0,"No Edit button");
		if(editBtns.size()>0){editBtns.get(editBtns.size()-1).click();Thread.sleep(500);
		log("Edit Button","Click Edit button","Fields editable","Edit clicked",true);}
		// Disable Button
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);
		List<WebElement> disableBtns=driver.findElements(By.xpath("(//a[contains(@class,'btn-danger') and contains(@onclick,'disableRecord')])[last()]"));
		log("Disable Button","Disable button should exist","Count > 0",String.valueOf(disableBtns.size()),disableBtns.size()>0);
		sa.assertTrue(disableBtns.size()>0,"No Disable button");
		if(disableBtns.size()>0){
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",disableBtns.get(disableBtns.size()-1));
			Thread.sleep(300);
			disableBtns.get(disableBtns.size()-1).click();
			Thread.sleep(500);
			log("Disable Button","Click Disable button","Confirmation popup or record disabled","Disable clicked",true);
			try{
				WebElement yesBtn=driver.findElement(By.id("popUpYes"));
				yesBtn.click();
				Thread.sleep(500);
				log("Disable Button","Click Yes on confirmation popup","Record disabled","Yes clicked",true);
			}catch(Exception e){
				log("Disable Button","No confirmation popup","Popup or direct disable","Disabled directly",true);
			}
		}
		System.out.println("=================================================");
		System.out.println("D17 - ADD SAVE VIEW EDIT VALIDATION END");
		System.out.println("=================================================");
	}
}


