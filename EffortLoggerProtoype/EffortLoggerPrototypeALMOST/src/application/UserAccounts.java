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
		VIEWER, DEVELOPER, SUPERVISOR, ADMINISTRATOR
	}
	//Creating Database
	public UserAccounts activeAccount = new UserAccounts(); //USED IN SIGNINHANDLER
	
	protected ArrayList <UserAccounts> accountDatabase = new ArrayList<UserAccounts>();
	public UserAccounts account1 = new UserAccounts("dhpham2@asu.edu", "dhpham2","password", "DEVELOPER");
	public UserAccounts account2 = new UserAccounts("orange", "dhpham2","password", "DEVELOPER");
	
	
	//addtion by DP
	public UserAccounts () {//base/void account
		email = "void@gmail.com";
		username = "ActiveAccount";
		password = "password";
		role = "ADMINISTRATOR";
	}
	public UserAccounts (String email, String username, String password, String role) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role.toUpperCase(null); //in all caps please 
	}
		
	public boolean checkUserAccount(UserAccounts thing) {
		boolean exists = false;
		//for() {
		
		//}
		
		return exists;
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
