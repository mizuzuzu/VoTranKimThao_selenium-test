package Common;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.google.common.base.Function;

import Constant.Constant;

public class Utilities {
	
	//wait
    private static final int TIMEOUT = 20;
    
    public static void waitUntilCondition(Function<WebDriver, Boolean> condition, int timeout) {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER,Duration.ofSeconds(timeout));

        wait.until(condition);
    }
    
    public static WebElement waitForVisible(By locator) {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT));

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForPresence(By locator) {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT));

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public static void waitForPageLoaded() {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitForOverlayGone(By locator) {

        try {
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

        } catch (Exception e) {
        	
        }
    }

    //click
    public static void click(By locator) {

        WebElement element = waitForClickable(locator);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            new Actions(Constant.WEBDRIVER).moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
        }
    }
    
    public static void clickByJS(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;

        js.executeScript("arguments[0].click();", element);
    }
    
    public static void clickByJS(By locator) {

        WebDriver driver = Constant.WEBDRIVER;

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", element);
    }


    //input
    public static void sendKeys(By locator, String text) {

        WebElement element = waitForVisible(locator);

        element.clear();
        element.sendKeys(text);
    }

    //get text
    public static String getText(By locator) {

        return waitForVisible(locator).getText().trim();
    }
    
    public static String getTextByJS(By locator) {

        WebElement element = waitForPresence(locator);

        return (String) ((JavascriptExecutor) Constant.WEBDRIVER)
                .executeScript("return arguments[0].textContent;", element);
    }

    //scroll
    public static void scrollTo(By locator) {

        WebElement element = waitForPresence(locator);

        ((JavascriptExecutor)Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
    
    public static void scrollTo(WebElement element) {

        ((JavascriptExecutor) Constant.WEBDRIVER)
                .executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
                );
    }

    //display
    public static boolean isDisplayed(By locator) {

        try {
            return Constant.WEBDRIVER.findElement(locator).isDisplayed();
            
        } catch (Exception e) {
            return false;
        }
    }

}

