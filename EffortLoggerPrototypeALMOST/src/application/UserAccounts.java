package application;
import java.util.ArrayList;

public class UserAccounts {
	//username, name, last_name, etc
	// make certain fields 
	private String email;
	private String username;
	private String password;
	private String role;
	private enum roleLevel { //using int values to represent 
		VIEWER, DEVELOPER, ADMINISTRATOR
	}
	
	protected ArrayList <UserAccounts> accounts = new ArrayList<UserAccounts>();
	
	//addtion by DP
	public void createAccount(String email, String username, String password, String role) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role.toUpperCase(null); //in all caps please 
	}
	
	public void addtoList(UserAccounts account) {
		accounts.add(account);
	}
	
	//Roles can be: Developer, Supervisor, Administrator
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.username = name;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public String getname() {
		return username;
	}
	public String getPass() {
		return password;
	}
	public String getRole() {
		return role;
	}
}
