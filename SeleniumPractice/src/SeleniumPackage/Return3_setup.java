package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Return3_setup extends Return1_prop {

    public void setuped() throws InterruptedException {

        driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1/");

        driver.findElement(By.id("loginId")).sendKeys("Dora");
        driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
        driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
        
        driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
        
        Thread.sleep(2000);
        driver.findElement(By.id("userLogin")).click();
        
        
        


    }
}