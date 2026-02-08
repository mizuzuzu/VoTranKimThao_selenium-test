package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.MailType;
import Guerrilla.GuerrillaMailPage;
import Window.Site;
import Window.WindowManager;


public class ResetPasswordTest extends BaseTest {
	
	@Test
	public void TC10() {

	    System.out.println("TC10 - Reset password shows error if the new password is same as current");
	    
	    //tach mail 
	    
	    String email = Constant.ACCOUNT_CHANGE_PASSWORD.getUsername();
	    String password = Constant.ACCOUNT_CHANGE_PASSWORD.getPassword();

	    String emailName = email.split("@")[0];
	    String hostName  = email.split("@")[1];
	    
	    //mo guerrilla len roi set up mail
	    
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();

	    mailPage.open();

	    WindowManager.save(Site.GUERRILLA_MAIL);

	    mailPage.useCreatedEmail(emailName, hostName);

	    System.out.println("Temp mail: " + email);
	    
	    //nhan forgot password de nhap email

	    WindowManager.openNew(Site.RAILWAY);

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();

	    resetPage.enterEmail(email);
	    
	    //ve lai guerrilla de lay link reset

	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    String resetUrl = mailPage.openNewestMailAndGetLink(MailType.RESET_PASSWORD);
	    
	    //lay token tu mail
	    String mailToken = mailPage.getResetTokenFromMail();

	    System.out.println("Reset URL: " + resetUrl);
	    
	    //mo url roi set up password y nhu cu

	    ResetPasswordPage resetPwdPage = ResetPasswordPage.open(resetUrl);

	    resetPwdPage.changeNewPassword(password, password);
	    
	    String uiToken = resetPage.getResetTokenFieldDisplay();

	    String actualMsg = resetPwdPage.getResetResultMessage();

	    String expectedMsg = "The new password cannot be the same with the current password";
	    
	    Assert.assertEquals(uiToken, mailToken, "Token is not the same with mail token");

	    Assert.assertEquals(actualMsg, expectedMsg, "Reset password error message is not displayed as expected");
	}



	@Test
	public void TC11() {

	    System.out.println("TC11 - Reset password shows error if confirm password doesn't match");

	    //tach mail 
	    
	    String email = Constant.ACCOUNT_CHANGE_PASSWORD.getUsername();
	    String password = Constant.ACCOUNT_CHANGE_PASSWORD.getPassword();

	    String emailName = email.split("@")[0];
	    String hostName  = email.split("@")[1];
	    
	    //mo guerrilla len roi set up mail
	    
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();

	    mailPage.open();

	    WindowManager.save(Site.GUERRILLA_MAIL);

	    mailPage.useCreatedEmail(emailName, hostName);

	    System.out.println("Temp mail: " + email);
	    
	    //nhan forgot password de nhap email

	    WindowManager.openNew(Site.RAILWAY);

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();

	    resetPage.enterEmail(email);
	    
	    //ve lai guerrilla de lay link reset

	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    String resetUrl = mailPage.openNewestMailAndGetLink(MailType.RESET_PASSWORD);
	    
	    //lay token tu mail
	    String mailToken = mailPage.getResetTokenFromMail();

	    System.out.println("Reset URL: " + resetUrl);
	    
	    //mo url roi set up password khac confirm password

	    ResetPasswordPage resetPwdPage = ResetPasswordPage.open(resetUrl);

	    resetPwdPage.changeNewPassword(password, "confirmpassword");
	    
	    String uiToken = resetPage.getResetTokenFieldDisplay();

	    String actualMsgAbove = resetPwdPage.getResetResultMessage();
	    String actualMsgNextCfrm = resetPwdPage.getConfirmPasswordErrorMsgDisplay();
	    
	    String expectedMsgAbove = "Could not reset password. Please correct the errors and try again.";
	    String expectedMsgNextCfrm = "The password confirmation did not match the new password.";
	    
	    Assert.assertEquals(uiToken, mailToken, "Token is not the same with mail token");

	    Assert.assertEquals(actualMsgAbove, expectedMsgAbove, "Error message above is not displayed as expected");
	    
	    Assert.assertEquals(actualMsgNextCfrm, expectedMsgNextCfrm, "Error message next to confirm password is not displayed as expected");
	}

}
