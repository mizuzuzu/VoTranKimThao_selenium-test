package Guerrilla;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {

    //Locators
	private final By editField = By.id("inbox-id");
	private final By txtEmail = By.xpath("//input[@type='text']");
	private final By btnSet = By.className(".save.button.small");
	private final By btnBacktoInbox = By.id("back_to_inbox_link");
	private final By rdoScramble = By.id("use-alias");
	private final By txtEmailDisplay = By.id("email-widget");
    private final By listEmail = By.xpath("email_list");
    private final By verifyLink = By.xpath("//a[contains(@href,'Account/Confirm')]");

    //Elements
    protected WebElement getEditField() {
        return Constant.WEBDRIVER.findElement(editField);
    }

    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    protected WebElement getBtnSet() {
        return Constant.WEBDRIVER.findElement(btnSet);
    }

    protected WebElement getBtnBackToInbox() {
        return Constant.WEBDRIVER.findElement(btnBacktoInbox);
    }

    protected WebElement getRdoScramble() {
        return Constant.WEBDRIVER.findElement(rdoScramble);
    }

    protected WebElement getTxtEmailDisplay() {
        return Constant.WEBDRIVER.findElement(txtEmailDisplay);
    }

    protected List<WebElement> getListEmail() {
        return Constant.WEBDRIVER.findElements(listEmail);
    }

    //Methods

    public void open() {

        Constant.WEBDRIVER.get("https://www.guerrillamail.com/");

        Utilities.waitForPageLoaded();
    }
    
    //dung mail random ngay khi mo web
    public String useRandomEmail() {

        Utilities.waitForVisible(rdoScramble);

        if (Constant.WEBDRIVER.findElement(rdoScramble).isSelected()) {
            Utilities.click(rdoScramble);
        }

        Utilities.waitForVisible(txtEmailDisplay);

        String email = Utilities.getText(txtEmailDisplay);

        System.out.println("Random Email: " + email);

        return email;
    }
    
    //set up lai mail
    public String useCreateEmail(String emailName) {
    	
        Utilities.waitForClickable(editField);
        Utilities.click(editField);

        Utilities.waitForVisible(txtEmail);
        Utilities.sendKeys(txtEmail, emailName);

        Utilities.click(btnSet);

        if (Utilities.isDisplayed(btnBacktoInbox)) {
            Utilities.click(btnBacktoInbox);
        }

        if (Constant.WEBDRIVER.findElement(rdoScramble).isSelected()) {
            Utilities.click(rdoScramble);
        }

        Utilities.waitForVisible(txtEmailDisplay);

        String email = Utilities.getText(txtEmailDisplay);

        System.out.println("Custom Email: " + email);

        return email;
    }
    
    //truyen vao so luong mail cu, khi mail list thay doi thi click mail o dau
    public String openNewestMailAndGetLink(int oldCount) {

        Utilities.waitUntilCondition(driver -> {

            int newCount = driver.findElements(listEmail).size();

            return newCount > oldCount;

        }, 60);

        By newestMail = By.xpath("//tbody[@id='email_list']/tr[1]");

        Utilities.clickByJS(newestMail);

        Utilities.waitForVisible(verifyLink);

        String link = Constant.WEBDRIVER.findElement(verifyLink).getAttribute("href");

        System.out.println("Confirm link: " + link);

        return link;
    }

}

