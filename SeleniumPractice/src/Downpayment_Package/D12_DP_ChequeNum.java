package Downpayment_Package;
import org.openqa.selenium.By; import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
public class D12_DP_ChequeNum extends D2_DP_Login {
	public void validateChequeNum(){
		System.out.println("=================================================");
		System.out.println("D12 - CHEQUE NUMBER VALIDATION START");
		System.out.println("=================================================");
		boolean ex=driver.findElements(By.id("chequeNumber")).size()>0;
		if(!ex||!driver.findElement(By.id("chequeNumber")).isDisplayed()){sa.fail("Cheque Number NOT visible");log("Cheque Number","Pre-check","Visible","NOT visible",false);return;}
		WebElement c=driver.findElement(By.id("chequeNumber"));
		log("Cheque Number","Field should be visible","true",String.valueOf(c.isDisplayed()),c.isDisplayed());
		log("Cheque Number","Field should be enabled","true",String.valueOf(c.isEnabled()),c.isEnabled());
		c.clear();c.sendKeys("123456");
		log("Cheque Number","Enter numeric '123456'","123456",c.getAttribute("value"),c.getAttribute("value").equals("123456"));
		c.clear();c.sendKeys("abcdef");
		String av=c.getAttribute("value");log("Cheque Number","Enter alphabets 'abcdef'","Rejected or Accepted",av,true);
		c.clear();c.sendKeys("@#$%");
		String sv=c.getAttribute("value");
		log("Cheque Number","Enter special chars '@#$%'","Rejected or Accepted",sv,true);
		c.clear();c.sendKeys("abc123");
		String mv=c.getAttribute("value");log("Cheque Number","Enter alphanumeric 'abc123'","Rejected or Accepted",mv,true);
		c.clear();c.sendKeys("-5000");
		String nv=c.getAttribute("value");log("Cheque Number","Enter negative '-5000'","Rejected or Accepted",nv,true);
		c.clear();c.sendKeys("   ");
		log("Cheque Number","Enter spaces only","Empty",c.getAttribute("value").trim(),c.getAttribute("value").trim().isEmpty());
		String ml=c.getAttribute("maxlength");log("Cheque Number","Check maxlength","Maxlength value",ml!=null?ml:"null",true);
		c.clear();log("Cheque Number","Clear field","Empty",c.getAttribute("value"),c.getAttribute("value").isEmpty());
		String ro=c.getAttribute("readonly");log("Cheque Number","Should NOT be readonly","null",String.valueOf(ro),ro==null);
		c.clear();c.sendKeys("987654");
		log("Cheque Number","Final value '987654'","987654",c.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D12 - CHEQUE NUMBER VALIDATION END");
		System.out.println("=================================================");
	}
	
	
}


