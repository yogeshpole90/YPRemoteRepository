package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NavigationPage extends BasePage{
	Actions act;
	
	public NavigationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath="//*[@class='item-nav']/div/div")
	WebElement lnk_hamburgerMenu;

	@FindBy(xpath="(//li[@id = 'COMMONCOLLECTORLIST'])/a")
	WebElement lnk_allCaseList;

	@FindBy (xpath="//input[@type='search']")
	WebElement txt_search;
	
	@FindBy(xpath="//td[text()='433']")
	WebElement lnk_case;
	
	public void clickBurger()
	{
		lnk_hamburgerMenu.click();

	}
	public void clickAllCaseList()
	{
		
		lnk_allCaseList.click();
		
	}
	public boolean search(String search)
	{
		txt_search.sendKeys(search);
		return txt_search.isDisplayed();
	}
	public void clickCase()
	{
		act = new Actions(driver);
		act.doubleClick(lnk_case).build().perform();
	}
	
	
	
	


}
