package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class ResetPasswordPage {
	// Locators
    private final By txtEmail = By.id("email");
    private final By txtNewPwd = By.id("newPassword");
    private final By txtCfmPwd = By.id("confirmPassword");
    private final By txtRsToken = By.id("resetToken");
    
    private final By btnSendIns = By.xpath("//input[@value='Send Instructions']");
    private final By btnResetPwd = By.xpath("//input[@title='Reset password']");
    
    private final By lblRsSuccesMsg = By.xpath("//p[@class='message success']");
    private final By lblRsErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblVldPwdMsg = By.xpath("//label[@class='validation-error' and contains(text(),'confirmation')]");

    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(txtNewPwd);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtCfmPwd);
    }
    
    public WebElement getTxtResetTokenDisplay() {
        return Constant.WEBDRIVER.findElement(txtRsToken);
    }
    
    public WebElement getBtnSenInstructions() {
        return Constant.WEBDRIVER.findElement(btnSendIns);
    }

    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(btnResetPwd);
    }

    public WebElement getLblResetPwdSuccesMsg() {
        return Constant.WEBDRIVER.findElement(lblRsSuccesMsg);
    }
    
    public WebElement getLblResetPwdErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblRsErrorMsg);
    }
    
    public WebElement getConfirmPasswordErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblVldPwdMsg);
    }
    
    //Methods
    
    public static ResetPasswordPage open(String url) {

        Utilities.openDynamicLink(url);

        return new ResetPasswordPage();
    }

    public ResetPasswordPage enterEmail(String email) {

        Utilities.waitForVisible(txtEmail);
        Utilities.sendKeys(txtEmail, email);
        
        Utilities.scrollTo(btnSendIns);
        Utilities.click(btnSendIns);

        return this;
    }
    
    public ResetPasswordPage changeNewPassword(String password, String confirmPwd) {

        Utilities.waitForVisible(txtNewPwd);
        Utilities.waitForVisible(txtCfmPwd);

        Utilities.sendKeys(txtNewPwd, password);
        Utilities.sendKeys(txtCfmPwd, confirmPwd);
        
        Utilities.scrollTo(btnResetPwd);
        Utilities.click(btnResetPwd);

        return this;
    }

    public String getResetResultMessage() {

        if (Utilities.isDisplayed(lblRsSuccesMsg)) {

            return Utilities.getText(lblRsSuccesMsg);

        } else {

            return Utilities.getText(lblRsErrorMsg);
        }
    }

    public String getConfirmPasswordErrorMsgDisplay() {

        return Utilities.getText(lblVldPwdMsg);
    }
    
    public String getResetTokenFieldDisplay() {

        WebElement el = Utilities.waitForVisible(txtRsToken);

        return el.getAttribute("value").trim();
    }



}
