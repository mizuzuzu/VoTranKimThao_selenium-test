package Guerrilla;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;
import Constant.MailType;

public class GuerrillaMailPage {

    //Locators
	private final By editField = By.id("inbox-id");
	private final By txtEmail = By.xpath("//span[@id='inbox-id']//input");
	private final By sltHostName = By.id("gm-host-select");
	private final By btnSet = By.xpath("//span[@id='inbox-id']//button[text()='Set']");
	private final By btnBacktoInbox = By.id("back_to_inbox_link");
	private final By rdoScramble = By.id("use-alias");
	private final By txtEmailDisplay = By.id("email-widget");
    private final By listEmail = By.xpath("email_list");
    
    private final By emailBody = By.cssSelector("div.email_body");

    private final By verifyAccountMail = By.xpath("//tr[contains(@class,'mail_row') and contains(.,'Please confirm your account')]");
    private final By verifyAccountLink = By.xpath("//a[contains(@href,'Account/Confirm')]");
    
    private final By verifyResetPwdMail = By.xpath("//tr[contains(@class,'mail_row') and contains(.,'Please reset your password')]");
    private final By verifyResetPwdLink = By.xpath("//a[contains(@href,'Account/PasswordReset')]");
    
    
    //Elements
    protected WebElement getEditField() {
        return Constant.WEBDRIVER.findElement(editField);
    }

    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }
    
    protected WebElement getSelectHostName() {
        return Constant.WEBDRIVER.findElement(sltHostName);
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
    
    protected List<WebElement> getVerifyAccountMail() {
        return Constant.WEBDRIVER.findElements(verifyAccountMail);
    }
    
    protected List<WebElement> getVerifyResetPwdMail() {
        return Constant.WEBDRIVER.findElements(verifyResetPwdMail);
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

        return email;
    }
    
    //set up lai mail
    public String useCreatedEmail(String emailName, String hostName) {
    	
        Utilities.waitForClickable(editField);
        Utilities.click(editField);

        Utilities.waitForVisible(txtEmail);
        Utilities.sendKeys(txtEmail, emailName);
        
        Utilities.waitForVisible(sltHostName);
        Utilities.selectByVisibleText(sltHostName, hostName);

        Utilities.click(btnSet);

        if (Constant.WEBDRIVER.findElement(rdoScramble).isSelected()) {
            Utilities.click(rdoScramble);
        }

        Utilities.waitForVisible(txtEmailDisplay);

        String email = Utilities.getText(txtEmailDisplay);

        return email;
    }
    
    private By getMailLocator(MailType type) {

        switch (type) {

            case VERIFY_ACCOUNT:
                return verifyAccountMail;

            case RESET_PASSWORD:
                return verifyResetPwdMail;

            default:
                throw new IllegalArgumentException("Unsupported mail type: " + type);
        }
    }


    private By getLinkLocator(MailType type) {

        switch (type) {

            case VERIFY_ACCOUNT:
                return verifyAccountLink;

            case RESET_PASSWORD:
                return verifyResetPwdLink;

            default:
                throw new IllegalArgumentException("Unsupported mail type: " + type);
        }
    }
    
    public String openNewestMailAndGetLink(MailType type) {

        By mailLocator = getMailLocator(type);
        By linkLocator = getLinkLocator(type);

        //mail
        Utilities.waitForVisible(mailLocator);
        Utilities.clickByJS(mailLocator);

        //link
        Utilities.waitForVisible(linkLocator);

        return Constant.WEBDRIVER.findElement(linkLocator).getAttribute("href");
    }
    
    public String getResetTokenFromMail() {

        Utilities.waitForVisible(emailBody);

        String body =
            Utilities.getText(emailBody);

        Pattern pattern = Pattern.compile("token is:\\s*([A-Za-z0-9+/=]+)");

        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new RuntimeException("Cannot find reset token in email!");
    }


}

