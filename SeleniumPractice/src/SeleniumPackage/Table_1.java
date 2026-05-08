package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Table_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		System.setProperty("webdriver.chrome.driver",
				"D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.automationtesting.co.uk/tables.html");
		
		List<WebElement> head = driver.findElements(By.xpath("//*[@class='sortable']/thead/tr/th"));
		System.out.println("Total Header is : "+ head.size());
		
		for(WebElement head1:head)
		{
			System.out.print( head1.getText() + " | ");
			
		}
		
		List<WebElement> Rows = driver.findElements(By.xpath("//*[@class='sortable']/tbody/tr"));
		for(WebElement Rows1:Rows)
		{
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println(Rows1.getText()+"  |  ");
			   //System.out.println("|");
		}
		
		for(int i=1;i<2;i++)
		{
			for(int j=1;j<7;j++)
			{
			String value = driver.findElement(By.xpath("//*[@class='sortable']//tr["+i+"]/td["+j+"]")).getText();
			System.out.print(value + " | ");

			}
		}

	}

}
