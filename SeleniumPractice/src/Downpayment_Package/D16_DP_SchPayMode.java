package Downpayment_Package;
import java.util.List; import org.openqa.selenium.By; import org.openqa.selenium.Keys; import org.openqa.selenium.WebElement; import org.openqa.selenium.support.ui.Select;
public class D16_DP_SchPayMode extends D2_DP_Login {
	public void validateSchPayMode() throws Exception {
		System.out.println("=================================================");
		System.out.println("D16 - SCHEDULE PAYMENT MODE VALIDATION START");
		System.out.println("=================================================");
		WebElement sp=driver.findElement(By.xpath("//select[@id='paymentMode1']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",sp);Thread.sleep(300);
		Select s=new Select(sp);List<WebElement> opts=s.getOptions();
		log("Sch Pay Mode","Dropdown should be visible","true",String.valueOf(sp.isDisplayed()),sp.isDisplayed());
		log("Sch Pay Mode","Dropdown should be enabled","true",String.valueOf(sp.isEnabled()),sp.isEnabled());
		log("Sch Pay Mode","Should be single select","false",String.valueOf(s.isMultiple()),!s.isMultiple());
		log("Sch Pay Mode","Total options count","More than 1",String.valueOf(opts.size()),opts.size()>1);
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for(int i=0;i<opts.size();i++) System.out.println("  ["+i+"] "+opts.get(i).getText());
		String def=s.getFirstSelectedOption().getText();
		log("Sch Pay Mode","Default selected option","Default option",def,def!=null);
		boolean allEn=true;for(WebElement o:opts) if(!o.isEnabled()) allEn=false;
		log("Sch Pay Mode","All options enabled","All enabled",String.valueOf(allEn),allEn);
		sp.sendKeys(Keys.DOWN);Thread.sleep(300);
		log("Sch Pay Mode","Arrow Down - keyboard accessible","Option selected",s.getFirstSelectedOption().getText(),true);
		s.selectByVisibleText("CASH");Thread.sleep(300);
		log("Sch Pay Mode","Final value 'CASH' for save","CASH",s.getFirstSelectedOption().getText(),s.getFirstSelectedOption().getText().equals("CASH"));
		System.out.println("=================================================");
		System.out.println("D16 - SCHEDULE PAYMENT MODE VALIDATION END");
		System.out.println("=================================================");
	}
}


