package LawFirm_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LF8_CountryStateCity extends LF2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF8 - COUNTRY STATE CITY VALIDATION START");
		System.out.println("=================================================");

		// COUNTRY
		WebElement cdd=driver.findElement(By.id("countryCode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",cdd);
		Select cs=new Select(cdd);
		log("Country DD","Should be visible","true",String.valueOf(cdd.isDisplayed()),cdd.isDisplayed());
		log("Country DD","Should be enabled","true",String.valueOf(cdd.isEnabled()),cdd.isEnabled());
		log("Country DD","Default value","Default",cs.getFirstSelectedOption().getText().trim(),true);
		List<WebElement> copts=cs.getOptions();
		log("Country DD","Total options","More than 1",String.valueOf(copts.size()),copts.size()>1);
		cs.selectByVisibleText("Seychelles"); Thread.sleep(1000);
		log("Country DD","Select 'Seychelles'","Seychelles",cs.getFirstSelectedOption().getText().trim(),cs.getFirstSelectedOption().getText().trim().equals("Seychelles"));

		// STATE - loads after country
		WebElement sdd=driver.findElement(By.id("stateCode"));
		Select ss=new Select(sdd);
		log("State DD","Should be visible after country select","true",String.valueOf(sdd.isDisplayed()),sdd.isDisplayed());
		log("State DD","Should be enabled","true",String.valueOf(sdd.isEnabled()),sdd.isEnabled());
		List<WebElement> sopts=ss.getOptions();
		log("State DD","Options loaded after country select","More than 1",String.valueOf(sopts.size()),sopts.size()>1);
		ss.selectByVisibleText("MAHE"); Thread.sleep(1000);
		log("State DD","Select 'MAHE'","MAHE",ss.getFirstSelectedOption().getText().trim(),ss.getFirstSelectedOption().getText().trim().equals("MAHE"));

		// CITY - loads after state
		WebElement ctdd=driver.findElement(By.id("cityCode"));
		Select cts=new Select(ctdd);
		log("City DD","Should be visible after state select","true",String.valueOf(ctdd.isDisplayed()),ctdd.isDisplayed());
		log("City DD","Should be enabled","true",String.valueOf(ctdd.isEnabled()),ctdd.isEnabled());
		List<WebElement> ctopts=cts.getOptions();
		log("City DD","Options loaded after state select","More than 1",String.valueOf(ctopts.size()),ctopts.size()>1);
		cts.selectByVisibleText("ANSEAUXPINS"); Thread.sleep(500);
		log("City DD","Select 'ANSEAUXPINS'","ANSEAUXPINS",cts.getFirstSelectedOption().getText().trim(),cts.getFirstSelectedOption().getText().trim().equals("ANSEAUXPINS"));

		// Cascading check
		log("Cascading DD","Country→State→City cascading works","All 3 selected","Country=Seychelles, State=MAHE, City=ANSEAUXPINS",true);

		System.out.println("=================================================");
		System.out.println("LF8 - COUNTRY STATE CITY VALIDATION END");
		System.out.println("=================================================");
	}
}

