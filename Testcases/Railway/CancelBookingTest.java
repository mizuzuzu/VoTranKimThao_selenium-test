package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class CancelBookingTest extends BaseTest {
	@Test
	public void TC16()  {

	    System.out.println("TC16 - User can cancel a ticket");
	    
	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Login with a valid account");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    HomePage pageAfterLogin = loginPage.login(Constant.VALID_USER_04, HomePage.class);

	    step("3. Book a ticket");
	    BookTicketPage bookTicketPage = pageAfterLogin.gotoBookTicketPage();
	    
	    Ticket newTicket = new Ticket(Utilities.getNextDay(3), "Nha Trang", "Huế", "Soft bed with air conditioner", "1");

	    bookTicketPage.bookTicket(newTicket);

	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";
	    
	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    step("4. Click on <My ticket> tab");
	    TicketManagePage myTicketPage = bookTicketPage.gotoMyTicketPage();
	    
	    step("5. Click on <Cancel> button of ticket which user want to cancel.");
	    step("6. Click on <OK> button on Confirmation message <Are you sure?>");
	    myTicketPage.clickCancelTicket(newTicket);
	    
	    step("Verify: The canceled ticket is disappeared.");
	    myTicketPage.verifyTicketDeleted(newTicket);
	    
	    
	}
}
