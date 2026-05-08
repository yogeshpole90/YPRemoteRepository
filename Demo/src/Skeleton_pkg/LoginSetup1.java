package Skeleton_pkg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSetup1 {

    WebDriver driver;

    public void login() {
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://example.com");
        System.out.println("Login setup done");
    }
}