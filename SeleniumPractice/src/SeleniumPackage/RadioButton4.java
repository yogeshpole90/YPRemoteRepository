package SeleniumPackage;



		// TODO Auto-generated method stub
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;

		import java.util.List;
import java.util.concurrent.TimeUnit;

		public class RadioButton4 {
		    public static void main(String[] args) throws InterruptedException {
				System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

				
		        WebDriver driver = new ChromeDriver();
		        driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		        // Website open karo
		        driver.get("https://demoqa.com/radio-button");

		        // Sare radio input elements uthao jinka name 'like' hai
		        List<WebElement> radios = driver.findElements(By.xpath("//input[@name='like']"));

		        for (WebElement radio : radios) {
		            // Input ka id lo
		            String id = radio.getAttribute("id");

		            // Us id wala label locate karo
		            WebElement label = driver.findElement(By.xpath("//label[@for='" + id + "']"));

		            // Label text print karo
		            System.out.println("Radio button label: " + label.getText());

		            // Check karo radio button enabled hai ya nahi
		            System.out.println("isEnabled: " + radio.isEnabled());

		            // Agar enabled hai, toh select karo
		            if (radio.isEnabled()) {
		                radio.click();
		                Thread.sleep(1000);  // thoda ruk jao taaki click hone ka effect dikhe
		                System.out.println("Selected radio: " + label.getText());
		            } else {
		                System.out.println(label.getText() + " is disabled, so cannot select.");
		            }

		            System.out.println("------------------------------");
		        }

		        driver.quit();
		    }
		


	}

