package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class LogoutTest extends BaseTest {

    @Test
    public void TC06() {

        System.out.println("TC06 - User is redirected to Home page after logout");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        HomePage afterLoginHome = loginPage
                .loginSuccess(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);

        afterLoginHome.gotoFAQPage();

        afterLoginHome.clickLogout();

        Assert.assertFalse(afterLoginHome.isLogoutDisplayed(), "Logout tab is still displayed after logout");
    }
}
