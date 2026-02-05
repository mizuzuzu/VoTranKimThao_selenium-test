package Railway;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class ResetPasswordPage {
	// Locators
    private final By txtEmail = By.id("email");
    private final By txtNewPwd = By.id("newPassword");
    private final By txtCfmPwd = By.id("confirmPassword");
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
    public ResetPasswordPage enterEmail(String email) {

        WebDriverWait wait =
                new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(15));

        JavascriptExecutor js =
                (JavascriptExecutor) Constant.WEBDRIVER;

        // Wait textbox email
        WebElement txtEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.txtEmail)
        );

        txtEmail.clear();
        txtEmail.sendKeys(email);

        // Wait button
        WebElement btnSend = wait.until(
                ExpectedConditions.presenceOfElementLocated(this.btnSendIns)
        );

        // Scroll tới button
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btnSend);

        // Đợi overlay biến mất (nếu có)
        wait.until(ExpectedConditions.elementToBeClickable(btnSend));

        try {
            btnSend.click();
        } catch (Exception e) {
            // Nếu vẫn bị chặn → dùng JS click
            js.executeScript("arguments[0].click();", btnSend);
        }

        return this;
    }


    
    public ResetPasswordPage changeNewPassword(String password, String confirmpwd) {

        WebDriverWait wait =
                new WebDriverWait(Constant.WEBDRIVER,
                        Duration.ofSeconds(25));

        // Đợi input
        WebElement newPwd = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtNewPwd));

        WebElement cfmPwd = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtCfmPwd));

        // Nhập password
        newPwd.clear();
        newPwd.sendKeys(password);

        cfmPwd.clear();
        cfmPwd.sendKeys(confirmpwd);


        // Đợi button xuất hiện
        WebElement btn = wait.until(
                ExpectedConditions.presenceOfElementLocated(btnResetPwd)
        );


        // Scroll tới button
        ((JavascriptExecutor) Constant.WEBDRIVER)
                .executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", btn);


        // Đợi overlay biến mất (nếu có)
        try {
            wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                    By.className("grippy-host"))
            );
        } catch (Exception e) {
            // ignore
        }


        // Đợi button thật sự enabled
        wait.until(d -> btn.isEnabled());


        // ===== TRY CLICK 1: Normal =====
        try {
            btn.click();
            return this;
        } catch (Exception e) {
            System.out.println("Normal click failed → try Actions");
        }


        // ===== TRY CLICK 2: Actions =====
        try {
            new Actions(Constant.WEBDRIVER)
                    .moveToElement(btn)
                    .pause(Duration.ofMillis(300))
                    .click()
                    .perform();

            return this;

        } catch (Exception e) {
            System.out.println("Actions click failed → try JS");
        }


        // ===== TRY CLICK 3: JS (Final backup) =====
        ((JavascriptExecutor) Constant.WEBDRIVER)
                .executeScript("arguments[0].click();", btn);

        return this;
    }


    
    public String getResetResultMessage() {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(15));

        try {
            WebElement success = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(lblRsSuccesMsg));

            return success.getText().trim();

        } catch (TimeoutException e) {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(lblRsErrorMsg));

            return error.getText().trim();
        }
    }


    public String getConfirmPasswordErrorMsgDisplay() {
        return Constant.WEBDRIVER.findElement(lblVldPwdMsg).getText().trim();
    }
    

}
