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

        String actualMsg = loginPage
                .loginSuccess(Constant.VALID_USERNAME, Constant.VALID_PASSWORD)
                .getWelcomeMessage();


        String expectedMsg = "Welcome " + Constant.VALID_USERNAME; 

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    
    
    @Test
    public void TC02() {

        System.out.println("TC02 - User cannot login with blank username textbox");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage
                .login("", Constant.VALID_PASSWORD)
                .getLoginErrorMsg();

        String expectedMsg = "There was a problem with your login and/or errors exist in your form." ;

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
    
    @Test
    public void TC03() {

        System.out.println("TC03 - User cannot log into Railway with invalid password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage
                .login(Constant.VALID_USERNAME, Constant.INVALID_PASSWORD)
                .getLoginErrorMsg();

        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
    
    @Test
    public void TC04() {

        System.out.println("TC04 - System shows warning after many invalid logins");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <= 4; i++) {

            loginPage = loginPage.login(Constant.LOCK_TEST_USERNAME, Constant.LOCK_TEST_PASSWORD);

            Assert.assertEquals(loginPage.getLoginErrorMsg(), "Invalid username or password. Please try again.", "Error message is not displayed as expected");
        }

        loginPage = loginPage.login(Constant.LOCK_TEST_USERNAME, Constant.LOCK_TEST_PASSWORD);

        Assert.assertEquals(loginPage.getLoginErrorMsg(), 
        		"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.", 
        		"Error message is not displayed as expected");
    }

    
    @Test
    public void TC05() {

        System.out.println("TC05 - User cannot login with inactive account");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage
                .login(Constant.INACTIVED_USERNAME, Constant.INVALID_PASSWORD)
                .getLoginErrorMsg();

        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected for inactive account");
    }


}
