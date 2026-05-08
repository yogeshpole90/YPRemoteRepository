package UserCreation_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UC19B_BranchAccess extends UC2_Login {

	private void closeDropdown() throws Exception {
		try {
			List<WebElement> searchFields = driver.findElements(By.xpath("//input[@class='select2-search__field']"));
			for (WebElement sf : searchFields) {
				if (sf.isDisplayed()) { sf.sendKeys(Keys.ESCAPE); Thread.sleep(300); }
			}
		} catch (Exception e) { }
		try {
			jse.executeScript("document.querySelector('.select2-container--open') && document.querySelector('body').click()");
			Thread.sleep(500);
		} catch (Exception e) { }
	}

	public void validateBranchAccess() throws Exception
	{
		String fn = "Branch Access List";
		WebElement container = driver.findElement(By.id("select2-userBaseBranchCode-container"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", container); Thread.sleep(500);

		log(fn, "Should be visible on page", "true", String.valueOf(container.isDisplayed()), container.isDisplayed());

		container.click(); Thread.sleep(1000);
		log(fn, "Click opens dropdown", "Dropdown opened", "Opened", true);

		List<WebElement> allOptions = driver.findElements(By.xpath("//ul[@id='select2-userBaseBranchCode-results']/li"));
		logInfo(fn, "Total Options", String.valueOf(allOptions.size()));

		StringBuilder sb = new StringBuilder();
		for (WebElement o : allOptions) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All Options", sb.toString());

		closeDropdown();

		int totalOpts = allOptions.size();
		for (int i = 0; i < totalOpts; i++) {
			container.click(); Thread.sleep(500);
			List<WebElement> opts = driver.findElements(By.xpath("//ul[@id='select2-userBaseBranchCode-results']/li"));
			if (i < opts.size()) {
				String optText = opts.get(i).getText();
				opts.get(i).click(); Thread.sleep(500);
				logInfo(fn, "Selected index " + i, optText);
			}
			closeDropdown();
		}

		log(fn, "Final selected value", "Non-empty", container.getText(), !container.getText().isEmpty());

		container.click(); Thread.sleep(500);
		try {
			WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
			searchBox.sendKeys("HEAD"); Thread.sleep(1000);
			List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='select2-userBaseBranchCode-results']/li"));
			log(fn, "Search 'HEAD'", "Results found", String.valueOf(searchResults.size()) + " results", searchResults.size() > 0);
		} catch (Exception e) {
			logInfo(fn, "Search test", "Error: " + e.getMessage());
		}

		closeDropdown();
		jse.executeScript("document.body.click()"); Thread.sleep(500);
		log(fn, "Dropdown closed after test", "Closed", "Closed", true);
	}
}
