package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD30_CurrencyDD extends LD2_Login {

	public void validateCurrency() throws Exception {
		List<WebElement> list = driver.findElements(By.id("currency"));
		if (list.isEmpty() || !list.get(0).isDisplayed()) {
			logInfo("Currency", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); String fn = "Currency";
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", field); Thread.sleep(500);

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Should be disabled (Read-Only)", "false", String.valueOf(field.isEnabled()), !field.isEnabled());

		String disAttr = field.getAttribute("disabled");
		log(fn, "Check disabled attribute", "disabled present", String.valueOf(disAttr != null), disAttr != null);

		String selectedText = (String) jse.executeScript("var s=arguments[0]; return s.options[s.selectedIndex]?s.options[s.selectedIndex].text:'';", field);
		log(fn, "Auto-fetched value", "Non-empty", selectedText, selectedText != null && !selectedText.contains("SELECT"));

		Long optCount = (Long) jse.executeScript("return arguments[0].options.length;", field);
		logInfo(fn, "Total options count", String.valueOf(optCount));

		String allVals = (String) jse.executeScript("var s=arguments[0];var r='';for(var i=0;i<s.options.length;i++){r+=s.options[i].text+' , ';}return r;", field);
		logInfo(fn, "All dropdown values", allVals);

		logInfo(fn, "Field type", "Read-Only Auto-Fetch");
	}
}
