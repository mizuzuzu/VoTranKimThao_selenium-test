package Railway;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class CreateAccountTest extends BaseTest{
	@Test
    public void TC07() {
        System.out.println("TC07 - User can't create account with an already in-use email");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String actualMsg = registerPage
                .register(Constant.VALID_USERNAME, Constant.VALID_PASSWORD, Constant.PID)
                .getRegisterErrorMsg();


        String expectedMsg = "This email address is already in use."; 

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
	
	@Test
    public void TC08() {
        System.out.println("TC08 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        //error
        String actualMsg = registerPage
                .register(Constant.UNSUBCRIBED_USERNAME, "", "")
                .getRegisterErrorMsg();

        Assert.assertEquals(actualMsg, "There're errors in the form. Please correct the errors and try again.", "Error message is not displayed as expected");

        //password field length validate
        
        String actualPasswordVlid = registerPage.getLblValidateErrorPasswordDisplay();
        
        Assert.assertEquals(actualPasswordVlid,"Invalid password length", "Error message is not displayed as expected");

        //pid field length validate
        
        String actualPIDVlid = registerPage.getLblValidateErrorPIDDisplay();
        
        Assert.assertEquals(actualPIDVlid, "Invalid ID length", "Error message is not displayed as expected");
    }
	
	@Test
	public void TC09() {

	    System.out.println("TC09 - User can create and activate account");

	    WebDriver driver = Constant.WEBDRIVER;

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(120));

	    //Open Guerrilla Mail

	    driver.get("https://www.guerrillamail.com/inbox");

	    WebElement chkAlias = wait.until(
	            ExpectedConditions.elementToBeClickable(By.id("use-alias")));

	    if (chkAlias.isSelected()) {
	        chkAlias.click();
	    }

	    String tempEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-widget"))).getText();

	    System.out.println("Temp mail: " + tempEmail);

	    String mailTab = driver.getWindowHandle();

	    //Register Account

	    driver.switchTo().newWindow(WindowType.TAB);

	    driver.getWindowHandle();

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(tempEmail, "Valid@Password", Constant.PID);

	    //Wait For Confirm Mail

	    driver.switchTo().window(mailTab);

	    longWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_list")));

	    int oldMailCount = driver.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();

	    System.out.println("Old mail count: " + oldMailCount);

	    longWait.until(d -> {
	        int newCount = d.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();
	        return newCount > oldMailCount;
	    });

	    System.out.println("New mail arrived!");

	    WebElement newestMail = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@id='email_list']/tr[1]")));

	    //advertise overlay 
	    longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("grippy-host")));

	    new Actions(driver).scrollToElement(newestMail).perform();
	    new Actions(driver).moveToElement(newestMail).pause(Duration.ofMillis(300)).click().perform();

	    //Confirm Link

	    WebElement mailBody = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body, div.mail_body, div#email_body")));

	    WebElement confirmLink = mailBody.findElement(By.xpath(".//a[contains(@href,'Account/Confirm')]"));

	    String confirmUrl = confirmLink.getAttribute("href");
	    System.out.println("Confirm URL: " + confirmUrl);

	    driver.get(confirmUrl);

	    WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Registration Confirmed')]")));

	    Assert.assertEquals(successMsg.getText(),"Registration Confirmed! You can now log in to the site.","Confirm message is incorrect");
	}

}
