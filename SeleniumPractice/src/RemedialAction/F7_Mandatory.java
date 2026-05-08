package RemedialAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F7_Mandatory extends F2_Setup {

	public void validateMandatory() throws Exception {
		System.out.println("=================================================");
		System.out.println("F7 - MANDATORY FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement saveBtn=driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",saveBtn);
		Thread.sleep(300);
		saveBtn.click();
		Thread.sleep(500);

		String toast=getToastMsg();
		log("Mandatory Check","Save without filling any field","Error toast should appear",toast.isEmpty()?"No toast":toast,!toast.isEmpty());
		sa.assertTrue(!toast.isEmpty(),"Mandatory message not shown");

		System.out.println("=================================================");
		System.out.println("F7 - MANDATORY FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


