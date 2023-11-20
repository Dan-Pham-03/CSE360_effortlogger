package application;

// Different things that needed to be imported
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInHandler extends sceneController {
	
	public SignInHandler() {
	
	}
	
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
	
	private void checkCredentials(ActionEvent event) throws IOException { // A method that will see if the credentials are valid
		//Main m = new Main();

		String[][] credentials = { // A variation of possible username and password combinations that would be accepted in the login screen
				{"bongo", "orange1o!"}, 
				{"app", "1a23ad45!"}, 
				{"sugarpie", "87*65"},
				{"a", "a"}
		};
		//would instead call UserAccounts for the list of users. 
		
		boolean validCredentials = false; // A boolean variable set to false
		
		//System.out.println("made it here");
		
		for(String [] credential : credentials) { // Goes through the credentials string array and checks if the login information matches
			if(username.getText().toString().equals(credential[0]) && password.getText().toString().equals(credential[1])) {
				//DP want to set active
				
				
				validCredentials = true; // If it does match, set validCredentials to true and break out of the if statement
				break;
			}
		}
		if(validCredentials) { // Checks the boolean validCredentials
			//wrongSignIn.setText("Welcome to Effortlogger."); // If it is true, it will welcome the user into the Effortlogger application
			//m.changeStage("EffortConsole.fxml"); // Takes them to the second scene (Effortlogger)
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EffortConsole.fxml"));
			root = loader.load();
			
			effortConsoleController controller = loader.getController();
			controller.setUsername(username.getText());
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(username.getText().isEmpty() && password.getText().isEmpty()) { // If the user didn't input anything, then ask them to enter their information
			wrongSignIn.setText("Please enter your information.");
		}
		else {
			wrongSignIn.setText("Incorrect password or username."); // If the information is incorrect, then prompt them to try again
		}
	}
}

// Some things that can be added
// A limited number of password tries
// A more extensive database
// A "forgot password" section where a verified user can reset their password securely
