package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class CancelBookingTest extends BaseTest {
	@Test
	public void TC16()  {

	    System.out.println("TC16 - User can cancel a ticket");
	    
	    //3 ngay tu hom nay
	    Ticket newTicket = new Ticket(Utilities.getNextDay(3), "Nha Trang", "Huế", "Soft bed with air conditioner", "1");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_04);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    BookTicketPage bookTicketPage = homeAfterLogin.gotoBookTicketPage();

	    bookTicketPage.bookTicket(newTicket);

	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";
	    
	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    TicketManagePage myTicketPage = bookTicketPage.gotoMyTicketPage();
	    
	    myTicketPage.clickCancelTicket(newTicket);
	    
	    myTicketPage.verifyTicketDeleted(newTicket);
	    
	    
	}
}
