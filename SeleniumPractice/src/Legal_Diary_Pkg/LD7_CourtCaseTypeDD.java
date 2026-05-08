package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD7_CourtCaseTypeDD extends LD2_Login {

	public void validateCourtCaseTypeDD() throws Exception {
		List<WebElement> list = driver.findElements(By.xpath("//select[@id='courtCaseType']"));
		if (list.isEmpty() || !list.get(0).isDisplayed()) {
			logInfo("Court Case Type", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); String fn = "Court Case Type";
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", field); Thread.sleep(500);

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Should be disabled (Read-Only)", "false", String.valueOf(field.isEnabled()), !field.isEnabled());

		String disAttr = field.getAttribute("disabled");
		log(fn, "Check disabled attribute", "disabled present", String.valueOf(disAttr != null), disAttr != null);

		String selectedText = (String) jse.executeScript("var s=arguments[0]; return s.options[s.selectedIndex]?s.options[s.selectedIndex].text:'';", field);
		log(fn, "Auto-fetched value from Court Case Ref", "Non-empty", selectedText, selectedText != null && !selectedText.equalsIgnoreCase("Select"));

		Long optCount = (Long) jse.executeScript("return arguments[0].options.length;", field);
		logInfo(fn, "Total options count", String.valueOf(optCount));

		String allVals = (String) jse.executeScript("var s=arguments[0];var r='';for(var i=0;i<s.options.length;i++){r+=s.options[i].text+' , ';}return r;", field);
		logInfo(fn, "All dropdown values", allVals);

		String selVal = (String) jse.executeScript("return arguments[0].value;", field);
		logInfo(fn, "Selected value attribute", selVal);

		logInfo(fn, "Field type", "Read-Only Auto-Fetch (value from Court Case Ref selection)");
	}
}
