package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;

public class BaseTest {

    @BeforeMethod
    public void setUp() {

        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {

        System.out.println("Post-condition");

        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }
}
