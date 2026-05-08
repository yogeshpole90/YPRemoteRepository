package RemedialAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F4_remact - Switch to Remedial Action Frame 
 * Kept for reference only.
 */
public class F4_remact extends F2_Setup {

	public void remact()
	{
		//parent frame
		//driver.switchTo().parentFrame();

		// Switch to PTP iframe
		driver.switchTo().frame("caseMstListPageFrame");
		//caseMstListPageFrame

	}

}
