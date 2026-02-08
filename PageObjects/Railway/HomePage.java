package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	
	// Locators
	private final By lblHomeTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	
	//Elements
    protected WebElement getLblHomeTitle() {
        return Constant.WEBDRIVER.findElement(lblHomeTitle);
    }
    
	protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    } 
	
	//Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public String getWelcomeMessage() {
	    return Utilities.getTextByJS(lblWelcomeMessage).trim();
	}
	
	public boolean isHomePageDisplayed() {
	    return Utilities.isDisplayed(lblHomeTitle);
	}

}
