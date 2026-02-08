package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class LoginTest extends BaseTest{

	@Test
	public void TC01() {

	    System.out.println("TC01 - User can log into Railway with valid username and password");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_01);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    String actualMsg = homeAfterLogin.getWelcomeMessage();
	    String expectedMsg = "Welcome " + Constant.VALID_USER_01.getUsername();

	    Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}

	@Test
	public void TC02() {

	    System.out.println("TC02 - User cannot login with blank username textbox");

	    HomePage homePage = new HomePage();
	    Account blankUsername = new Account("", "123456");

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(blankUsername);

	    Assert.assertTrue(pageAfterLogin instanceof LoginPage,"Login should fail but user was not stayed on Login page");

	    LoginPage loginAfterFail = (LoginPage) pageAfterLogin;

	    String actualMsg = loginAfterFail.getLoginErrorMsg();
	    String expectedMsg ="There was a problem with your login and/or errors exist in your form.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}

    @Test
    public void TC03() {

    	System.out.println("TC03- User cannot log into Railway with invalid password ");

	    HomePage homePage = new HomePage();
	    Account invalidPwdUsername = new Account(Constant.VALID_USER_01.getUsername(), "invalid@Password");

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(invalidPwdUsername);

	    Assert.assertTrue(pageAfterLogin instanceof LoginPage,"Login should fail but user was not stayed on Login page");

	    LoginPage loginAfterFail = (LoginPage) pageAfterLogin;

	    String actualMsg = loginAfterFail.getLoginErrorMsg();
	    String expectedMsg ="There was a problem with your login and/or errors exist in your form.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
    
    @Test
    public void TC04() {

        System.out.println("TC04 - System shows message when user enters wrong password many times");

        HomePage homePage = new HomePage();
        Account invalidAccount = new Account(Constant.VALID_USER_01.getUsername(), "invalid@Password");

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String expectedNormalMsg = "Invalid username or password. Please try again.";

        String expectedLimitMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        
        for (int i = 1; i <= 4; i++) {
            GeneralPage page = loginPage.login(invalidAccount);
            Assert.assertTrue(page instanceof LoginPage, "Attempt " + i + ": Login should fail but user was not stayed on Login page");

            loginPage = (LoginPage) page;

            String actualMsg = loginPage.getLoginErrorMsg();
            Assert.assertEquals(actualMsg, expectedNormalMsg, "Attempt " + i + ": Error message is not displayed as expected");
        }

        //Lan thu 5
        GeneralPage page = loginPage.login(invalidAccount);

        Assert.assertTrue(page instanceof LoginPage, "Attempt 5: Login should fail but user was not stayed on Login page");

        LoginPage loginAfter5th = (LoginPage) page;
        String actualMsg5th = loginAfter5th.getLoginErrorMsg();

        Assert.assertEquals(actualMsg5th, expectedLimitMsg, "Attempt 5: Error message is not displayed as expected");
    }

    
    @Test
    public void TC05() {

    	System.out.println("TC05- User can't login with an account hasn't been activated");

	    HomePage homePage = new HomePage();

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.INACTIVED_USER);

	    Assert.assertTrue(pageAfterLogin instanceof LoginPage,"Login should fail but user was not stayed on Login page");

	    LoginPage loginAfterFail = (LoginPage) pageAfterLogin;

	    String actualMsg = loginAfterFail.getLoginErrorMsg();
	    String expectedMsg = "Invalid username or password. Please try again.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}


}
