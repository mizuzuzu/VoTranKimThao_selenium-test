package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

	//Methods
	public RegisterPage register(String email, String password, String pid) {

        this.getTxtEmail().clear();
        this.getTxtEmail().sendKeys(email);

        this.getTxtPassword().clear();
        this.getTxtPassword().sendKeys(password);
        
        this.getTxtCfrmPassword().clear();
        this.getTxtCfrmPassword().sendKeys(password);
        
        this.getTxtPID().clear();
        this.getTxtPID().sendKeys(pid);

        this.getBtnRegister().click();

        return this; 
    }
	
	public String getRegisterErrorMsg() {
        return Constant.WEBDRIVER
                .findElement(lblRegisterErrorMsg)
                .getText()
                .trim();
    }
	
	public String getLblValidateErrorPasswordDisplay() {
        return Constant.WEBDRIVER
                .findElement(lblVlidtePassword)
                .getText()
                .trim();
    }
	
	public String getLblValidateErrorPIDDisplay() {
        return Constant.WEBDRIVER
                .findElement(lblVlidtePID)
                .getText()
                .trim();
    }

}
