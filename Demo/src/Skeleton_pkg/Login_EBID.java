package Skeleton_pkg;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Login_EBID {

    WebDriver driver;

    // ✅ Constructor
    public Login_EBID(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws Exception {

        driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

        driver.findElement(By.id("loginId")).sendKeys("infraadmin");
        driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
        driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
        driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

        driver.findElement(By.id("userLogin")).click();
    }
}