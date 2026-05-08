package Selenium_Package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenUrl1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

				//chrome browser property
				System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
				
				//launch browser instance
				WebDriver driver =new ChromeDriver();

	}

}
