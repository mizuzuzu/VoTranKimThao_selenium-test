package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage {
	// Locators
	private final By txtEmail = By.id("email");
	private final By txtPassword = By.id("password");
	private final By txtCfrmPassword = By.id("confirmPassword");
	private final By txtPID = By.id("pid");
	
	private final By btnRegister = By.xpath("//input[@title='Register']");
	
	private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblVlidtePassword = By.xpath("//label[@class='validation-error' and @for='password']");
	private final By lblVlidtePID = By.xpath("//label[@class='validation-error' and @for='pid']");
	private final By lblVerifySuccessMsg = By.xpath("//*[contains(text(),'Registration Confirmed')]");
	
	//Elements
	public WebElement getTxtEmail() {
	    return Constant.WEBDRIVER.findElement(txtEmail);
	}

	public WebElement getTxtPassword() {
	    return Constant.WEBDRIVER.findElement(txtPassword);
	}

	public WebElement getTxtCfrmPassword() {
	    return Constant.WEBDRIVER.findElement(txtCfrmPassword);
	}

	public WebElement getTxtPID() {
	    return Constant.WEBDRIVER.findElement(txtPID);
	}

	public WebElement getBtnRegister() {
	    return Constant.WEBDRIVER.findElement(btnRegister);
	}
	
	public WebElement getLblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
    }
	
	public WebElement getLblValidateErrorPassword() {
        return Constant.WEBDRIVER.findElement(lblVlidtePassword);
    }
	
	public WebElement getLblValidateErrorPID() {
        return Constant.WEBDRIVER.findElement(lblVlidtePID);
    }

	public WebElement getLblVerifySuccessMsg() {
        return Constant.WEBDRIVER.findElement(lblVerifySuccessMsg);
    }
	
	//Methods
	public RegisterPage register(String email, String password, String confirmPwd, String pid) {

	    Utilities.sendKeys(txtEmail, email);
	    Utilities.sendKeys(txtPassword, password);
	    Utilities.sendKeys(txtCfrmPassword, confirmPwd);
	    Utilities.sendKeys(txtPID, pid);

	    Utilities.scrollTo(btnRegister);
	    Utilities.click(btnRegister);

	    return this;
	}
	
	public RegisterPage register(Account user) {
	    return register(user.getUsername(), user.getPassword(), user.getConfirmPassword(), user.getPid());
	}

	public String getRegisterErrorMsg() {
	    return Utilities.getText(lblRegisterErrorMsg);
	}

	public String getValidatePasswordError() {
	    return Utilities.getText(lblVlidtePassword);
	}

	public String getValidatePIDError() {
	    return Utilities.getText(lblVlidtePID);
	}
	
	public String getVerifySuccessMsg() {
	    return Utilities.getText(lblVerifySuccessMsg);
	}

}
