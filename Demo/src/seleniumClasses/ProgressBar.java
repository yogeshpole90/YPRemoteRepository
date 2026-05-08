package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProgressBar {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/progress-bar");

        Thread.sleep(2000);

        // Click Start
        driver.findElement(By.xpath("//*[text()='Start']")).click();

        WebElement progress = driver.findElement(By.xpath("//*[@id='progressBar']/div"));

        // Loop until progress >= 52
        while (true) {
            String progressStr = progress.getAttribute("aria-valuenow");
            int progressValue = Integer.parseInt(progressStr);
            System.out.println("Current Progress: " + progressValue + "%");

            if (progressValue >= 52) {
                driver.findElement(By.xpath("//*[text()='Stop']")).click();
                System.out.println("Progress stopped at: " + progressValue + "%");
                break;  // exit loop
            }

            Thread.sleep(50); // small delay to reduce CPU usage
        }

        // Optional: close browser
        Thread.sleep(2000);
        driver.quit();
    }
}