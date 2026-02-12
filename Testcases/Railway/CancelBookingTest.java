package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelBookingTest extends BaseTest {
	@Test
	public void TC16()  {

	    System.out.println("TC16 - User can cancel a ticket");
	    
	    Account account = Accounts.VALID_USER_04.toAccount();
	    Ticket newTicket = Tickets.TC16.toTicket();
	    
	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Login with a valid account");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    homePage = loginPage.login(account, HomePage.class);

	    step("3. Book a ticket");
	    BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();

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
