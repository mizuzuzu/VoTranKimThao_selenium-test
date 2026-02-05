package Railway;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class ResetPasswordTest extends BaseTest {
	
	private void openLatestMailWithRetry(WebDriver driver) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(120));

	    boolean opened = false;

	    for (int i = 0; i < 5; i++) {

	        try {

	            WebElement mail = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@id='email_list']/tr[1]")));

	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mail);

	            longWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body")));

	            opened = true;
	            break;

	        } catch (Exception e) {

	            System.out.println("Retry open mail: " + (i + 1));

	            driver.navigate().refresh();

	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_list")));
	        }
	    }

	    Assert.assertTrue(opened, "Cannot open email!");
	}

	
	@Test
	public void TC10() {

	    System.out.println("TC10 - Reset password shows error if the new password is same as current");

	    WebDriver driver = Constant.WEBDRIVER;

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(120));

	    String password = "Valid@Password";


	    // ================= STEP 1: Open Guerrilla =================

	    driver.get("https://www.guerrillamail.com/inbox");

	    WebElement chkAlias = wait.until(ExpectedConditions.elementToBeClickable(By.id("use-alias")));

	    if (chkAlias.isSelected()) {
	        chkAlias.click();
	    }

	    String tempEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-widget"))).getText();

	    System.out.println("Temp mail: " + tempEmail);



	    // ================= STEP 2: Register =================

	    driver.switchTo().newWindow(WindowType.TAB);

	    List<String> tabs =new ArrayList<>(driver.getWindowHandles());

	    driver.switchTo().window(tabs.get(1));

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage =homePage.gotoRegisterPage();

	    registerPage.register(tempEmail, password, Constant.PID);



	    // ================= STEP 3: Activate =================

	    driver.switchTo().window(tabs.get(0));

	    longWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_list")));

	    int oldCount = driver.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();

	    longWait.until(d -> d.findElements(By.xpath("//tbody[@id='email_list']/tr")).size() > oldCount);


	    // Open confirm mail
	    openLatestMailWithRetry(driver);


	    WebElement confirmBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body")));

	    WebElement confirmLink = confirmBody.findElement(By.xpath(".//a[contains(@href,'Account/Confirm')]"));

	    String confirmUrl = confirmLink.getAttribute("href");

	    System.out.println("Confirm URL: " + confirmUrl);


	    wait.until(ExpectedConditions.elementToBeClickable(By.id("back_to_inbox_link"))).click();


	    driver.switchTo().newWindow(WindowType.TAB);
	    driver.get(confirmUrl);



	    // ================= STEP 4: Forgot Password =================

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();



	    // ================= STEP 5: Send Reset =================

	    resetPage.enterEmail(tempEmail);



	    // ================= STEP 6: Get Reset Mail =================

	    driver.switchTo().window(tabs.get(0));

	    longWait.until(d -> d.findElements(By.xpath("//tbody[@id='email_list']/tr")).size() > oldCount + 1);


	    openLatestMailWithRetry(driver);


	    WebElement resetBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body")));

	    WebElement resetLink = resetBody.findElement(By.xpath(".//a[contains(@href,'Account/PasswordR')]"));

	    String resetUrl = resetLink.getAttribute("href");

	    System.out.println("Reset URL: " + resetUrl);


	    wait.until(ExpectedConditions.elementToBeClickable(By.id("back_to_inbox_link"))).click();



	    // ================= STEP 7: Reset =================

	    driver.switchTo().newWindow(WindowType.TAB);
	    driver.get(resetUrl);

	    ResetPasswordPage resetPwdPage = new ResetPasswordPage();

	    resetPwdPage.changeNewPassword(password, password);



	    // ================= STEP 8: Verify =================

	    String actualMsg = resetPwdPage.getResetResultMessage();

	    System.out.println("Result: " + actualMsg);


	    String expectedMsg = "The new password cannot be the same with the current password";

	    Assert.assertEquals(actualMsg, expectedMsg);
	}

	@Test
	public void TC11() {

	    System.out.println("TC11 - Reset password shows error if the new password and confirm password doesn't match");

	    WebDriver driver = Constant.WEBDRIVER;

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(120));

	    String password = "Valid@Password";
	    String newpassword = "New@Valid@Password";
	    String cfmpassword = "NewNewValid@Password";

	    driver.get("https://www.guerrillamail.com/inbox");

	    WebElement chkAlias = wait.until(ExpectedConditions.elementToBeClickable(By.id("use-alias")));

	    if (chkAlias.isSelected()) {
	        chkAlias.click();
	    }

	    String tempEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-widget"))).getText();

	    System.out.println("Temp mail: " + tempEmail);

	    driver.switchTo().newWindow(WindowType.TAB);

	    List<String> tabs =new ArrayList<>(driver.getWindowHandles());

	    driver.switchTo().window(tabs.get(1));

	    HomePage homePage = new HomePage();
	    homePage.open();

	    RegisterPage registerPage =homePage.gotoRegisterPage();

	    registerPage.register(tempEmail, password, Constant.PID);

	    driver.switchTo().window(tabs.get(0));

	    longWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_list")));

	    int oldCount = driver.findElements(By.xpath("//tbody[@id='email_list']/tr")).size();

	    longWait.until(d -> d.findElements(By.xpath("//tbody[@id='email_list']/tr")).size() > oldCount);

	    openLatestMailWithRetry(driver);


	    WebElement confirmBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body")));

	    WebElement confirmLink = confirmBody.findElement(By.xpath(".//a[contains(@href,'Account/Confirm')]"));

	    String confirmUrl = confirmLink.getAttribute("href");

	    System.out.println("Confirm URL: " + confirmUrl);


	    wait.until(ExpectedConditions.elementToBeClickable(By.id("back_to_inbox_link"))).click();


	    driver.switchTo().newWindow(WindowType.TAB);
	    driver.get(confirmUrl);



	    // ================= STEP 4: Forgot Password =================

	    homePage.open();

	    LoginPage loginPage = homePage.gotoLoginPage();

	    ResetPasswordPage resetPage = loginPage.gotoResetPwdPage();



	    // ================= STEP 5: Send Reset =================

	    resetPage.enterEmail(tempEmail);



	    // ================= STEP 6: Get Reset Mail =================

	    driver.switchTo().window(tabs.get(0));

	    longWait.until(d -> d.findElements(By.xpath("//tbody[@id='email_list']/tr")).size() > oldCount + 1);


	    openLatestMailWithRetry(driver);


	    WebElement resetBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.email_body")));

	    WebElement resetLink = resetBody.findElement(By.xpath(".//a[contains(@href,'Account/PasswordR')]"));

	    String resetUrl = resetLink.getAttribute("href");

	    System.out.println("Reset URL: " + resetUrl);


	    wait.until(ExpectedConditions.elementToBeClickable(By.id("back_to_inbox_link"))).click();



	    // ================= STEP 7: Reset =================

	    driver.switchTo().newWindow(WindowType.TAB);
	    driver.get(resetUrl);

	    ResetPasswordPage resetPwdPage = new ResetPasswordPage();

	    resetPwdPage.changeNewPassword(newpassword, cfmpassword);



	    // ================= STEP 8: Verify =================

	    String actualMsg = resetPwdPage.getResetResultMessage();

	    Assert.assertEquals(actualMsg, "Could not reset password. Please correct the errors and try again.","Error message is not displayed as expected");
	    
	    String ConfirmPwdMsg = resetPwdPage.getConfirmPasswordErrorMsgDisplay();
	    
	    Assert.assertEquals(ConfirmPwdMsg, "The password confirmation did not match the new password.", "Error message is not displayed as expected");
	}

}
