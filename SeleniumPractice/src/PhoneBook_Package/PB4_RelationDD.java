package PhoneBook_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PB4_RelationDD extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB4 - RELATION DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("contactRelation"));
		Select s=new Select(dd);
		log("Relation DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Relation DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");
		String def=s.getFirstSelectedOption().getText().trim();
		log("Relation DD","Default value","--SELECT [CONTACTRELATION]--",def,def.contains("--SELECT"));
		List<WebElement> opts=s.getOptions();
		log("Relation DD","Total options","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		String[] exp={"Brother","Debtor","Father","Guaranteer","Mother","Sister","Wife"};
		for(String e:exp){boolean found=false;for(WebElement o:opts)if(o.getText().trim().equals(e)){found=true;break;}
		log("Relation DD","Option '"+e+"' present",e,found?e+" found":e+" NOT found",found);sa.assertTrue(found,"Missing: "+e);}
		s.selectByVisibleText("Father");Thread.sleep(200);
		log("Relation DD","Select 'Father'","Father",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Father"));
		s.selectByVisibleText("Brother");Thread.sleep(200);
		log("Relation DD","Select 'Brother'","Brother",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Brother"));
		s.selectByVisibleText("Debtor");Thread.sleep(200);
		log("Relation DD","Select 'Debtor'","Debtor",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Debtor"));
		s.selectByIndex(0);Thread.sleep(200);
		log("Relation DD","Reset to default","--SELECT--",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().contains("--SELECT"));
		s.selectByVisibleText("Father");Thread.sleep(200);
		log("Relation DD","Final value 'Father' for save","Father",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("Father"));
		System.out.println("=================================================");
		System.out.println("PB4 - RELATION DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


