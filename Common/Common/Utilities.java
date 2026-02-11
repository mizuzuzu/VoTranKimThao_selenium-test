package Common;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.google.common.base.Function;

import Constant.Constant;

public class Utilities {

    private static final int TIMEOUT = 20;

    //wait
    public static void waitUntilCondition(Function<WebDriver, Boolean> condition) {

        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(condition);
    }


    public static WebElement waitForVisible(By locator) {

        return new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement waitForClickable(By locator) {

        return new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static WebElement waitForPresence(By locator) {

        return new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static void waitForPageLoaded() {

        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }

    public static Alert waitForAlert() {

        return new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }


    //click
    public static void click(By locator) {

        WebElement element = waitForClickable(locator);

        scrollTo(element);

        try {

            element.click();

        } catch (ElementClickInterceptedException e) {

            new Actions(Constant.WEBDRIVER).moveToElement(element).pause(Duration.ofSeconds(1)).click().perform();
        }
    }


    public static void clickByJS(WebElement element) {

        scrollTo(element);

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", element);
    }


    public static void clickByJS(By locator) {

        WebElement element = waitForPresence(locator);

        scrollTo(element);

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", element);
    }


    //input
    public static void sendKeys(By locator, String text) {

        WebElement element = waitForVisible(locator);

        scrollTo(element);

        element.clear();
        element.sendKeys(text);
    }


    //get text
    public static String getText(By locator) {
    	WebElement element = waitForVisible(locator);

    	scrollTo(element);
    	
        return element.getText().trim();
    }


    public static String getTextByJS(By locator) {

        WebElement element = waitForPresence(locator);

        return (String) ((JavascriptExecutor) Constant.WEBDRIVER)
                .executeScript("return arguments[0].textContent;", element);
    }


    public static String getText(WebElement element) {
    	
    	scrollTo(element);

        return element.getText().trim();
    }
    
    public static String getSelectedOptionText(By locator) {

        Select select = new Select(Constant.WEBDRIVER.findElement(locator));

        return select.getFirstSelectedOption().getText();
    }



    //date
    public static String getNextDay(int days) { //number day + from today

        LocalDate date = LocalDate.now().plusDays(days);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        return date.format(formatter);
    }


    //scroll
    public static void scrollTo(By locator) {

        scrollTo(waitForPresence(locator));
    }


    public static void scrollTo(WebElement element) {

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }


    //display
    public static boolean isDisplayed(By locator) {

        try {

            return Constant.WEBDRIVER.findElement(locator).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    //select
    public static void selectByVisibleText(By locator, String text) {

        WebElement element = waitForVisible(locator);

        scrollTo(element);

        try {
        	new Select(element).selectByVisibleText(text);
        } catch (NoSuchElementException e) {

            throw new AssertionError(
                "Cannot find option with text: [" + text + "] at locator: " + locator
            );
        }
    }


    public static void selectAfterReload(By locator, String text) {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER,Duration.ofSeconds(TIMEOUT));

        // Lay danh sach option ban dau
        List<String> oldOptions = new Select(waitForVisible(locator)).getOptions().stream().map(o -> o.getText().trim()).toList();

        // Cho dropdown reload
        boolean isReloaded = wait.until(driver -> {

            try {

                Select select = new Select(driver.findElement(locator));

                List<String> newOptions = select.getOptions().stream().map(o -> o.getText().trim()).toList();

                return !newOptions.equals(oldOptions);

            } catch (StaleElementReferenceException e) {
                return false;
            }
        });

        if (!isReloaded) {
            throw new AssertionError("Dropdown did not reload: " + locator);
        }

        // Sau khi reload xong -> tim lai element
        WebElement element = waitForVisible(locator);

        scrollTo(element);

        try {

        	new Select(element).selectByVisibleText(text);

        } catch (NoSuchElementException e) {

            StringBuilder options = new StringBuilder();

            for (WebElement opt : new Select(element).getOptions()) {
                options.append(opt.getText()).append(" | ");
            }

            throw new AssertionError(
                "Cannot find option after reload: [" + text + "]"
 
            );
        }
    }


    //pop up
    public static void handleAlert(boolean accept) {

        Alert alert = waitForAlert();

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }
    
    public static String getAlertText() {

        Alert alert = waitForAlert();
        return alert.getText();
    }


    //url
    public static void openDynamicLink(String url) {

        Constant.WEBDRIVER.get(url);
    }

}
