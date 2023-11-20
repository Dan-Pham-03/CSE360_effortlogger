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
import java.time.LocalDateTime;

public class effortConsoleController extends sceneController implements Initializable {
	private LocalDateTime startTime;
	private String username;

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
    
    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("Welcome " + this.username + "!");
    }
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		comboBoxProject.setItems(FXCollections.observableArrayList("Business Project", "Development Project"));
		comboBoxLifeCycle.setItems(FXCollections.observableArrayList("Planning", "Information Gathering", "Information Understanding", "Verifying"
																	, "Outlining", "Drafting", "Finalizing", 
																	"Team Meeting", "Coach Meeting", "Stakeholder Meeting"));
		comboBoxCategory.setItems(FXCollections.observableArrayList("Plans", "Deliverables", "Interruptions", "Defects", "Others"));
		comboBoxPlan.setItems(FXCollections.observableArrayList("Conceptual Design", "Project Plan", "Conceptual Design Plan",
																"Detailed Design Plan", "Implementation Plan"));
	}
		
	
	public void startActivity(ActionEvent eventStart) {
		if(count == 1) { 
			warningActivityStart.setText("Already started an activity, cannot start another until you finish your current one."); 
			return; 	
		}
			count = 1;
			index++;
			this.startTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss a");
			String formattedStartTime = this.startTime.format(formatter);
			clockText.setText("Clock has Started");
			Platform.runLater(() -> rectangleColor.setFill(Color.web("#b7f8cb")));
			timeText.setText("Activity started on " + formattedStartTime);
			warningActivityStart.setText(""); 
			warningActivityStop.setText(""); 
		}
	
	public void finishActivity(ActionEvent eventFinish) throws IOException {
		if(count == 1) {
			LocalDateTime stopTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss a");
			String formattedStopTime = stopTime.format(formatter);
			clockText.setText("Clock has Stopped");
			Platform.runLater(() -> rectangleColor.setFill(Color.web("#ff7063")));
			timeText.setText("Activity stopped on " + formattedStopTime);
			count = 0;
			warningActivityStop.setText(""); 
			warningActivityStart.setText("");			
		}
		else {
			warningActivityStop.setText("Have not started an activity yet."); 
		}
	}
}
