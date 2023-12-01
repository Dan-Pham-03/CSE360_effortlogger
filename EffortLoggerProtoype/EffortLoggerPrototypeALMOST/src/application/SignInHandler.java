package application;

// Different things that needed to be imported
import java.io.IOException;
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

public class SignInHandler extends sceneController {
	
	// Default Constructor
	public SignInHandler() {
		
	}
	
	int authCode = 0;
	
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
	private void checkCredentials(ActionEvent event) throws IOException { // A method that will see if the credentials are valid
		Main m = new Main();
		
		//REPLACED WITH ACCOUNTS[] IN USERACCOUNTS.JAVA
		String[][] credentials = { // A variation of possible username and password combinations that would be accepted in the login screen
				{"bongo", "c!"}, 
				{"app", "1a23ad45!"}, 
				{"sugarpie", "87*65"},
				{"a", "a"}
		}; 
		
		boolean validCredentials = false; // A boolean variable set to false
		
		// Checks to see if the entered credentials are valid
		for(String [] credential : credentials) { // Goes through the credentials string array and checks if the login information matches
			if(username.getText().toString().equals(credential[0]) && password.getText().toString().equals(credential[1])) {
				validCredentials = true; // If it does match, set validCredentials to true and break out of the if statement
				break;
			}
		}
		
		// If they are valid, load the next scene
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
		}
		else {
			wrongSignIn.setText("Incorrect password or username."); // If the information is incorrect, then prompt them to try again
		}
	}
}
