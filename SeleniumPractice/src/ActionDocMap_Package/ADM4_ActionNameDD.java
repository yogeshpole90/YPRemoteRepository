package ActionDocMap_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ADM4_ActionNameDD extends ADM2_Login {

	public void validateActionName() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM4 - ACTION NAME DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("actionName"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(300);
		Select select = new Select(dd);

		log("Action Name DD","Dropdown should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Action Name DD","Dropdown should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");

		String defaultVal = select.getFirstSelectedOption().getText().trim();
		log("Action Name DD","Default selected value","Default/Select",defaultVal,true);

		List<WebElement> opts = select.getOptions();
		log("Action Name DD","Total options count","More than 1",String.valueOf(opts.size()),opts.size()>1);

		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");

		String[] expected = {"Asset Repossession","Full & Final Settlement","Partial Settlement","Promise To Pay","Release Asset","Write Off"};
		for(String exp : expected) {
			boolean found=false;
			for(WebElement o:opts) if(o.getText().trim().contains(exp)) {found=true;break;}
			log("Action Name DD","Option '"+exp+"' should be present",exp+" present",found?exp+" found":exp+" NOT found",found);
			sa.assertTrue(found,"Option missing: "+exp);
		}

		select.selectByVisibleText("Asset Repossession");
		Thread.sleep(300);
		log("Action Name DD","Select 'Asset Repossession'","Asset Repossession",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equals("Asset Repossession"));

		select.selectByVisibleText("Promise To Pay");
		Thread.sleep(300);
		log("Action Name DD","Select 'Promise To Pay'","Promise To Pay",select.getFirstSelectedOption().getText().trim(),select.getFirstSelectedOption().getText().trim().equals("Promise To Pay"));

		// Final set for save
		select.selectByVisibleText("Asset Repossession");
		Thread.sleep(300);
		log("Action Name DD","Final value 'Asset Repossession' for save","Asset Repossession",select.getFirstSelectedOption().getText().trim(),true);

		System.out.println("=================================================");
		System.out.println("ADM4 - ACTION NAME DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


