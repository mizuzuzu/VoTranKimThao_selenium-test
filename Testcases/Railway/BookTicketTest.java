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
	    
	    //3 ngay tu hom nay
	    Ticket newTicket = new Ticket(Utilities.getNextDay(3), "Nha Trang", "Huế", "Soft bed with air conditioner", "1");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_02);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    BookTicketPage bookTicketPage = homeAfterLogin.gotoBookTicketPage();

	    bookTicketPage.bookTicket(newTicket);

	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    bookTicketPage.assertBookedTicketInfo(newTicket);
	}
	
	@Test
	public void TC13()  {

	    System.out.println("TC13 - User can book many tickets at a time");
	    
	    //25 ngay tu hom nay
	    Ticket newTicket = new Ticket(Utilities.getNextDay(25), "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_02);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    BookTicketPage bookTicketPage = homeAfterLogin.gotoBookTicketPage();

	    bookTicketPage.bookTicket(newTicket);

	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    bookTicketPage.assertBookedTicketInfo(newTicket);
	}
	
	@Test
	public void TC14()  {

	    System.out.println("TC14 - User can check price of ticket from Timetable");
	 
	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_02);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    TimetablePage timeTablePage = homeAfterLogin.gotoTimetablePage();

	    TicketPricePage checkPricePage = timeTablePage.gotoCheckPrice("Đà Nẵng", "Sài Gòn");
	    
	    //Ticket price page verify
	    Assert.assertTrue(checkPricePage instanceof TicketPricePage, "Check price failed - Not redirected to TicketPage");
	    
	    //Table title verify
	    String actualTPtableName = checkPricePage.getTicketPriceTitle();
	    String expectedTPtableName = "Ticket price from Đà Nẵng to Sài Gòn";
	    
	    Assert.assertEquals(actualTPtableName, expectedTPtableName, "Title on Ticket Table is not showed as expected" );

	    //Table seat price verify
	    for (SeatType seat : SeatType.values()) {

	        String actualSeatPrice = checkPricePage.getPriceBySeat(seat.getCode());

	        Assert.assertEquals(actualSeatPrice, seat.getPrice(), "Wrong price for " + seat.getCode());
	    }
	}
	
	@Test
	public void TC15()  {

	    System.out.println("TC15 - User can book ticket from Timetable");
	    
	    //ko the book tomorrow vi mac dinh he thong deu cach 3 ngay tinh tu hom nay
	    Ticket newTicket = new Ticket(Utilities.getNextDay(3), "Soft seat with air conditioner", "5");
	    String departFrom = "Quảng Ngãi";
	    String arriveAt = "Huế";

	    HomePage homePage = new HomePage();
	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();
	    GeneralPage pageAfterLogin = loginPage.login(Constant.VALID_USER_03);

	    Assert.assertTrue(pageAfterLogin instanceof HomePage, "Login failed - Not redirected to HomePage");

	    HomePage homeAfterLogin = (HomePage) pageAfterLogin;

	    TimetablePage timeTablePage = homeAfterLogin.gotoTimetablePage();

	    BookTicketPage bookTicketPage = timeTablePage.gotoBookTicket(departFrom, arriveAt );
	    
	    //field verify
	    String actualDepartFromDisplay = bookTicketPage.getSelectedDepartFrom();
	    Assert.assertEquals(actualDepartFromDisplay, departFrom, "Depart from field on booking form is not showed as table");

	    String actualArriveAtDisplay = bookTicketPage.getSelectedArriveAt();
	    Assert.assertEquals(actualArriveAtDisplay, arriveAt, "Arrive at field on booking form is not showed as table");
	    
	    //ticket
	    bookTicketPage.bookTicketWithPresetRoute(newTicket);

	    String actualMsg = bookTicketPage.getSuccessMessage();
	    String expectedMsg = "Ticket booked successfully!";

	    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");

	    bookTicketPage.assertBookedTicketInfo(newTicket);
	}

}
