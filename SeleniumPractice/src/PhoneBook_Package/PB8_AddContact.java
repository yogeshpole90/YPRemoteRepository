package PhoneBook_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PB8_AddContact extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB8 - ADD CONTACT BUTTON VALIDATION START");
		System.out.println("=================================================");

		// Add Contact button
		WebElement addBtn=driver.findElement(By.id("addButton"));
		log("Add Contact","Should be visible","true",String.valueOf(addBtn.isDisplayed()),addBtn.isDisplayed());
		sa.assertTrue(addBtn.isDisplayed(),"Not displayed");
		log("Add Contact","Should be enabled","true",String.valueOf(addBtn.isEnabled()),addBtn.isEnabled());
		sa.assertTrue(addBtn.isEnabled(),"Disabled");

		// Button text
		String btnText=addBtn.getText().trim();
		log("Add Contact","Button text should be 'Add Contact'","Add Contact",btnText,btnText.contains("Add Contact"));

		// Click Add Contact
		addBtn.click();
		Thread.sleep(500);
		String toast=getSuccessToastMsg();
		String errToast=getToastMsg();
		if(!toast.isEmpty()){
			log("Add Contact","Click Add Contact with valid data","Success toast",toast,toast.toLowerCase().contains("success")||toast.toLowerCase().contains("saved")||toast.toLowerCase().contains("added"));
		} else if(!errToast.isEmpty()){
			log("Add Contact","Click Add Contact - error","Error toast",errToast,false);
		} else {
			log("Add Contact","Click Add Contact","Toast expected","No toast found",false);
		}

		// Data stored in grid check
		Thread.sleep(500);
		WebElement container=driver.findElement(By.id("contact-details-container"));
		log("Data Grid","Container should be visible","true",String.valueOf(container.isDisplayed()),container.isDisplayed());

		List<WebElement> rows=container.findElements(By.xpath(".//div[contains(@style,'grid-template-columns')]/div"));
		int totalCells=rows.size();
		int dataCells=totalCells-4; // minus 4 header cells
		boolean multiline=dataCells>4; // more than 1 data row (each row=4 cells)
		log("Data Grid","Data stored in multiline","Multiple rows","Total cells="+totalCells+", Data cells="+dataCells+", Rows="+(dataCells/4),multiline);

		// Print all rows
		System.out.println("----------------------------------------------");
		System.out.println("Grid Data:");
		for(int i=4;i<totalCells;i+=4){
			if(i+3<totalCells){
				System.out.println("  Row "+(i/4)+": "+rows.get(i).getText()+" | "+rows.get(i+1).getText()+" | "+rows.get(i+2).getText()+" | "+rows.get(i+3).getText());
			}
		}

		// Verify last added row has correct data
		if(dataCells>=4){
			int lastRow=totalCells-4;
			String rel=rows.get(lastRow).getText().trim();
			String name=rows.get(lastRow+1).getText().trim();
			String phone=rows.get(lastRow+2).getText().trim();
			String active=rows.get(lastRow+3).getText().trim();
			log("Data Grid","Last row Relation","Father",rel,rel.equals("Father"));
			log("Data Grid","Last row Contact Name","Test Contact",name,name.equals("Test Contact"));
			log("Data Grid","Last row Phone Number","9876543210",phone,phone.equals("9876543210"));
			log("Data Grid","Last row Is Active","Yes",active,active.equals("Yes"));
		}

		System.out.println("=================================================");
		System.out.println("PB8 - ADD CONTACT BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}


