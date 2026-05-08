package FeesLegalCharges_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FLC4_ChargeName extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC4 - CHARGE NAME DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("chargeName"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",dd);Thread.sleep(300);
		Select s=new Select(dd);
		log("Charge Name DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Charge Name DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");
		String def=s.getFirstSelectedOption().getText().trim();
		log("Charge Name DD","Default value","--SELECT [NFHCHARGENAME]--",def,def.contains("--SELECT"));
		List<WebElement> opts=s.getOptions();
		log("Charge Name DD","Total options","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		String[] exp={"Appeal Fees","Court Fees","Expert Fees","Insurance Expense","Other","Registration Expenses","Traffic Ticket","Transport Expense","Travel Ban Fees"};
		for(String e:exp){boolean found=false;for(WebElement o:opts)if(o.getText().trim().equals(e)){found=true;break;}
		log("Charge Name DD","Option '"+e+"' present",e,found?e+" found":e+" NOT found",found);sa.assertTrue(found,"Missing: "+e);}
		s.selectByVisibleText("Court Fees");Thread.sleep(300);
		log("Charge Name DD","Select 'Court Fees'","Court Fees",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Court Fees"));
		s.selectByVisibleText("Appeal Fees");Thread.sleep(300);
		log("Charge Name DD","Select 'Appeal Fees'","Appeal Fees",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Appeal Fees"));
		s.selectByIndex(0);Thread.sleep(300);
		log("Charge Name DD","Reset to default","--SELECT--",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().contains("--SELECT"));
		s.selectByVisibleText("Court Fees");Thread.sleep(300);
		log("Charge Name DD","Final value 'Court Fees' for save","Court Fees",s.getFirstSelectedOption().getText().trim(),true);
		System.out.println("=================================================");
		System.out.println("FLC4 - CHARGE NAME DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}

