package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LogoutTest extends BaseTest {

	@Test
	public void TC06() {

	    System.out.println("TC06 - Verify user is redirected to Home page after logging out");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    // Logout
	    homeAfterLogin.Logout();
	    
	    Utilities.waitForPageLoaded();

	    HomePage homeAfterLogout = new HomePage();

	    Assert.assertTrue(homeAfterLogout.isHomePageDisplayed(),"User is NOT redirected to HomePage after logout");
	    
	    Assert.assertFalse(homeAfterLogout.isLogoutDisplayed(), "The Logout tab is still displayed after logout.");
	}



}
