package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.MailType;
import Guerrilla.GuerrillaMailPage;
import Window.Site;
import Window.WindowManager;


public class ResetPasswordTest extends BaseTest {
	
	@Test
	public void TC10() {

	    System.out.println("TC10 - Reset password shows error if the new password is same as current");
	    
	    Account account = Accounts.ACCOUNT_CHANGE_PASSWORD.toAccount();
	    
	    String email = account.getUsername();
	    String password = account.getPassword();

	    String emailName = email.split("@")[0];
	    String hostName  = email.split("@")[1];
	    
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();

	    mailPage.open();

	    WindowManager.save(Site.GUERRILLA_MAIL);

	    mailPage.useCreatedEmail(emailName, hostName);

	    System.out.println("Temp mail: " + email);

	    WindowManager.openNew(Site.RAILWAY);
	    
	    step("1. Navigate to QA Railway Login page");
	    LoginPage loginPage = new LoginPage();
	    loginPage.open();

	    step("2. Click on <Forgot Password page> link");
	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();

	    step("3. Enter the email address of the activated account");
	    step("4. Click on <Send Instructions> button");
	    
	    resetPage.enterEmail(email);
	    
	    step("5. Login to the mailbox (the same mailbox when creating account)"); 
	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    step("6. Open email with subject contaning <Please reset your password> and the email of the account at step 3");
	    String resetUrl = mailPage.openNewestMailAndGetLink(MailType.RESET_PASSWORD);

	    String mailToken = mailPage.getResetTokenFromMail();

	    System.out.println("Reset URL: " + resetUrl);

	    step("7. Click on reset link");
	    ResetPasswordPage resetPwdPage = ResetPasswordPage.open(resetUrl);

	    step("8. Input same password into 2 fields <new password> and <confirm password>");
	    step("9. Click Reset Password");
	    resetPwdPage.changeNewPassword(password, password);
	    
	    String uiToken = resetPage.getResetTokenFieldDisplay();

	    String actualMsg = resetPwdPage.getResetResultMessage();

	    String expectedMsg = "The new password cannot be the same with the current password";
	    
	    step("Verify: Redirect to Railways page and Form <Password Change Form> is shown with the reset password token.");
	    Assert.assertEquals(uiToken, mailToken, "Token is not the same with mail token");

	    step("Verify: Message <The new password cannot be the same with the current password> is shown");
	    Assert.assertEquals(actualMsg, expectedMsg, "Reset password error message is not displayed as expected");
	}

	@Test
	public void TC11() {

	    System.out.println("TC11 - Reset password shows error if confirm password doesn't match");
	    
	    Account account = Accounts.ACCOUNT_CHANGE_PASSWORD.toAccount();
	    
	    String email = account.getUsername();
	    String password = account.getPassword();

	    String emailName = email.split("@")[0];
	    String hostName  = email.split("@")[1];
	    
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();

	    mailPage.open();

	    WindowManager.save(Site.GUERRILLA_MAIL);

	    mailPage.useCreatedEmail(emailName, hostName);

	    System.out.println("Temp mail: " + email);

	    WindowManager.openNew(Site.RAILWAY);

	    step("1. Navigate to QA Railway Login page");
	    LoginPage loginPage = new LoginPage();
	    loginPage.open();

	    step("2. Click on <Forgot Password page> link");
	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();
	    
	    step("3. Enter the email address of the activated account");
	    step("4. Click on <Send Instructions> button");
	    
	    resetPage.enterEmail(email);
	    
	    step("5. Login to the mailbox (the same mailbox when creating account)"); 
	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    step("6. Open email with subject contaning <Please reset your password> and the email of the account at step 3");
	    String resetUrl = mailPage.openNewestMailAndGetLink(MailType.RESET_PASSWORD);

	    String mailToken = mailPage.getResetTokenFromMail();

	    System.out.println("Reset URL: " + resetUrl);

	    step("7. Click on reset link");
	    ResetPasswordPage resetPwdPage = ResetPasswordPage.open(resetUrl);

	    step("8. Input same password into 2 fields <new password> and <confirm password>");
	    step("9. Click Reset Password");

	    resetPwdPage.changeNewPassword(password, "confirmpassword");
	    
	    String uiToken = resetPage.getResetTokenFieldDisplay();

	    String actualMsgAbove = resetPwdPage.getResetResultMessage();
	    String actualMsgNextCfrm = resetPwdPage.getConfirmPasswordErrorMsgDisplay();
	    
	    String expectedMsgAbove = "Could not reset password. Please correct the errors and try again.";
	    String expectedMsgNextCfrm = "The password confirmation did not match the new password.";
	    
	    step("Verify: Redirect to Railways page and Form <Password Change Form> is shown with the reset password token.");
	    Assert.assertEquals(uiToken, mailToken, "Token is not the same with mail token");

	    step("Error message <Could not reset password. Please correct the errors and try again.> displays above the form.");
	    Assert.assertEquals(actualMsgAbove, expectedMsgAbove, "Error message above is not displayed as expected");
	    
	    step("Error message <The password confirmation did not match the new password.> displays next to the confirm password field.");
	    Assert.assertEquals(actualMsgNextCfrm, expectedMsgNextCfrm, "Error message next to confirm password is not displayed as expected");
	}

}
