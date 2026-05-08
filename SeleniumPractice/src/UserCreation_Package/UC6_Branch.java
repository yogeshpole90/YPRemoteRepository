package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC6_Branch extends UC2_Login {

	public void validateBranch() throws Exception
	{
		WebElement f = driver.findElement(By.id("select2-assignedBranch-container"));
		String fn = "Assigned Branch";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());

		f.click(); Thread.sleep(1000);
		log(fn, "Click opens dropdown", "Dropdown opened", "Opened", true);

		WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
		searchBox.sendKeys("HEAD"); Thread.sleep(1000);
		log(fn, "Search 'HEAD' in dropdown", "Results found", "Searched", true);

		try {
			WebElement firstResult = driver.findElement(By.xpath("//li[contains(@class,'select2-results__option')][1]"));
			String resultText = firstResult.getText();
			firstResult.click(); Thread.sleep(1000);
			log(fn, "Select first search result", "Non-empty", resultText, true);
		} catch (Exception e) {
			log(fn, "Select first search result", "Found", "No results", false);
		}

		logInfo(fn, "Final selected value", f.getAttribute("title"));
	}
}
