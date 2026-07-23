package com.agat.los.driver;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.agat.los.config.ConfigManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            String browser = ConfigManager.get("browser");
            if (browser == null || browser.trim().isEmpty()) browser = "chrome";

            WebDriver driver;
            switch (browser.trim().toLowerCase()) {
                case "edge":
                    String edgePath = ConfigManager.get("edgdriverpath");
                    if (edgePath != null && !edgePath.trim().isEmpty())
                        System.setProperty("webdriver.edge.driver", edgePath);
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if ("true".equalsIgnoreCase(ConfigManager.get("headless")))
                        edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("-inprivate");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    String chromePath = ConfigManager.get("chrdriverpath");
                    if (chromePath != null && !chromePath.trim().isEmpty())
                        System.setProperty("webdriver.chrome.driver", chromePath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if ("true".equalsIgnoreCase(ConfigManager.get("headless")))
                        chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--incognito");
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
