package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;

public class TicketManagePage extends GeneralPage {
	//Locators
	private final By tbtTickets = By.xpath("//table[contains(@class,'MyTable')]/tbody/tr[contains(@class,'OddRow')]");
	
	private int beforeDeleteCount = -1;

	//Elements
    public WebElement getInfoBookTicket() {
        return Constant.WEBDRIVER.findElement(tbtTickets);
    }
    
	//Methods
	
    public void clickCancelTicket(String depart, String arrive, String seatType, String departDate, String amount) {

    	By btnCancel = By.xpath(String.format(
    			"//table[contains(@class,'MyTable')]//tr[" + "td[2]='%s' and " + "td[3]='%s' and " + "td[4]='%s' and " + "td[5]='%s' and " + "td[9]='%s'" 
    					+ "]//input[@value='Cancel']",
    					depart, arrive, seatType, departDate, amount));

    	Utilities.click(btnCancel);
    	
    	Utilities.handleAlert(true);;
    }

    public void clickCancelTicket(Ticket ticket) {

    	beforeDeleteCount = countTicket(ticket);
        clickCancelTicket(
            ticket.getDepartFrom(),
            ticket.getArriveAt(),
            ticket.getSeatType(),
            ticket.getDepartDate(),
            ticket.getTicketAmount()
        );
    }
    
    public void verifyTicketDeleted(Ticket ticket) {

        Assert.assertTrue(beforeDeleteCount >= 0,"Must call cancelTicket() before verify");

        Utilities.waitForPageLoaded();

        int after = countTicket(ticket);

        Assert.assertEquals(
            after,
            beforeDeleteCount - 1,
            "Ticket was not deleted correctly"
        );
    }

    
    public int countTicket(Ticket ticket) {

        By row = By.xpath(String.format(
            "//table[contains(@class,'MyTable')]//tr[" +
            "td[2]='%s' and td[3]='%s' and td[4]='%s' and td[5]='%s' and td[9]='%s']",
            ticket.getDepartFrom(),
            ticket.getArriveAt(),
            ticket.getSeatType(),
            ticket.getDepartDate(),
            ticket.getTicketAmount()
        ));

        return Constant.WEBDRIVER.findElements(row).size();
    }


    
}
