package ActionDocMap_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ADM6_MandatoryUploadDD extends ADM2_Login {

	public void validateMandatoryUpload() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM6 - MANDATORY UPLOAD DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("ifMandatoryUpload"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Select select = new Select(dd);

		log("Mandatory Upload DD","Dropdown should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Mandatory Upload DD","Dropdown should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");

		String defaultVal = select.getFirstSelectedOption().getText().trim();
		log("Mandatory Upload DD","Default selected value","Default/Select",defaultVal,true);

		List<WebElement> opts = select.getOptions();
		log("Mandatory Upload DD","Total options count","More than 1",String.valueOf(opts.size()),opts.size()>1);

		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");

		// Check yes/no options
		boolean yesFound=false, noFound=false;
		for(WebElement o:opts) {
			if(o.getText().trim().equalsIgnoreCase("yes")) yesFound=true;
			if(o.getText().trim().equalsIgnoreCase("no")) noFound=true;
		}
		log("Mandatory Upload DD","'yes' option should be present","yes present",yesFound?"yes found":"yes NOT found",yesFound);
		log("Mandatory Upload DD","'no' option should be present","no present",noFound?"no found":"no NOT found",noFound);

		select.selectByVisibleText("yes");Thread.sleep(200);
		log("Mandatory Upload DD","Select 'yes'","yes",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("yes"));

		select.selectByVisibleText("no");Thread.sleep(200);
		log("Mandatory Upload DD","Select 'no'","no",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("no"));

		// Final set for save
		select.selectByVisibleText("yes");Thread.sleep(200);
		log("Mandatory Upload DD","Final value 'yes' for save","yes",select.getFirstSelectedOption().getText().trim(),true);

		System.out.println("=================================================");
		System.out.println("ADM6 - MANDATORY UPLOAD DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


