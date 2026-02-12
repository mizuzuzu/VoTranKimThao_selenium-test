package Railway;

public enum Accounts {

	VALID_USER_01("superTest@pokemail.net", "superInvalid@Password123"),
    VALID_USER_02("testAccount02@sharklasers.com", "superInvalid@Password123"),
    VALID_USER_03("testAccount03@guerrillamail.net", "superInvalid@Password123"),
    VALID_USER_04("testAccount04@grr.la", "superInvalid@Password123"),
    
    ACCOUNT_INVALID_PASSWORD("superTest@pokemail.net", "invalid@Password"),

    ACCOUNT_CHANGE_PASSWORD("changePassword@spam4.me", "p4ssword@lw4ysCh@nge"),

    LOCK_TEST_USER("thanhle@logigear.com", "12345678");

    private final String username;
    private final String password;

    Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account toAccount() {
        return new Account(username, password);
    }

	public String getUsername() {
		return username;
	}
}
