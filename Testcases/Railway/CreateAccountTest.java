package Railway;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	    // ================= STEP 1: Open Guerrilla Mail =================

	    driver.get("https://www.guerrillamail.com/inbox");

	    // Tắt alias
	    WebElement chkAlias = wait.until(
	            ExpectedConditions.elementToBeClickable(By.id("use-alias")));

	    if (chkAlias.isSelected()) {
	        chkAlias.click();
	    }

	    // Lấy mail
	    String tempEmail = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.id("email-widget"))).getText();

	    System.out.println("Temp mail: " + tempEmail);



	    // ================= STEP 2: Register (dùng POM) =================

	    ((JavascriptExecutor) driver).executeScript("window.open()");

	    ArrayList<String> tabs =
	            new ArrayList<>(driver.getWindowHandles());

	    driver.switchTo().window(tabs.get(1));

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage = homePage.gotoRegisterPage();

	    registerPage.register(
	            tempEmail,
	            "Valid@Password",
	            Constant.PID
	    );



	    // ================= STEP 3: Back to Guerrilla =================

	    driver.switchTo().window(tabs.get(0));

	    // Đợi mail về
	    wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//tbody[@id='email_list']/tr")));

	    // Click mail đầu tiên
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//tbody[@id='email_list']/tr[1]"))).click();



	    // ================= STEP 4: Click Confirm =================

	    wait.until(ExpectedConditions
	            .frameToBeAvailableAndSwitchToIt("email_body"));

	    WebElement confirmLink = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[contains(@href,'Confirm')]")));

	    confirmLink.click();

	    driver.switchTo().defaultContent();



	    // ================= STEP 5: Login verify =================

	    ArrayList<String> tabs2 =
	            new ArrayList<>(driver.getWindowHandles());

	    driver.switchTo().window(tabs2.get(tabs2.size() - 1));

	    wait.until(ExpectedConditions.urlContains("Login"));

	    LoginPage loginPage = new LoginPage();

	    loginPage.login(tempEmail, "Valid@Password");

	    String welcome = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.className("account"))).getText();

	    Assert.assertTrue(welcome.contains(tempEmail));
	}


}
