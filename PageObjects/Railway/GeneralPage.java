package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class GeneralPage {
	
	// Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
  
    // Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
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

}
