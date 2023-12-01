package application;

// Importing necessary JavaFX classes
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

// TwoFactorAuthenController class that extends sceneController
public class TwoFactorAuthenController extends sceneController {
    // Private variables for the authentication code and the number of incorrect attempts
	private int authCode;
	int incorrectAttempts = 0;
	
	// Variables used in the TwoFactorAuthen
    @FXML
    private TextField checkDigits;
    
    @FXML
    private Label wrongDigits;

    // Sets the authentication code
	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}
	
    // Will check and verify the entered digits
	public void check5Digits(ActionEvent event) throws IOException {
		int userCode = 0;
		String userInput = checkDigits.getText();
	
        // Check if the user input contains any letters. Displays error message if it does
		if (userInput.matches(".*[a-zA-Z].*")) {
	        wrongDigits.setText("Input must not contain letters.");
	        checkDigits.clear();
	        return;
	    }

        // Check if the user input is a 5-digit number. Displays error message if it does
		if(userInput.matches("\\d{5}")) {
			userCode = Integer.parseInt(userInput);
		}
		else  {
			wrongDigits.setText("Input must be a 5-digit number.");
			checkDigits.clear();
			return;
		}
		
        // Check if the user code matches the authentication code
		if(userCode == authCode) {
			switchToEffortConsole(event);
		}
		else {
            // If the user code does not match the authentication code, increment the number of incorrect attempts and display error message
			wrongDigits.setText("Wrong Code. Please Try Again.");
			checkDigits.clear();
			incorrectAttempts++;
		}
	
        // If the user has made 3 incorrect attempts, switch to the login scene
		if(incorrectAttempts == 3) {
			switchToLogin(event);
		}
	}	
}
