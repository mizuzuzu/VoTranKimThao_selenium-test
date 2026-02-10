package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.SeatType;

public class BookTicketTest extends BaseTest{
	@Test
	public void TC12()  {

	    System.out.println("TC12 - User can book 1 ticket at a time");
	  
	    HomePage homePage = new HomePage();
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Login with a valid account ");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    HomePage pageAfterLogin = loginPage.login(Constant.VALID_USER_02, HomePage.class);
	    step("3. Click on <Book ticket> tab");
	    
	    BookTicketPage bookTicketPage = pageAfterLogin.gotoBookTicketPage();

	    step("4. Select the next 2 days from <Depart date>");
	    step("5. Select Depart from <Nha Trang> and Arrive at <Huế>");
	    step("6. Select <Soft bed with air conditioner> for <Seat type>");
	    step("7. Select <1> for <Ticket amount>");
	    Ticket newTicket = new Ticket(Utilities.getNextDay(5), "Nha Trang", "Huế", "Soft bed with air conditioner", "1");
	    		
	    step("8. Click on <Book ticket> button");
	    bookTicketPage.bookTicket(newTicket);

	    step("Verify: Message <Ticket booked successfully!> displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    Ticket actual = bookTicketPage.getBookedTicketInfo();

	    Assert.assertEquals(actual.getDepartDate(), newTicket.getDepartDate(), "Wrong Date");
	    Assert.assertEquals(actual.getDepartFrom(), newTicket.getDepartFrom(), "Wrong Depart");
	    Assert.assertEquals(actual.getArriveAt(), newTicket.getArriveAt(), "Wrong Arrive");
	    Assert.assertEquals(actual.getSeatType(), newTicket.getSeatType(), "Wrong Seat Type");
	    Assert.assertEquals(actual.getTicketAmount(), newTicket.getTicketAmount(), "Wrong Amount");
	}
	
	@Test
	public void TC13()  {

	    System.out.println("TC13 - User can book many tickets at a time");
	    
	    HomePage homePage = new HomePage();
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Login with a valid account ");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    HomePage pageAfterLogin = loginPage.login(Constant.VALID_USER_03, HomePage.class);

	    step("3. Click on <Book ticket> tab"); 
	    BookTicketPage bookTicketPage = pageAfterLogin.gotoBookTicketPage();

	    step("4. Select the next 25 days from <Depart date>");
	    step("5. Select Depart from <Nha Trang> and Arrive at <Sài Gòn>");
	    step("6. Select <Soft seat with air conditioner> for <Seat type>");
	    step("7. Select <5> for <Ticket amount>");
	    Ticket newTicket = new Ticket(Utilities.getNextDay(28), "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");
	    		
	    step("8. Click on <Book ticket> button");
	    bookTicketPage.bookTicket(newTicket);

	    step("Verify: Message <Ticket booked successfully!> displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    Ticket actual = bookTicketPage.getBookedTicketInfo();

	    Assert.assertEquals(actual.getDepartDate(), newTicket.getDepartDate(), "Wrong Date");
	    Assert.assertEquals(actual.getDepartFrom(), newTicket.getDepartFrom(), "Wrong Depart");
	    Assert.assertEquals(actual.getArriveAt(), newTicket.getArriveAt(), "Wrong Arrive");
	    Assert.assertEquals(actual.getSeatType(), newTicket.getSeatType(), "Wrong Seat Type");
	    Assert.assertEquals(actual.getTicketAmount(), newTicket.getTicketAmount(), "Wrong Amount");
	}
	
	@Test
	public void TC14()  {

	    System.out.println("TC14 - User can check price of ticket from Timetable");
	 
	    HomePage homePage = new HomePage();

	    step("1. Navigate to QA Railway Website");

	    homePage.open();
	    
	    step("2. Login with a valid account");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    HomePage pageAfterLogin = loginPage.login(Constant.VALID_USER_03, HomePage.class);

	    step("3. Click on <Timetable> tab");
	    TimetablePage timeTablePage = pageAfterLogin.gotoTimetablePage();

	    step("4. Click on <check price> link of the route from <Đà Nẵng> to <Sài Gòn>");
	    TicketPricePage checkPricePage = timeTablePage.gotoCheckPrice("Đà Nẵng", "Sài Gòn");

	    step("Verify: <Ticket Price> page is loaded.");
	    Assert.assertTrue(checkPricePage.isTicketPricePageDisplayed(),"User is NOT redirected to Ticket Price page");
	    
	    step("Ticket table shows <Ticket price from Đà Nẵng to Sài Gòn>.");
	    String actualTPtableName = checkPricePage.getTicketPriceTitle();
	    String expectedTPtableName = "Ticket price from Đà Nẵng to Sài Gòn";
	    
	    Assert.assertEquals(actualTPtableName, expectedTPtableName, "Title on Ticket Table is not showed as expected" );

	    step("Price for each seat displays correctly: "
	    		+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
	    for (SeatType seat : SeatType.values()) {

	        String actualSeatPrice = checkPricePage.getPriceBySeat(seat.getCode());

	        Assert.assertEquals(actualSeatPrice, seat.getPrice(), "Wrong price for " + seat.getCode());
	    }
	}
	
	@Test
	public void TC15()  {

	    System.out.println("TC15 - User can book ticket from Timetable");
	    
	    HomePage homePage = new HomePage();
	    
	    step("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    step("2. Login with a valid account");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    HomePage pageAfterLogin = loginPage.login(Constant.VALID_USER_03, HomePage.class);

	    step("3. Click on <Timetable> tab");
	    TimetablePage timeTablePage = pageAfterLogin.gotoTimetablePage();

	    step("4. Click on book ticket of route <Quảng Ngãi> to <Huế>");
	    String departFrom = "Quảng Ngãi";
	    String arriveAt = "Huế";
	    BookTicketPage bookTicketPage = timeTablePage.gotoBookTicket(departFrom, arriveAt );
	    
	    step("Verify: Book ticket form is shown with the corrected <depart from> and <Arrive at>");
	    String actualDepartFromDisplay = bookTicketPage.getSelectedDepartFrom();
	    Assert.assertEquals(actualDepartFromDisplay, departFrom, "Depart from field on booking form is not showed as table");

	    String actualArriveAtDisplay = bookTicketPage.getSelectedArriveAt();
	    Assert.assertEquals(actualArriveAtDisplay, arriveAt, "Arrive at field on booking form is not showed as table");
	    
	    step("5. Select Depart date = tomorrow");
	    step("6. Select Ticket amount = 5");
	    step("7. Click on <Book ticket> button");
	    
	    Ticket newTicket = new Ticket(Utilities.getNextDay(1), "Soft seat with air conditioner", "5");

	    bookTicketPage.bookTicketWithPresetRoute(newTicket);

	    step("Verify: Message <Ticket booked successfully!> displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    Ticket actual = bookTicketPage.getBookedTicketInfo();

	    Assert.assertEquals(actual.getDepartDate(), newTicket.getDepartDate(), "Wrong Date");
	    Assert.assertEquals(actual.getDepartFrom(), newTicket.getDepartFrom(), "Wrong Depart");
	    Assert.assertEquals(actual.getArriveAt(), newTicket.getArriveAt(), "Wrong Arrive");
	    Assert.assertEquals(actual.getSeatType(), newTicket.getSeatType(), "Wrong Seat Type");
	    Assert.assertEquals(actual.getTicketAmount(), newTicket.getTicketAmount(), "Wrong Amount");
	}

}
