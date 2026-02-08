package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TicketPricePage extends GeneralPage{
	//Locators
	private final By lblTicketTitle = By.xpath("//div[@id='content']//h1[text()='Ticket Price']");
    private final By lblTicketTableTitle = By.xpath("//table[contains(@class,'MedTable')]//tr[1]/th");

    private final By rowSeatType = By.xpath("//table[contains(@class,'MedTable')]//tr[2]/td");
    private final By rowPrice = By.xpath("//table[contains(@class,'MedTable')]//tr[3]/td");
    
	//Elements
    public WebElement getTicketPageTitle() {
        return Constant.WEBDRIVER.findElement(lblTicketTitle);
    }

    public WebElement getTicketTableTitle() {
        return Constant.WEBDRIVER.findElement(lblTicketTableTitle);
    }

    public List<WebElement> getSeatCells() {
        return Constant.WEBDRIVER.findElements(rowSeatType);
    }

    public List<WebElement> getPriceCells() {
        return Constant.WEBDRIVER.findElements(rowPrice);
    }
    
	//Methods
    public boolean isTicketPricePageDisplayed() {
        return Utilities.isDisplayed(lblTicketTitle);
    }

    public String getTicketPriceTitle() {
        return Utilities.getText(getTicketTableTitle());
    }
    
    public String getPriceBySeat(String seat) {

        List<WebElement> seats = getSeatCells();
        List<WebElement> prices = getPriceCells();

        for (int i = 0; i < seats.size(); i++) {

            if (seats.get(i).getText().equals(seat)) {

                return prices.get(i).getText();
            }
        }

        throw new RuntimeException("Seat not found: " + seat);
    }
}
