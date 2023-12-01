package application;

// Different things that needed to be imported
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SignInHandler extends sceneController {
	
	// Default Constructor
	public SignInHandler() {
		
	}
	
	int authCode = 0;
	int attempts = 3; // Available attempts, can be changed
	
	// Private variables for the varying buttons, labels, and fields
	@FXML
	private Button button;
	
	@FXML
	private Label wrongSignIn;
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	public void userSignIn(ActionEvent event) throws IOException{ // A method that leads to the checkCredentials method
		checkCredentials(event);
					
	}
	
	// Method that checks the credentials entered by the user in the login screen
	protected void checkCredentials(ActionEvent event) throws IOException { // A method that will see if the credentials are valid
		//Main m = new Main();
		
		//REPLACED WITH ACCOUNTS[] IN USERACCOUNTS.JAVA
		
		String[][] credentials = { // A variation of possible username and password combinations that would be accepted in the login screen
				{"bongo", "orange1o!"}, 
				{"app", "1a23ad45!"}, 
				{"sugarpie", "87*65"},
				{"a", "a"}
		};  
		UserAccounts userAccount1 = new UserAccounts("dhpham2@asu.edu", "dhpham2","password", "ADMINISTRATOR");
		UserAccounts userAccount2 = new UserAccounts("orange@asu.edu", "bongo","orange1o!", "DEVELOPER");
		UserAccounts userAccount3 = new UserAccounts("app@asu.edu", "sugarpie", "1a23ad45!", "SUPERVISOR");
		UserAccounts userAccount4 = new UserAccounts("a@asu.edu", "a", "a", "VIEWER");
		UserAccounts userAccount5 = new UserAccounts("sugar@asu.edu", "sugarpie", "87*65", "DEVELOPER");
		ArrayList <UserAccounts> accountDatabase = new ArrayList<UserAccounts>();
		accountDatabase.add(userAccount1);
		accountDatabase.add(userAccount2);
		accountDatabase.add(userAccount3);    // Initializing Faux account database.
		accountDatabase.add(userAccount4);
		accountDatabase.add(userAccount5);
		UserAccounts activeAccount = new UserAccounts();
		
		boolean validCredentials = false; // A boolean variable set to false
		// Checks to see if the entered credentials are valid
		
		String UN = username.getText().toString();
		String PW = password.getText().toString();
		if (activeAccount.checkCredential(UN, PW, accountDatabase)) {
			validCredentials = true;
			activeAccount = activeAccount.searchCredential(username.getText().toString(), accountDatabase);
			UserAccounts.activeacc = activeAccount;  //should now be accessable to other classes
			System.out.println("Active Account is: " + UserAccounts.activeacc.getname()+ ", has the role: "+ UserAccounts.activeacc.getRole());
			//Sets ACTIVEACCOUNT
		}/*
		 //~~~~ Old Code~~~~~ 
		for(String [] credential : credentials) { // Goes through the credentials string array and checks if the login information matches
			if(username.getText().toString().equals(credential[0]) && password.getText().toString().equals(credential[1])) {
				validCredentials = true; // If it does match, set validCredentials to true and break out of the if statement
				break;
			}
		} */
		textdataHandler handler = new textdataHandler("arbitrary");
		// If they are valid, load the next scene
		if (attempts > 0) { // If attempts are available, check the user inputs for valid login information
			if(validCredentials) { // Checks the boolean validCredentials
				//wrongSignIn.setText("Welcome to Effortlogger."); // If it is true, it will welcome the user into the Effortlogger application
				//m.changeStage("EffortConsole.fxml"); // Takes them to the second scene (Effortlogger)
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortConsole.fxml"));
				root = loader.load();
	
				//effortConsoleController controller = loader.getController();
				//controller.setUsername(username.getText());
						
				// Create the authentication code for the user
				authCode = (int)(Math.random() * 90000) + 10000;
				System.out.println("Your 5 digit code is: " + authCode);
			
				switchToTwoFactorAuthen(event, authCode); // Switch scenes
			}
			else if(username.getText().isEmpty() && password.getText().isEmpty()) { // If the user didn't input anything, then ask them to enter their information
				wrongSignIn.setText("Please enter your information.");
			} // Addition for wrong/illegal text
			else if(username.getText().isEmpty() && !(password.getText().isEmpty())) { // If the user didn't input a username, then ask them to enter one
				wrongSignIn.setText("Please enter a username.");
			} //Addition of message in case of only 1 missing field
			else if(!(username.getText().isEmpty()) && password.getText().isEmpty()) { // If the user didn't input password, then ask them to enter one
				wrongSignIn.setText("Please enter a password.");
			} //Addition of message in case of only 1 missing field
			else if(handler.textRestrictor(username.getText().toString(), 16) || handler.textRestrictor(password.getText().toString(), 32)) {
				wrongSignIn.setText("Those characters are not allowed. Please try again");
			}
			else {
				attempts -= 1;
				wrongSignIn.setText("Incorrect password or username. You have " + attempts + " attempts left."); // If the information is incorrect, then prompt them to try again
			}
		}
		else { // Locked out of login
			wrongSignIn.setText("No more attempts available. Please close the app."); // No more attempts available, close app to reset attempts
		}
	}
}
