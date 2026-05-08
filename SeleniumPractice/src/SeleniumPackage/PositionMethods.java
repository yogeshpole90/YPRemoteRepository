package SeleniumPackage;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PositionMethods {

	public static void main(String[] args) {


		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//max
		//driver.manage().window().maximize();
		
		//1.naviagte
		driver.navigate().to("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		
		//getSize = actual size in pixel width*height
		Dimension aa = driver.manage().window().getSize();
		System.out.println(aa);
		
		//getPosition = top-left(0,0) then pixel point step.
		Point bb = driver.manage().window().getPosition();
		System.out.println(bb);
		
		//setsize to define convert window into desired size 
		 driver.manage().window().setSize(new Dimension(1500,900));
		 
		 //setposition
	  driver.manage().window().setPosition(new Point(400,400));
		
		
	}

}
