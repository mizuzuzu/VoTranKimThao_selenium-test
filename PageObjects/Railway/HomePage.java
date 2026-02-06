package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	
	// Locators
	private final By lblHomeTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabFAQ = By.xpath("//a[@href='/Page/FAQ.cshtml']");
    private final By tabBookTicket = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
	
	//Elements
    protected WebElement getLblHomeTitle() {
        return Constant.WEBDRIVER.findElement(lblHomeTitle);
    }
    
	protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
	
	protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
	
	protected WebElement getTabFAQ() {
        return Constant.WEBDRIVER.findElement(tabFAQ);
    }
	
	protected WebElement getTabBookTicket() {
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }
    
	
	//Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public void gotoFAQPage() {
        Utilities.click(tabFAQ);
    }
	
	public void gotoBookTicketPage() {
        Utilities.click(tabBookTicket);
    }
	
	public String getWelcomeMessage() {
	    return Utilities.getTextByJS(lblWelcomeMessage).trim();
	}
	
	public void Logout() {
        Utilities.click(tabLogout);
    }
	
	public boolean isHomePageDisplayed() {
	    return Utilities.isDisplayed(lblHomeTitle);
	}

    public boolean isLogoutDisplayed() {
        return Utilities.isDisplayed(tabLogout);
    }

}
