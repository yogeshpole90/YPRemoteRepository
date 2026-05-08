package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Return1_prop {

     WebDriver driver;   // 👈 class level driver
     Actions act;
     JavascriptExecutor jse;
     
    public void setproperty() {

        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();   // 👈 initialize same driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        
        
    }
}