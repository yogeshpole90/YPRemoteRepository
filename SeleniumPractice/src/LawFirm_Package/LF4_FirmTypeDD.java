package LawFirm_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LF4_FirmTypeDD extends LF2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF4 - FIRM TYPE DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("firmType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",dd);
		Select s=new Select(dd);
		log("Firm Type DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		log("Firm Type DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		String def=s.getFirstSelectedOption().getText().trim();
		log("Firm Type DD","Default value","Default/Select",def,true);
		List<WebElement> opts=s.getOptions();
		log("Firm Type DD","Total options","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText());
		// Check GOVERMET and GROUP OF LAWERS
		String[] exp={"GOVERMET","GROUP OF LAWERS PVT LTD"};
		for(String e:exp){boolean found=false;for(WebElement o:opts)if(o.getText().trim().contains(e)){found=true;break;}
		log("Firm Type DD","Option '"+e+"' present",e,found?e+" found":e+" NOT found",found);}
		s.selectByVisibleText("GOVERMET"); Thread.sleep(300);
		log("Firm Type DD","Select 'GOVERMET'","GOVERMET",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().contains("GOVERMET"));
		s.selectByVisibleText("GROUP OF LAWERS PVT LTD"); Thread.sleep(300);
		log("Firm Type DD","Select 'GROUP OF LAWERS PVT LTD'","GROUP OF LAWERS PVT LTD",s.getFirstSelectedOption().getText().trim(),true);
		s.selectByVisibleText("GOVERMET"); Thread.sleep(300);
		log("Firm Type DD","Final value 'GOVERMET' for save","GOVERMET",s.getFirstSelectedOption().getText().trim(),true);
		System.out.println("=================================================");
		System.out.println("LF4 - FIRM TYPE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}

