package application;
import java.util.ArrayList;
import java.io.*;

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
	public static UserAccounts activeacc;
	//addtion by DP
	public UserAccounts() {//base/void account
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
	//takes in account and database and checks for matches.
	public boolean checkUserAccount(UserAccounts thing, UserAccounts[] database) {
		boolean exists = false;
		String itemUsername = thing.getname();
		String itemPass = thing.getPass();
		for(UserAccounts temp : database) {
			String tempUsername = temp.getname();
			String tempPass = temp.getPass();
			if (tempUsername.equals(itemUsername) && tempPass.equals(itemPass)) {
				exists = true;
				break;
			}
		}
		return exists;
	}
	public boolean checkCredential(String user, String pass, ArrayList<UserAccounts> accountDatabase) {
		boolean exists = false;
		String tempUsername;
		String tempPass;
		for(UserAccounts temp : accountDatabase) {
			tempUsername = temp.getname();
			tempPass = temp.getPass();
			if (tempUsername.equals(user) && tempPass.equals(pass)) {
				exists = true;
				break;
			}
		}
		return exists;
	}
	public UserAccounts searchCredential(String user, ArrayList<UserAccounts> database) {
		UserAccounts acc = new UserAccounts();
		for (UserAccounts temp: database) {
			if (temp.getname().equals(user)) {
				acc = temp;
			}
		}
		return acc;
	}
	public static UserAccounts getActiveAccount() {
		return activeacc;
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
