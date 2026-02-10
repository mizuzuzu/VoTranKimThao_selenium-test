package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class LoginTest extends BaseTest{

	@Test
	public void TC01() {

	    System.out.println("TC01 - User can log into Railway with valid username and password");

	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    step("3. Enter valid Email and Password");
	    step("4. Click on <Login> button");
	    HomePage homeAfterLogin = loginPage.login(Constant.VALID_USER_01,HomePage.class);

	    step("Verify: User is logged into Railway. Welcome user message is displayed.");
	    Assert.assertNotNull(homeAfterLogin, "Failed - HomePage is not display");

	    String actualMsg = homeAfterLogin.getWelcomeMessage();
	    String expectedMsg = "Welcome " + Constant.VALID_USER_01.getUsername();

	    Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}

	@Test
	public void TC02() {

	    System.out.println("TC02 - User cannot login with blank username textbox");

	    HomePage homePage = new HomePage();
	    Account blankUsername = new Account("", "123456");

	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    step("3. User doesn't type any words into <Username> textbox but enter valid information into <Password> textbox ");
	    step("4. Click on <Login> button");
	    LoginPage pageAfterLogin = loginPage.login(blankUsername,LoginPage.class);
	    
	    step("Verify: User can't login and message <There was a problem with your login and/or errors exist in your form.> appears.");
	    Assert.assertNotNull(pageAfterLogin, "Failed - Login page is not display");

	    String actualMsg = pageAfterLogin.getLoginErrorMsg();
	    String expectedMsg ="There was a problem with your login and/or errors exist in your form.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}

    @Test
    public void TC03() {

    	System.out.println("TC03- User cannot log into Railway with invalid password");

	    HomePage homePage = new HomePage();
	    Account invalidPwdUsername = new Account(Constant.VALID_USER_01.getUsername(), "invalid@Password");

	    step("1. Navigate to QA Railway Website");
	    homePage.open();

	    step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    step("3. Enter valid Email and invalid Password");
	    step("4. Click on <Login> button");
	    LoginPage pageAfterLogin = loginPage.login(invalidPwdUsername,LoginPage.class);

	    step("Verify: Error message <There was a problem with your login and/or errors exist in your form.> is displayed");
	    Assert.assertNotNull(pageAfterLogin, "Failed - Login page is not display");

	    String actualMsg = pageAfterLogin.getLoginErrorMsg();
	    String expectedMsg ="There was a problem with your login and/or errors exist in your form.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
    
    @Test
    public void TC04() {

        System.out.println("TC04 - System shows message when user enters wrong password many times");

        HomePage homePage = new HomePage();
        Account invalidAccount = new Account(Constant.VALID_USER_01.getUsername(), "invalid@Password");

        step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Login> tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        String expectedNormalMsg = "Invalid username or password. Please try again.";

        String expectedLimitMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        
        step("3. Enter valid information into <Username> textbox except <Password> textbox.");
        step("4. Click on <Login> button");
        step("5. Repeat step 3 and 4 three more times.");
        
        
        for (int i = 1; i <= 4; i++) {
        	LoginPage pageAfterLogin = loginPage.login(invalidAccount, LoginPage.class);
            Assert.assertNotNull(pageAfterLogin, "Attempt " + i + ": Failed - Login page is not display");
            
            step(" Verify: <Invalid username or password. Please try again> is shown");
            String actualMsg = pageAfterLogin.getLoginErrorMsg();
            Assert.assertEquals(actualMsg, expectedNormalMsg, "Attempt " + i + ": Error message is not displayed as expected");
        }

        step("Verify: User can't login and message <You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.> appears.");
        LoginPage pageAfterLogin = loginPage.login(invalidAccount, LoginPage.class);

        Assert.assertNotNull(pageAfterLogin, "Attempt 5: Failed - Login page is not display");
        
        String actualMsg5th = pageAfterLogin.getLoginErrorMsg();

        Assert.assertEquals(actualMsg5th, expectedLimitMsg, "Attempt 5: Error message is not displayed as expected");
    }

    
    @Test
    public void TC05() {

    	System.out.println("TC05- User can't login with an account hasn't been activated");

	    HomePage homePage = new HomePage();

	    step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    step("3. Enter username and password of account hasn't been activated.");
        step("4. Click on <Login> button");
	    LoginPage pageAfterLogin = loginPage.login(Constant.INACTIVED_USER,LoginPage.class);
	    
	    step(" Verify: <Invalid username or password. Please try again> is shown");
	    Assert.assertNotNull(pageAfterLogin, "Failed - Login page is not display");

	    String actualMsg = pageAfterLogin.getLoginErrorMsg();
	    String expectedMsg = "Invalid username or password. Please try again.";

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}


}
