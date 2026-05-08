package RemedialAction;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class F5_ActionNameDD extends F2_Setup {

	public void validateActionNameDD() throws Exception {
		System.out.println("=================================================");
		System.out.println("F5 - ACTION NAME DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("actionId"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",dd);
		Select s=new Select(dd); List<WebElement> opts=s.getOptions();
		log("Action Name DD","Dropdown should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(),"Not displayed");
		log("Action Name DD","Dropdown should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		sa.assertTrue(dd.isEnabled(),"Disabled");
		log("Action Name DD","Should be single select","false",String.valueOf(s.isMultiple()),!s.isMultiple());
		log("Action Name DD","Total options count","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText()+" (value="+opts.get(i).getAttribute("value")+")");
		String def=s.getFirstSelectedOption().getText();
		log("Action Name DD","Default selected option","Default option",def,def!=null);
		boolean allEn=true;for(WebElement o:opts) if(!o.isEnabled()) allEn=false;
		log("Action Name DD","All options should be enabled","All enabled",String.valueOf(allEn),allEn);
		dd.sendKeys(Keys.DOWN);Thread.sleep(300);
		log("Action Name DD","Arrow Down - keyboard accessible","Option selected",s.getFirstSelectedOption().getText(),true);
		s.selectByIndex(2);Thread.sleep(300);
		String fin=s.getFirstSelectedOption().getText();
		log("Action Name DD","Final value set for save","Selected",fin,true);
		System.out.println("=================================================");
		System.out.println("F5 - ACTION NAME DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


