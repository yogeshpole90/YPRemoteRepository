package UserCreation_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UC4_EmpId extends UC2_Login {

	public void validateEmpId() throws Exception
	{
		WebElement container = driver.findElement(By.id("select2-employeeId-container"));
		String fn = "Employee ID";

		log(fn, "Should be visible on page", "true", String.valueOf(container.isDisplayed()), container.isDisplayed());
		sa.assertTrue(container.isDisplayed(), fn + " NOT visible.");

		container.click(); Thread.sleep(1000);
		List<WebElement> allOptions = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
		logInfo(fn, "Total Options Found", String.valueOf(allOptions.size()));

		StringBuilder sb = new StringBuilder();
		for (WebElement o : allOptions) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All Options", sb.toString());

		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(Keys.ESCAPE);
		Thread.sleep(500);

		logInfo(fn, "Action", "Searching for unique Employee ID...");

		boolean uniqueFound = false;
		int totalOptions = allOptions.size();

		for (int i = 0; i < totalOptions; i++) {
			container.click(); Thread.sleep(1000);
			List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
			if (i >= options.size()) break;

			String optText = options.get(i).getText();
			if (optText.equalsIgnoreCase("Select") || optText.contains("Select")) {
				driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(Keys.ESCAPE);
				Thread.sleep(500);
				logInfo(fn, "Index=" + i + " | '" + optText + "'", "SKIPPED - Placeholder");
				continue;
			}

			options.get(i).click();
			Thread.sleep(3000);

			boolean isDuplicate = false;
			try {
				List<WebElement> toasts = driver.findElements(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
				for (WebElement t : toasts) {
					if (t.getText().contains("Selected Employee ID already in use")) {
						isDuplicate = true;
						logInfo(fn, "Index=" + i + " | '" + optText + "'", "DUPLICATE - Trying next...");
						break;
					}
				}
			} catch (Exception e) { }

			if (isDuplicate) { Thread.sleep(3000); continue; }

			String selectedVal = container.getAttribute("title");
			log(fn, "Unique Employee ID found & selected", "Non-empty", selectedVal, true);
			uniqueFound = true;
			break;
		}

		if (!uniqueFound) {
			log(fn, "Unique Employee ID search", "Found", "NOT FOUND", false);
		}
	}
}
