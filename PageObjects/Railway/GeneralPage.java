package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class GeneralPage {
	
	// Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabFAQ = By.xpath("//a[@href='/Page/FAQ.cshtml']");
    private final By tabBookTicket = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabMyTicket = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabTimetable = By.xpath("//a[@href='TrainTimeListPage.cshtml']");
    
  
    // Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
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
	
	protected WebElement getTabMyTicket() {
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }
	
	protected WebElement getTabTimetable() {
        return Constant.WEBDRIVER.findElement(tabTimetable);
    }

    // Methods
    
    public LoginPage gotoLoginPage() {
        Utilities.click(tabLogin);
        return new LoginPage();
    }
    
    public RegisterPage gotoRegisterPage() {
        Utilities.click(tabRegister);
        return new RegisterPage();
    }
    
    
	public BookTicketPage gotoBookTicketPage() {
        Utilities.click(tabBookTicket);
        
        return new BookTicketPage();
    }
	
	public TicketManagePage gotoMyTicketPage() {
        Utilities.click(tabMyTicket);
        
        return new TicketManagePage();
    }
	
	public TimetablePage gotoTimetablePage() {
        Utilities.click(tabTimetable);
        
        return new TimetablePage();
    }
	
	public void gotoFAQPage() {
        Utilities.click(tabFAQ);
    }
	
	public HomePage Logout() {
        Utilities.click(tabLogout);
        
        return new HomePage();
    }
	
	public boolean isLogoutDisplayed() {
        return Utilities.isDisplayed(tabLogout);
    }

}
