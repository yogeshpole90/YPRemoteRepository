package FeesLegalCharges_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FLC7_CurrencyDD extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC7 - CURRENCY DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("currency"));
		Select s=new Select(dd);
		log("Currency DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		log("Currency DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		String def=s.getFirstSelectedOption().getText().trim();
		log("Currency DD","Default value","--SELECT [CURRENCY]--",def,def.contains("SELECT")||def.contains("Select"));
		List<WebElement> opts=s.getOptions();
		log("Currency DD","Total options","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		String[] exp={"EURO","UA","US Dollar","XOF"};
		for(String e:exp){boolean found=false;for(WebElement o:opts)if(o.getText().trim().equals(e)){found=true;break;}
		log("Currency DD","Option '"+e+"' present",e,found?e+" found":e+" NOT found",found);}
		s.selectByVisibleText("US Dollar");Thread.sleep(300);
		log("Currency DD","Select 'US Dollar'","US Dollar",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("US Dollar"));
		s.selectByVisibleText("EURO");Thread.sleep(300);
		log("Currency DD","Select 'EURO'","EURO",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("EURO"));
		s.selectByVisibleText("US Dollar");Thread.sleep(300);
		log("Currency DD","Final value 'US Dollar' for save","US Dollar",s.getFirstSelectedOption().getText().trim(),true);
		System.out.println("=================================================");
		System.out.println("FLC7 - CURRENCY DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}

