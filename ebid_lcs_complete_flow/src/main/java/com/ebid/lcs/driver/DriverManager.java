package com.ebid.lcs.driver;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.ebid.lcs.config.ConfigManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            String browser = ConfigManager.get("browser");
            if (browser == null || browser.trim().isEmpty()) {
                browser = "chrome";
            }
            WebDriver driver;
            switch (browser.trim().toLowerCase()) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if ("true".equalsIgnoreCase(ConfigManager.get("headless"))) {
                        edgeOptions.addArguments("--headless");
                    }
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if ("true".equalsIgnoreCase(ConfigManager.get("headless"))) {
                        chromeOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    public static void quit() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
