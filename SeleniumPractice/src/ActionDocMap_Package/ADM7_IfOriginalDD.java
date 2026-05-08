package ActionDocMap_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ADM7_IfOriginalDD extends ADM2_Login {

	public void validateIfOriginal() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM7 - IF ORIGINAL DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("ifOriginal"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Select select = new Select(dd);

		log("If Original DD","Dropdown should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("If Original DD","Dropdown should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");

		String defaultVal = select.getFirstSelectedOption().getText().trim();
		log("If Original DD","Default selected value","Default/Select",defaultVal,true);

		List<WebElement> opts = select.getOptions();
		log("If Original DD","Total options count","More than 1",String.valueOf(opts.size()),opts.size()>1);

		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");

		boolean yesFound=false, noFound=false;
		for(WebElement o:opts) {
			if(o.getText().trim().equalsIgnoreCase("yes")) yesFound=true;
			if(o.getText().trim().equalsIgnoreCase("no")) noFound=true;
		}
		log("If Original DD","'yes' option should be present","yes present",yesFound?"yes found":"yes NOT found",yesFound);
		log("If Original DD","'no' option should be present","no present",noFound?"no found":"no NOT found",noFound);

		select.selectByVisibleText("yes");Thread.sleep(200);
		log("If Original DD","Select 'yes'","yes",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("yes"));

		select.selectByVisibleText("no");Thread.sleep(200);
		log("If Original DD","Select 'no'","no",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("no"));

		// Final set for save
		select.selectByVisibleText("yes");Thread.sleep(200);
		log("If Original DD","Final value 'yes' for save","yes",select.getFirstSelectedOption().getText().trim(),true);

		System.out.println("=================================================");
		System.out.println("ADM7 - IF ORIGINAL DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


