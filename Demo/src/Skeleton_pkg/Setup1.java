package Skeleton_pkg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Setup1 {

    protected WebDriver driver;

    @BeforeClass
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver",
                "D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Browser Started");
    }

	/*
	 * @AfterClass public void closeBrowser() {
	 * 
	 * driver.quit(); System.out.println("Browser Closed"); }
	 */
}