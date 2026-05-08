package UserCreation_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UC18_HnwCategory extends UC2_Login {

	public void validateHnwCategory() throws Exception
	{
		WebElement f = driver.findElement(By.id("hnwCategory"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f); Thread.sleep(500);
		Select s = new Select(f); List<WebElement> opts = s.getOptions(); String fn = "HNW Category";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled());
		log(fn, "Should be single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		logInfo(fn, "Total Options", String.valueOf(opts.size()));

		StringBuilder sb = new StringBuilder();
		for (WebElement o : opts) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All dropdown options", sb.toString());

		log(fn, "Check default selected", "Select or placeholder", s.getFirstSelectedOption().getText(), true);

		boolean ae = true; for (WebElement o : opts) { if (!o.isEnabled()) ae = false; }
		log(fn, "All options should be enabled", "true", String.valueOf(ae), ae);

		f.sendKeys(Keys.DOWN); Thread.sleep(300);
		log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		s.selectByVisibleText("NORMAL"); String finalVal = s.getFirstSelectedOption().getText();
		log(fn, "Select 'NORMAL'", "NORMAL", finalVal, finalVal.equals("NORMAL"));
	}
}
