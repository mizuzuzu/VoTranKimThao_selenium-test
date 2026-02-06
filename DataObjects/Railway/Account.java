package Railway;

public class Account {
	

	    private String username;
	    private String password;
	    private String confirmPassword;
	    private String pid;

	    public Account(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public Account(String username, String password, String confirmPassword, String pid) {
	        this.username = username;
	        this.password = password;
	        this.confirmPassword = confirmPassword;
	        this.pid = pid;
	    }


	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }
	    
	    public String getConfirmPassword() {
	        return confirmPassword;
	    }

	    public String getPid() {
	        return pid;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String setConfirmPassword() {
	        return confirmPassword;
	    }

	    public void setPid(String pid) {
	        this.pid = pid;
	    }
	
}
