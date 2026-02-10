package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constant.Constant;

public class BaseTest {
	 protected HomePage homePage;

	@Parameters("browser") 
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        System.out.println("Run with browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            Constant.WEBDRIVER = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            Constant.WEBDRIVER = new FirefoxDriver();
        }
        else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        Constant.WEBDRIVER.manage().window().maximize();
        homePage = new HomePage();
    }
	
	//Action
    
    protected void step(String msg) {
        System.out.println("Step " + msg);
    }

    @AfterMethod
    public void tearDown() {

        System.out.println("Post-condition");

        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }
}
