package Legal_Diary_Pkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD3_Frame extends LD2_Login {

	public void frame() throws Exception
	{
		// Legal Diary tab click
		WebElement legaldiary = driver.findElement(By.xpath("//a[contains(text(),'Legal Diary')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legaldiary);
		Thread.sleep(1000);
		act.doubleClick(legaldiary).build().perform();

		Thread.sleep(2000);

		// Switch to Legal Diary frame
		driver.switchTo().frame("getLegalDiaryDataFrame");
		System.out.println("=================================================");
		System.out.println("Switched to Legal Diary Frame: getLegalDiaryDataFrame");
	}
}
