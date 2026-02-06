package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class BookTicketPage {
	//Locators
	private final By sltDepartDate = By.name("Date");
	private final By sltDepartFrom = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By sltArriveAt = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By sltSeatType = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By sltTicketAmount = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By btnBookTicket = By.xpath("//input[@type='submit']");
	
	// Elements
	public WebElement getselectDepartDate() {
	    return Constant.WEBDRIVER.findElement(sltDepartDate);
	}

	public WebElement getselectDepartFrom() {
	    return Constant.WEBDRIVER.findElement(sltDepartFrom);
	}

	public WebElement getselectArriveAt() {
	    return Constant.WEBDRIVER.findElement(sltArriveAt);
	}

	public WebElement getselectSeatType() {
	    return Constant.WEBDRIVER.findElement(sltSeatType);
	}

	public WebElement getselectTicketAmount() {
	    return Constant.WEBDRIVER.findElement(sltTicketAmount);
	}

	public WebElement getselectbtnBookTicket() {
	    return Constant.WEBDRIVER.findElement(btnBookTicket);
	}
	//Methods
	
	
}
