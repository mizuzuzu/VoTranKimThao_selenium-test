package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.MailType;
import Guerrilla.GuerrillaMailPage;
import Window.Site;
import Window.WindowManager;

public class CreateAccountTest extends BaseTest{
	@Test
	public void TC07() {

	    System.out.println("TC07 - User can't create account with an already in-use email");

	    HomePage homePage = new HomePage();
	    Account alreadyUsedEmail = new Account(Constant.VALID_USER_01.getUsername(), "12345678", "12345678", "12345678");
	    step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Register> tab");
	    RegisterPage registerPage = homePage.gotoRegisterPage();
	    
	    step("3. Enter information of the created account in Pre-condition");
	    step("4. Click on <Register> button");
	    registerPage.register(alreadyUsedEmail);
	    
	    step("Verify: Home page displays.");
	    String actualMsg = registerPage.getRegisterErrorMsg();
	    String expectedMsg = "This email address is already in use.";

	    Assert.assertEquals(actualMsg,  expectedMsg,"Error message is not displayed as expected");
	}

	
	@Test
    public void TC08() {
        System.out.println("TC08 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
	    Account emptyPwdPid = new Account("awdawdwdwq@pokemail.net", "", "", "");
	    
	    step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Register> tab");
	    RegisterPage registerPage = homePage.gotoRegisterPage();
	    
	    step("3. Enter valid email address and leave other fields empty");
	    step("4. Click on <Register> button");
	    registerPage.register(emptyPwdPid);

	    String actualMsg = registerPage.getRegisterErrorMsg();
	    String actualPasswordVlid = registerPage.getValidatePasswordError();
	    String actualPIDVlid = registerPage.getValidatePIDError();

	    String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
	    String expectedVlidPwdError = "Invalid password length.";
	    String expectedVlidPIDError = "Invalid ID length.";
	    

	    step("Verify: Message <There're errors in the form. Please correct the errors and try again.> appears above the form.");
	    Assert.assertEquals(actualMsg, expectedMsg,"Error message is not displayed as expected");
        
	    step("Verify: Next to password fields, error message <Invalid password length.> displays");
        Assert.assertEquals(actualPasswordVlid, expectedVlidPwdError, "Error message for Password is not displayed as expected");

        step("Verify: Next to PID field, error message <Invalid ID length.> displays");
        Assert.assertEquals(actualPIDVlid, expectedVlidPIDError, "Error message for PID is not displayed as expected");
    }
	
	@Test
	public void TC09() {

	    System.out.println("TC09 - User can create and activate account");

	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();

	    WindowManager.save(Site.GUERRILLA_MAIL);

	    String tempEmail = mailPage.useRandomEmail();

	    System.out.println("Temp mail: " + tempEmail);

	    WindowManager.openNew(Site.RAILWAY);

	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
        homePage.open();
        
        step("Verify: Home page is shown with guide containing href <create an account> to <Register> page");
        Assert.assertTrue(homePage.isRegisterLinkDisplayed(),"There is no <create an account> link to <Register> page displays on Home Page ");

        step("2. Click on <Create an account>");
	    RegisterPage registerPage = homePage.gotoRegisterPageLink();

	    step("Verify: Register page is shown");
	    Assert.assertTrue(registerPage.isRegisterPageDisplayed(),"User is NOT redirected to Register Page");
	    
	    step("3. Enter valid information into all fields");
	    step("4. Click on <Register> button");
	    registerPage.register(tempEmail, "Valid@Password", "Valid@Password", Constant.PID);
	    
	    step("Verify: <Thank you for registering your account> is shown");
	    Assert.assertTrue(registerPage.isThankYouRegisterDisplayed(),"Title Thank you is not displayed after register");

	    step("5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
	    step("6. Login to the mailbox");
	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    step("7. Open email with subject containing <Please confirm your account>  and the email of the new account at step 3");
	    String confirmUrl = mailPage.openNewestMailAndGetLink(MailType.VERIFY_ACCOUNT);

	    System.out.println("Confirm URL: " + confirmUrl);

	    step("8. Click on the activate link");
	    Utilities.openDynamicLink(confirmUrl);

	    step("Verify: Redirect to Railways page and message <Registration Confirmed! You can now log in to the site> is shown");
	    String actualMsg = registerPage.getVerifySuccessMsg();
	    String expectedMsg = "Registration Confirmed! You can now log in to the site";

	    Assert.assertEquals(actualMsg, expectedMsg, "Confirm message is not displayed as expected");
	}

}
