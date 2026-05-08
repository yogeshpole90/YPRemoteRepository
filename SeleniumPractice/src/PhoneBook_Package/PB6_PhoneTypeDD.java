package PhoneBook_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PB6_PhoneTypeDD extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB6 - PHONE TYPE DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("phoneType"));
		Select s=new Select(dd);
		log("Phone Type DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Phone Type DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");
		String def=s.getFirstSelectedOption().getText().trim();
		log("Phone Type DD","Default value","--SELECT [PHONETYPECONTACT]--",def,def.contains("--SELECT"));
		List<WebElement> opts=s.getOptions();
		log("Phone Type DD","Total options","3 (1 default + 2 actual)",String.valueOf(opts.size()),opts.size()==3);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		// Check Primary and Secondary
		boolean priFound=false,secFound=false;
		for(WebElement o:opts){if(o.getText().trim().equals("Primary"))priFound=true;if(o.getText().trim().equals("Secondary"))secFound=true;}
		log("Phone Type DD","'Primary' option present","Primary",priFound?"Primary found":"NOT found",priFound);
		log("Phone Type DD","'Secondary' option present","Secondary",secFound?"Secondary found":"NOT found",secFound);
		s.selectByVisibleText("Primary");Thread.sleep(200);
		log("Phone Type DD","Select 'Primary'","Primary",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Primary"));
		s.selectByVisibleText("Secondary");Thread.sleep(200);
		log("Phone Type DD","Select 'Secondary'","Secondary",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Secondary"));
		s.selectByIndex(0);Thread.sleep(200);
		log("Phone Type DD","Reset to default","--SELECT--",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().contains("--SELECT"));
		s.selectByVisibleText("Primary");Thread.sleep(200);
		log("Phone Type DD","Final value 'Primary' for save","Primary",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Primary"));
		System.out.println("=================================================");
		System.out.println("PB6 - PHONE TYPE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


