package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class LoginPage extends GeneralPage {
	// Locators
    private final By txtUsername = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By hrLkForgotPwd = By.xpath("//a[contains(@href,'ForgotPassword')]");

    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
    }
    
    public WebElement getHrLkForgotPassword() {
        return Constant.WEBDRIVER.findElement(hrLkForgotPwd);
    }
    
    //Methods
    public LoginPage login(String username, String password) {

        this.getTxtUsername().clear();
        this.getTxtUsername().sendKeys(username);

        this.getTxtPassword().clear();
        this.getTxtPassword().sendKeys(password);

        this.getBtnLogin().click();

        return this; 
    }
    
    public String getLoginErrorMsg() {
        return Constant.WEBDRIVER
                .findElement(lblLoginErrorMsg)
                .getText()
                .trim();
    }

    public HomePage loginSuccess(String username, String password) {
    	//Submit login credentials
    	this.getTxtUsername().sendKeys(username);
    	this.getTxtPassword().sendKeys(password);
    	this.getBtnLogin().click();
    	
    	//Land on Home Page
    	return new HomePage();
    }
    
    public ResetPasswordPage gotoResetPwdPage() {
        this.getHrLkForgotPassword().click();
        return new ResetPasswordPage();
    }

}
