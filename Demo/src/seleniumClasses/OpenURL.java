package seleniumClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenURL {

	public static void main(String[] args) throws Exception {
		
		//setting property for browser
		//Passing CD path
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	   
		//Launching instance of Chrome Browser
		WebDriver driver = new ChromeDriver(); 
		

		driver.manage().window().maximize();
		
		//Launching URL of Application using get() method
	   driver.get("https://www.facebook.com");
	   
	   //to wait long milis
	   Thread.sleep(3000);
		
	   //new url opens after 3sec
	   driver.get("https://www.flipkart.com");
	   
	   
	   //navigate to refresh
	   Thread.sleep(3000);
	   driver.navigate().refresh();
	   
	   //navigagte back
	   Thread.sleep(3000);
	   driver.navigate().back();
	   
	   //navigate fwd
	   Thread.sleep(3000);
	   driver.navigate().forward();
	   
	 //navigagte back
	   Thread.sleep(2000);
	   driver.navigate().back();
	   
	   //get url
	   Thread.sleep(2000);
	   String url = driver.getCurrentUrl();
	   System.out.println(url);
	   

	   //get title
	   Thread.sleep(2000);
	   String title=driver.getTitle();
	   System.out.println(title);

	   
	   //Closing browser
	   Thread.sleep(2000);
	   driver.close();
	   
	   //Closing all browser instance
	   Thread.sleep(2000);
	   driver.quit();
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	
	
	}

}
