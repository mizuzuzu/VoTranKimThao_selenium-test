package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
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
    private void doLogin(Account user) {

        Utilities.sendKeys(txtUsername, user.getUsername());
        Utilities.sendKeys(txtPassword, user.getPassword());
        Utilities.click(btnLogin);
    }
    
    //hien error message thi o lai login page, ko thi o trang home
    public GeneralPage login(Account user) {

        doLogin(user);

        if (Utilities.isDisplayed(lblLoginErrorMsg)) {
            return this;
        }

        return new HomePage();
    }

    public String getLoginErrorMsg() {
        return Utilities.getText(lblLoginErrorMsg);
    }

    public ResetPasswordPage gotoResetPwdPage() {
        Utilities.click(hrLkForgotPwd);
        return new ResetPasswordPage();
    }

}
