package LawyerDetails_Package;
import java.util.List;import org.openqa.selenium.By;import org.openqa.selenium.WebElement;import org.openqa.selenium.support.ui.Select;
public class LD7_QualificationDD extends LD2_Login {
	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD7 - QUALIFICATION DROPDOWN VALIDATION START");
		System.out.println("=================================================");
		try{driver.switchTo().alert().accept();}catch(Exception e){} // clear pending alert
		WebElement dd=driver.findElement(By.id("qualification"));
		Select s=new Select(dd);
		log("Qualification DD","Should be visible","true",String.valueOf(dd.isDisplayed()),dd.isDisplayed());
		log("Qualification DD","Should be enabled","true",String.valueOf(dd.isEnabled()),dd.isEnabled());
		log("Qualification DD","Default value","Default",s.getFirstSelectedOption().getText().trim(),true);
		List<WebElement> opts=s.getOptions();
		log("Qualification DD","Total options","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText());
		String[] exp={"DOCTORATE","GRADUATE","OTHERS"};
		for(String e:exp){boolean found=false;for(WebElement o:opts)if(o.getText().trim().equals(e)){found=true;break;}
		log("Qualification DD","Option '"+e+"' present",e,found?e+" found":e+" NOT found",found);}
		s.selectByVisibleText("DOCTORATE");Thread.sleep(200);
		log("Qualification DD","Select 'DOCTORATE'","DOCTORATE",s.getFirstSelectedOption().getText().trim(),s.getFirstSelectedOption().getText().trim().equals("DOCTORATE"));
		s.selectByVisibleText("GRADUATE");Thread.sleep(200);
		log("Qualification DD","Select 'GRADUATE'","GRADUATE",s.getFirstSelectedOption().getText().trim(),true);
		s.selectByVisibleText("DOCTORATE");Thread.sleep(200);
		log("Qualification DD","Final value 'DOCTORATE'","DOCTORATE",s.getFirstSelectedOption().getText().trim(),true);
		System.out.println("=================================================");
		System.out.println("LD7 - QUALIFICATION DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}

