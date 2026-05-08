package ActionDocMap_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ADM5_DocumentName extends ADM2_Login {

	public void validateDocumentName() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM5 - DOCUMENT NAME FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement dn = driver.findElement(By.id("documentName"));
		log("Document Name","Field should be visible","true",String.valueOf(dn.isDisplayed()),dn.isDisplayed());
		sa.assertTrue(dn.isDisplayed(),"Not displayed");
		log("Document Name","Field should be enabled","true",String.valueOf(dn.isEnabled()),dn.isEnabled());
		sa.assertTrue(dn.isEnabled(),"Disabled");

		dn.clear();
		log("Document Name","Field should be empty initially","Empty","'"+dn.getAttribute("value")+"'",dn.getAttribute("value").isEmpty());

		dn.clear();dn.sendKeys("Test Document");Thread.sleep(200);
		log("Document Name","Enter text 'Test Document'","Test Document",dn.getAttribute("value"),dn.getAttribute("value").equals("Test Document"));

		dn.clear();Thread.sleep(200);
		log("Document Name","Clear field - should be empty","Empty","'"+dn.getAttribute("value")+"'",dn.getAttribute("value").isEmpty());

		dn.clear();dn.sendKeys("@#$%^&*()!~");Thread.sleep(200);
		log("Document Name","Enter special chars '@#$%^&*()!~'","@#$%^&*()!~",dn.getAttribute("value"),dn.getAttribute("value").equals("@#$%^&*()!~"));

		dn.clear();dn.sendKeys("1234567890");Thread.sleep(200);
		log("Document Name","Enter numeric '1234567890'","1234567890",dn.getAttribute("value"),dn.getAttribute("value").equals("1234567890"));

		dn.clear();dn.sendKeys("Doc_123_Test");Thread.sleep(200);
		log("Document Name","Enter alphanumeric 'Doc_123_Test'","Doc_123_Test",dn.getAttribute("value"),dn.getAttribute("value").equals("Doc_123_Test"));

		dn.clear();dn.sendKeys("     ");Thread.sleep(200);
		log("Document Name","Enter spaces only","Spaces","'"+dn.getAttribute("value")+"'",true);

		String ml=dn.getAttribute("maxlength");
		log("Document Name","Check maxlength attribute","Maxlength value",ml!=null?ml:"null (no limit)",true);

		dn.clear();dn.sendKeys("a".repeat(200));Thread.sleep(200);
		log("Document Name","Enter 200 chars - long text","Length check","Length="+dn.getAttribute("value").length(),true);

		// Final value for save
		dn.clear();dn.sendKeys("Asset Reposses");Thread.sleep(200);
		log("Document Name","Final value 'Asset Reposses' for save","Asset Reposses",dn.getAttribute("value"),true);

		System.out.println("=================================================");
		System.out.println("ADM5 - DOCUMENT NAME FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


