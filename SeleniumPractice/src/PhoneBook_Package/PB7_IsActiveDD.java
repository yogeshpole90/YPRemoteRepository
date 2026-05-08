package PhoneBook_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PB7_IsActiveDD extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB7 - IS ACTIVE DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("isActive"));
		Select s=new Select(dd);
		log("Is Active DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Is Active DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");
		String def=s.getFirstSelectedOption().getText().trim();
		log("Is Active DD","Default value","--SELECT [ISACTIVECONTACT]--",def,def.contains("--SELECT"));
		List<WebElement> opts=s.getOptions();
		log("Is Active DD","Total options","3 (1 default + 2 actual)",String.valueOf(opts.size()),opts.size()==3);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		boolean yesFound=false,noFound=false;
		for(WebElement o:opts){if(o.getText().trim().equals("Yes"))yesFound=true;if(o.getText().trim().equals("No"))noFound=true;}
		log("Is Active DD","'Yes' option present","Yes",yesFound?"Yes found":"NOT found",yesFound);
		log("Is Active DD","'No' option present","No",noFound?"No found":"NOT found",noFound);
		s.selectByVisibleText("Yes");Thread.sleep(200);
		log("Is Active DD","Select 'Yes'","Yes",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Yes"));
		s.selectByVisibleText("No");Thread.sleep(200);
		log("Is Active DD","Select 'No'","No",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("No"));
		s.selectByIndex(0);Thread.sleep(200);
		log("Is Active DD","Reset to default","--SELECT--",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().contains("--SELECT"));
		s.selectByVisibleText("Yes");Thread.sleep(200);
		log("Is Active DD","Final value 'Yes' for save","Yes",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Yes"));
		System.out.println("=================================================");
		System.out.println("PB7 - IS ACTIVE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


