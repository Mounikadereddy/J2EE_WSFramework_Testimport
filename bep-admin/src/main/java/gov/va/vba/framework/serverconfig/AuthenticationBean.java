package gov.va.vba.framework.serverconfig;


public class AuthenticationBean {

	private String username;
	private String password;
	
	private static final long serialVersionUID = 1L;

	public AuthenticationBean(String username, String password) {
		this.username=username;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString()
	{
		return username+"/"+password;
	}

}
