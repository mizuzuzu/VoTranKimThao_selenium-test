package Railway;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Guerrilla.GuerrillaMailPage;


public class ResetPasswordTest extends BaseTest {
	// web sap nen chua test thanh cong :(
	
	@Test
	public void TC10() {

	    System.out.println("TC10 - Reset password shows error if new password = old");

	    String password = "Valid@Password";

	    GuerrillaMailPage mailPage = new GuerrillaMailPage();


	    //Open Mail

	    mailPage.open();

	    String tempEmail = mailPage.useRandomEmail();

	    System.out.println("Temp mail: " + tempEmail);

	    String mailTab = Constant.WEBDRIVER.getWindowHandle();


	    //Register

	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(
	            tempEmail,
	            password,
	            password,
	            Constant.PID
	    );


	    //Activate

	    Constant.WEBDRIVER.switchTo().window(mailTab);

	    int oldCount = Constant.WEBDRIVER.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();

	    String confirmUrl = mailPage.openNewestMailAndGetLink(oldCount);

	    Constant.WEBDRIVER.get(confirmUrl);


	    //Forgot Password

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();

	    resetPage.enterEmail(tempEmail);


	    //Get Reset Mail

	    Constant.WEBDRIVER.switchTo().window(mailTab);

	    int oldCount2 =
	            Constant.WEBDRIVER.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();

	    String resetUrl = mailPage.openNewestMailAndGetLink(oldCount2);


	    //Reset

	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);

	    Constant.WEBDRIVER.get(resetUrl);

	    ResetPasswordPage resetPwdPage = new ResetPasswordPage();

	    resetPwdPage.changeNewPassword(password, password);


	    //Verify

	    String actualMsg = resetPwdPage.getResetResultMessage();

	    String expectedMsg =
	            "The new password cannot be the same with the current password";

	    Assert.assertEquals(actualMsg, expectedMsg);
	}


	@Test
	public void TC11() {

	    System.out.println("TC11 - Reset password shows error if confirm password doesn't match");

	    String password = "Valid@Password";
	    String newPassword = "New@Valid@Password";
	    String confirmPassword = "NewNewValid@Password";

	    GuerrillaMailPage mailPage = new GuerrillaMailPage();


	    //Open Mail

	    mailPage.open();

	    String tempEmail = mailPage.useRandomEmail();

	    System.out.println("Temp mail: " + tempEmail);

	    String mailTab = Constant.WEBDRIVER.getWindowHandle();



	    //Register

	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(
	            tempEmail,
	            password,
	            password,
	            Constant.PID
	    );



	    //Activate

	    Constant.WEBDRIVER.switchTo().window(mailTab);

	    int oldCount =
	            Constant.WEBDRIVER
	                    .findElements(By.xpath("//tbody[@id='email_list']/tr"))
	                    .size();

	    String confirmUrl =
	            mailPage.openNewestMailAndGetLink(oldCount);

	    Constant.WEBDRIVER.get(confirmUrl);



	    //Forgot Password

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();

	    resetPage.enterEmail(tempEmail);



	    //Get Reset Mail

	    Constant.WEBDRIVER.switchTo().window(mailTab);

	    int oldCount2 =
	            Constant.WEBDRIVER
	                    .findElements(By.xpath("//tbody[@id='email_list']/tr"))
	                    .size();

	    String resetUrl =
	            mailPage.openNewestMailAndGetLink(oldCount2);



	    //Reset

	    Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);

	    Constant.WEBDRIVER.get(resetUrl);

	    ResetPasswordPage resetPwdPage = new ResetPasswordPage();

	    resetPwdPage.changeNewPassword(newPassword, confirmPassword);



	    //verify
	    String actualMsg =
	            resetPwdPage.getResetResultMessage();

	    String expectedMsg =
	            "Could not reset password. Please correct the errors and try again.";

	    Assert.assertEquals(actualMsg, expectedMsg);


	    String confirmPwdMsg =
	            resetPwdPage.getConfirmPasswordErrorMsgDisplay();

	    Assert.assertEquals(
	            confirmPwdMsg,
	            "The password confirmation did not match the new password.",
	            "Confirm password error message is incorrect"
	    );
	}

}
