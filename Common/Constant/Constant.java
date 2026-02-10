package Constant;
import org.openqa.selenium.WebDriver;

import Railway.Account;

public class Constant {
	
	public static WebDriver WEBDRIVER;
	
	public static final String PID = "123456789";
	
	public static final String HOMEPAGE_RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String lOGIN_RAILWAY_URL = "http://www.saferailway.somee.com/Account/Register.cshtml";
	
    public static final Account VALID_USER_01 = new Account("superTest@pokemail.net", "superInvalid@Password123");
    public static final Account VALID_USER_02 = new Account("testAccount02@sharklasers.com", "superInvalid@Password123");
    public static final Account VALID_USER_03 = new Account("testAccount03@guerrillamail.net", "superInvalid@Password123");
    public static final Account VALID_USER_04 = new Account("testAccount04@grr.la", "superInvalid@Password123");
    
    //user for changing password 
    public static final Account ACCOUNT_CHANGE_PASSWORD = new Account("changePassword@spam4.me", "p4ssword@lw4ysCh@nge");
    
    //account did not verify yet
    public static final Account INACTIVED_USER = new Account("abcxyz333@spam4.me", "invalidPassword");
    
    //account prepare for locked situation
    public static final Account LOCK_TEST_USER = new Account("thanhle@logigear.com", "12345678");

	
	
}
