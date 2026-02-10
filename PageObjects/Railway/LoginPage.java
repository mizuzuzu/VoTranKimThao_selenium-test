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
    
    private final By linkForgotPwd = By.xpath("//a[contains(@href,'ForgotPassword')]");

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
    
    public WebElement getLinkForgotPassword() {
        return Constant.WEBDRIVER.findElement(linkForgotPwd);
    }
    
    //Methods
    public LoginPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.lOGIN_RAILWAY_URL);
		return this;
	}
    
    private void doLogin(Account user) {

        Utilities.sendKeys(txtUsername, user.getUsername());
        Utilities.sendKeys(txtPassword, user.getPassword());
        
        Utilities.scrollTo(btnLogin);
        Utilities.click(btnLogin);
    }
    
    //hien error message thi o lai login page, ko thi o trang home
    public <T> T login(Account user, Class<T> pageClass) {

        doLogin(user);

        if (Utilities.isDisplayed(lblLoginErrorMsg)) {
            return pageClass.cast(this); // LoginPage
        }

        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getLoginErrorMsg() {
        return Utilities.getText(lblLoginErrorMsg);
    }

    public ResetPasswordPage gotoResetPwdPage() {
        Utilities.click(linkForgotPwd);
        return new ResetPasswordPage();
    }

}
