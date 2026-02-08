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
	    
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(alreadyUsedEmail);

	    String actualMsg = registerPage.getRegisterErrorMsg();

	    String expectedMsg = "This email address is already in use.";

	    Assert.assertEquals(actualMsg,  expectedMsg,"Error message is not displayed as expected");
	}

	
	@Test
    public void TC08() {
        System.out.println("TC08 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
	    Account emptyPwdPid = new Account("awdawdwdwq@pokemail.net", "", "", "");
	    
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(emptyPwdPid);

	    String actualMsg = registerPage.getRegisterErrorMsg();
	    String actualPasswordVlid = registerPage.getValidatePasswordError();
	    String actualPIDVlid = registerPage.getValidatePIDError();

	    String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
	    String expectedVlidPwdError = "Invalid password length";
	    String expectedVlidPIDError = "Invalid ID length";
	    

	    Assert.assertEquals(actualMsg, expectedMsg,"Error message is not displayed as expected");
        
        Assert.assertEquals(actualPasswordVlid, expectedVlidPwdError, "Error message for Password is not displayed as expected");

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
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(tempEmail, "Valid@Password", "Valid@Password", Constant.PID);

	    WindowManager.switchTo(Site.GUERRILLA_MAIL);

	    Utilities.waitForPageLoaded();

	    String confirmUrl = mailPage.openNewestMailAndGetLink(MailType.VERIFY_ACCOUNT);

	    System.out.println("Confirm URL: " + confirmUrl);

	    Utilities.openDynamicLink(confirmUrl);

	    String actualMsg = registerPage.getVerifySuccessMsg();
	    
	    String expectedMsg = "Registration Confirmed! You can now log in to the site.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Confirm message is not displayed as expected");
	}

}
