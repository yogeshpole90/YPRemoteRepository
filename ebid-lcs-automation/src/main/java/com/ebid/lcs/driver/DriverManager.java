package com.ebid.lcs.driver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.ebid.lcs.config.ConfigManager;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigManager.get("browser");
            if (browser == null || browser.trim().isEmpty()) {
                browser = "chrome";
            }
            switch (browser.trim().toLowerCase()) {
                case "edge":
                    String edgePath = ConfigManager.get("edgdriverpath");
                    if (edgePath != null && !edgePath.trim().isEmpty()) {
                        System.setProperty("webdriver.edge.driver", edgePath);
                    }
                    driver = new EdgeDriver();
                    break;
                default:
                    String chromePath = ConfigManager.get("chrdriverpath");
                    if (chromePath != null && !chromePath.trim().isEmpty()) {
                        System.setProperty("webdriver.chrome.driver", chromePath);
                    }
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
