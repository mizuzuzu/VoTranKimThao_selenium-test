package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class LogoutTest extends BaseTest {

	@Test
	public void TC06() {
		
	    System.out.println("TC06 - Verify user is redirected to Home page after logging out");

	    HomePage homePage = new HomePage();
	    step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    HomePage homeAfterLogin = loginPage.login(Constant.VALID_USER_01,HomePage.class);
	    
	    step("3. Click on <FAQ> tab");
	    homeAfterLogin.gotoFAQPage();
	    
	    step("4. Click on <Log out> tab");
	    HomePage homeAfterLogout = homeAfterLogin.Logout();
	    
	    step("Verify: Home page displays.");
	    Assert.assertTrue(homeAfterLogout.isHomePageDisplayed(),"User is NOT redirected to HomePage after logout");
	    
	    step("Verify: <Log out> tab is disappeared.");
	    Assert.assertFalse(homeAfterLogout.isLogoutDisplayed(), "The Logout tab is still displayed after logout.");
	}

}
