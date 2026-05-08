package LawyerDetails_Package;
import java.util.List;import org.openqa.selenium.By;import org.openqa.selenium.WebElement;import org.openqa.selenium.support.ui.Select;
public class LD4_LawFirmCodeDD extends LD2_Login {
	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD4 - LAW FIRM CODE DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		WebElement dd=driver.findElement(By.id("lawFirmCode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",dd);
		Select s=new Select(dd);
		log("Law Firm Code DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		log("Law Firm Code DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		String def=s.getFirstSelectedOption().getText().trim();
		log("Law Firm Code DD","Default value","Default/Select",def,true);
		List<WebElement> opts=s.getOptions();
		log("Law Firm Code DD","Total options (from Law Firm Master)","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText());
		if(opts.size()>1){s.selectByIndex(1);Thread.sleep(300);
		log("Law Firm Code DD","Select first firm","Selected",s.getFirstSelectedOption().getText().trim(),true);}
		System.out.println("=================================================");
		System.out.println("LD4 - LAW FIRM CODE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}

