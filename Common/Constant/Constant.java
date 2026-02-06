package Constant;
import org.openqa.selenium.WebDriver;

import Railway.Account;

public class Constant {
	
	public static WebDriver WEBDRIVER;
	
	public static final String PID = "123456789";
	
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";

    public static final Account VALID_USER = new Account("vothao7102@gmail.com", "tynrt1072002");
    
    public static final Account INACTIVED_USER = new Account("abcxyz333@spam4.me", "invalidPassword");
    
    public static final Account LOCK_TEST_USER = new Account("thanhle@logigear.com", "12345678");

	
	
}
