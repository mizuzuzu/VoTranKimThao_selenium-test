package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

	@Test
	public void TC06() {
		
	    System.out.println("TC06 - Verify user is redirected to Home page after logging out");

	    Account account = Accounts.VALID_USER_01.toAccount();
	    
	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
        homePage.open();

        step("2. Click on <Login> tab");
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    homePage = loginPage.login(account,HomePage.class);
	    
	    step("3. Click on <FAQ> tab");
	    homePage.gotoFAQPage();
	    
	    step("4. Click on <Log out> tab");
	    homePage.Logout();
	    
	    step("Verify: Home page displays.");
	    Assert.assertTrue(homePage.isHomePageDisplayed(),"User is NOT redirected to HomePage after logout");
	    
	    step("Verify: <Log out> tab is disappeared.");
	    Assert.assertFalse(homePage.isLogoutDisplayed(), "The Logout tab is still displayed after logout.");
	}

}
