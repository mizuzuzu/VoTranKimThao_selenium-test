package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;

public class BookTicketPage extends GeneralPage {

    // Locators
    private final By sltDepartDate = By.name("Date");
    private final By sltDepartFrom = By.name("DepartStation");
    private final By sltArriveAt = By.name("ArriveStation");
    private final By sltSeatType = By.name("SeatType");
    private final By sltTicketAmount = By.name("TicketAmount");

    private final By btnBookTicket = By.xpath("//input[@type='submit']");

    private final By lblSuccessMsg = By.xpath("//h1[normalize-space()='Ticket booked successfully!']");

    private final By infoBookTicket = By.xpath("//table[contains(@class,'MyTable')]/tbody/tr[contains(@class,'OddRow')]");

    private final By colDepartFrom = By.xpath("./td[1]");
    private final By colArriveAt   = By.xpath("./td[2]");
    private final By colSeatType   = By.xpath("./td[3]");
    private final By colDepartDate = By.xpath("./td[4]");
    private final By colAmount     = By.xpath("./td[7]");


    //Elements
    public WebElement getInfoBookTicket() {
        return Constant.WEBDRIVER.findElement(infoBookTicket);
    }
    
    // Methods
    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType,String ticketAmount) {
    	

        Utilities.selectByVisibleText(sltDepartDate, departDate);
        Utilities.selectByVisibleText(sltDepartFrom, departFrom);

        Utilities.selectAfterReload(sltArriveAt, arriveAt);
        
        Utilities.selectByVisibleText(sltSeatType, seatType);
        Utilities.selectByVisibleText(sltTicketAmount, ticketAmount);

        Utilities.click(btnBookTicket);
    }
    
    public void bookTicket(Ticket ticket) {

        bookTicket(ticket.getDepartDate(), ticket.getDepartFrom(), ticket.getArriveAt(), ticket.getSeatType(), ticket.getTicketAmount());
    }
    
    //book ticket from timetable
    public void bookTicketWithPresetRoute(String departDate, String seatType, String ticketAmount) {

    	Utilities.selectByVisibleText(sltDepartDate, departDate);
    	Utilities.selectByVisibleText(sltSeatType, seatType);
    	Utilities.selectByVisibleText(sltTicketAmount, ticketAmount);

    	Utilities.click(btnBookTicket);
    }
    
    public void bookTicketWithPresetRoute(Ticket ticket) {

        bookTicketWithPresetRoute(ticket.getDepartDate(), ticket.getSeatType(), ticket.getTicketAmount());
    }

    public String getSuccessMessage() {
        return Utilities.getText(lblSuccessMsg);
    }
    
    //verify depart and arrive part after click book ticket from time table
    public String getSelectedDepartFrom() {
        return Utilities.getSelectedOptionText(sltDepartFrom);
    }

    public String getSelectedArriveAt() {
        return Utilities.getSelectedOptionText(sltArriveAt);
    }
    
    //verify ticket after booking
    private String getCellText(By column) {

        WebElement row = getInfoBookTicket();

        WebElement cell = row.findElement(column);

        return Utilities.getText(cell);
    }
    
    private void assertIfNotNull(String actual, String expected, String message) {

    	if (expected != null) {
    		Assert.assertEquals(actual, expected, message);
    	}
    }

    public void assertBookedTicketInfo(Ticket ticket) {
        assertIfNotNull(getCellText(colDepartDate), ticket.getDepartDate(), "Wrong Date");
        assertIfNotNull(getCellText(colDepartFrom), ticket.getDepartFrom(), "Wrong Depart");
        assertIfNotNull(getCellText(colArriveAt), ticket.getArriveAt(), "Wrong Arrive");
        assertIfNotNull(getCellText(colSeatType), ticket.getSeatType(), "Wrong Seat Type");
        assertIfNotNull(getCellText(colAmount), ticket.getTicketAmount(), "Wrong Amount");
    }


}
