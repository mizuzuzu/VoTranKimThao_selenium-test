package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TimetablePage extends GeneralPage {

    //Locators
    private final By tbTrainTime = By.xpath("//table[contains(@class,'MyTable') and contains(@class,'WideTable')]");
    
    //Elements
    public WebElement getTrainTimeTable() {
        return Constant.WEBDRIVER.findElement(tbTrainTime);
    }

    //Methods
    public TicketPricePage gotoCheckPrice(String depart, String arrive) {

        By ticketPrice = By.xpath(String.format(
            "//table[contains(@class,'MyTable')]//tr[" + "td[2]='%s' and td[3]='%s'" + "]//a[contains(@href,'TicketPricePage.cshtml')]",
            depart, arrive
        ));

        Utilities.click(ticketPrice);
        
        return new TicketPricePage();
    }
    
    public BookTicketPage gotoBookTicket(String depart, String arrive) {

        By bookTicket = By.xpath(String.format(
            "//table[contains(@class,'MyTable')]//tr[" + "td[2]='%s' and td[3]='%s'" + "]//a[contains(@href,'BookTicketPage.cshtml')]",
            depart, arrive
        ));

        Utilities.click(bookTicket);
        
        return new BookTicketPage();
    }

}
