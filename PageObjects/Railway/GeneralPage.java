package Railway;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class GeneralPage {
	
	// Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabFAQ = By.xpath("//a[@href='/Page/FAQ.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
  

    // Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }

    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }

    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    
    protected WebElement getTabFAQ() {
        return Constant.WEBDRIVER.findElement(tabFAQ);
    }
    
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }


    // Methods
    public String getWelcomeMessage() {

        WebDriverWait wait = new WebDriverWait(
                Constant.WEBDRIVER,
                Duration.ofSeconds(10)
        );

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(lblWelcomeMessage)
        );

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;

        String text = (String) js.executeScript(
                "return arguments[0].textContent;",
                element
        );

        return text.trim();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }
    
    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    
    
    public void gotoFAQPage() {
        this.getTabFAQ().click();
    }
    
    public void clickLogout() {
        this.getTabLogout().click();
    }

    public boolean isLogoutDisplayed() {
        try {
            return getTabLogout().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
