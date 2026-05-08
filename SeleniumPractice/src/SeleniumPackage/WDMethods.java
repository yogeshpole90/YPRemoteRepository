package SeleniumPackage;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WDMethods {

	public static void main(String[] args) throws Exception {
		
		//Browser proerty defiend + Path of instance driver
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(); //Launching
		
		//1 maximize 
		driver.manage().window().maximize();
		
		
		//1. full screen
		//driver.manage().window().fullscreen();
		
		//2get Method
		//driver.get("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		
		//3 navigate same as get
		driver.navigate().to("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		
		//3.getwindow handle
		String ss = driver.getWindowHandle();
		System.out.println(ss);
		
		//4 getwindowhandles method
		//Set<String> tt = driver.getWindowHandles();
		
		//5 gettitle as return title
		String uu = driver.getTitle();
		System.out.println(uu);
		
		//6 getpagesource  //return HTML DOM code same as F12
		//String vv = driver.getPageSource();
	//	System.out.println(vv);
		
		//7 getcurrenturl  return url
		String ww = driver.getCurrentUrl();
		System.out.println(ww);
		
		//size of window
	Dimension xx = driver.manage().window().getSize();
		System.out.println(xx);

		//minimize
		//driver.manage().window().minimize();
		
		//8 close
		Thread.sleep(3000);
		driver.close();
		
		// 9 quit
		Thread.sleep(3000);
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
				
	}

}
