package application;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDateTime;

public class effortConsoleController extends sceneController implements Initializable {
	private LocalDateTime startTime;
	private String username;
	String user;

	int count;
	int index;

	// Variables used in the effortConsole
	@FXML
	private ComboBox<String> comboBoxProject;
	
	@FXML
	private ComboBox<String> comboBoxLifeCycle;
	
	@FXML
	private ComboBox<String> comboBoxCategory;
	
	@FXML
	private ComboBox<String> comboBoxPlan;

	@FXML
	private Label clockText;
	
	@FXML
	private Label timeText;
	
	@FXML
	private Rectangle rectangleColor;
	
	@FXML
    private Label usernameLabel;
	
	@FXML
	private Label warningActivityStart;
	
	@FXML
	private Label warningActivityStop;
	
	@FXML
	private Label elapsedTime;
    
    /*public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("Welcome " + this.username + "!");
        user = this.username;
    }*/
	
    // Initializes the options for the dropdown in the application
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		comboBoxProject.setItems(FXCollections.observableArrayList("Business Project", "Development Project"));
		comboBoxLifeCycle.setItems(FXCollections.observableArrayList("Planning", "Information Gathering", "Information Understanding", "Verifying", "Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"));
		comboBoxCategory.setItems(FXCollections.observableArrayList("Plans", "Deliverables", "Interruptions", "Defects", "Others"));
		comboBoxPlan.setItems(FXCollections.observableArrayList("Conceptual Design", "Project Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"));
		//setUsername(user);
	}
		
	// Function that runs when you click "Start an Activity". It will check to see if an activity is currently running, and if there is one,
	// an error message will appear. After that, the time and date of the start activity is collected, which will then be sent to the logs
	public void startActivity(ActionEvent eventStart) {
		// Checks if the activity is already running. If it is, display an error message
		if(count == 1) { 
			warningActivityStart.setText("Please finish your current activity before starting a new one."); 
			return; 	
		}
		// Checks to see if all the dropdown lists have an item selected. If not, display error messaeg
		else if(comboBoxProject.getSelectionModel().getSelectedItem() == null || comboBoxLifeCycle.getSelectionModel().getSelectedItem() == null || comboBoxCategory.getSelectionModel().getSelectedItem() == null || comboBoxPlan.getSelectionModel().getSelectedItem() == null) {
			warningActivityStart.setText("Please have a selected item in each of the drop down lists."); 
			return;
		}
		// If an activity isn't already running, and every dropdown has an item selected, start the new activity
			count = 1;
			index++;
			
			// Gets the current time and date
			this.startTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss a");
			String formattedStartTime = this.startTime.format(formatter);
			
			// Updates the scene and UI to reflect that an activity has started
			clockText.setText("Clock has Started");
			Platform.runLater(() -> rectangleColor.setFill(Color.web("#b7f8cb")));
			timeText.setText("Activity started on " + formattedStartTime);
			elapsedTime.setText("");
			warningActivityStart.setText(""); 
			warningActivityStop.setText(""); 
		}
	
	// Function that runs when you click "Finish Activity". It will check to see if an activity is currently running, and if there is not,
	// an error message will appear. After that, the time and date of the start activity is collected, which will then be sent to the logs
	public void finishActivity(ActionEvent eventFinish) throws IOException {
		// Checks if an activity is running, if yes, start the method
		if(count == 1) {
			// Will hold the time when the Finish Activity button was pressed
			LocalDateTime stopTime = LocalDateTime.now();
			
			// Calculates the elapsed time
			Duration duration = Duration.between(startTime, stopTime);
			long seconds = duration.getSeconds();
			long hours = seconds / 3600;
			long minutes = ((seconds % 3600) / 60);
			long elapsedInMinutes = duration.toMinutes(); // Would be sent to logs to document the time that elapsed in miuntes
			seconds = seconds % 60;
			
			// Formats the stopped time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss a");
			String formattedStopTime = stopTime.format(formatter);
			
			// Updates the scene and UI to reflect that an activity has started
			clockText.setText("Clock has Stopped");
			Platform.runLater(() -> rectangleColor.setFill(Color.web("#ff7063")));
			timeText.setText("Activity stopped on " + formattedStopTime);
			elapsedTime.setText("Elapsed time: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds.");
			
			// Reset the count
			count = 0;
			warningActivityStop.setText(""); 
			warningActivityStart.setText("");			
		}
		// If an activity isn't running, display an error message
		else {
			warningActivityStop.setText("Have not started an activity yet."); 
		}
	}
}
