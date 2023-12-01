package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class effortConsoleController extends sceneController implements Initializable {
    private LocalDateTime startTime;
    private int count = 0;
    private int index = 0;
    private ObservableList<LogEntry> logEntries = FXCollections.observableArrayList();
    private LogConsole logConsole = new LogConsole(logEntries);

    @FXML private ComboBox<String> comboBoxProject;
    @FXML private ComboBox<String> comboBoxLifeCycle;
    @FXML private ComboBox<String> comboBoxCategory;
    @FXML private ComboBox<String> comboBoxPlan;
    @FXML private Label clockText;
    @FXML private Label timeText;
    @FXML private Rectangle rectangleColor;
    @FXML private Label elapsedTime;
    @FXML private Label warningActivityStart;
    @FXML private Label warningActivityStop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	 //super.initialize(url, resourceBundle);
         logConsole = new LogConsole(LogData.getLogEntries());
        comboBoxProject.setItems(FXCollections.observableArrayList("Business Project", "Development Project"));
        comboBoxLifeCycle.setItems(FXCollections.observableArrayList("Planning", "Information Gathering", "Information Understanding", "Verifying", "Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"));
        comboBoxCategory.setItems(FXCollections.observableArrayList("Plans", "Deliverables", "Interruptions", "Defects", "Others"));
        comboBoxPlan.setItems(FXCollections.observableArrayList("Conceptual Design", "Project Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"));
    }

    public void startActivity(ActionEvent eventStart) {
        // Implement the logic to start the activity
        if (count == 1) {
            warningActivityStart.setText("Please finish your current activity before starting a new one.");
            warningActivityStop.setText("");
            return;
        } else if (comboBoxProject.getSelectionModel().getSelectedItem() == null || comboBoxLifeCycle.getSelectionModel().getSelectedItem() == null || comboBoxCategory.getSelectionModel().getSelectedItem() == null || comboBoxPlan.getSelectionModel().getSelectedItem() == null) {
            warningActivityStart.setText("Please have a selected item in each of the drop down lists.");
            warningActivityStop.setText("");
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
        elapsedTime.setText("");
        warningActivityStart.setText("");
        warningActivityStop.setText("");
    }

    public void finishActivity(ActionEvent eventFinish) {
        // Implement the logic to finish the activity and log the entry
        if (count == 1) {
            LocalDateTime stopTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime, stopTime);
            long elapsedInMinutes = duration.toMinutes();
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            long seconds = duration.minusMinutes(elapsedInMinutes).getSeconds();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss a");
            String formattedStopTime = stopTime.format(formatter);

            // Create and add a new LogEntry
            LogEntry logEntry = new LogEntry(
                index, 
                startTime, 
                stopTime, 
                String.format("%02d:%02d:%02d", hours, minutes, seconds), 
               
                comboBoxLifeCycle.getValue(), 
                comboBoxProject.getValue()
            );
            LogData.addLogEntry(logEntry);

            clockText.setText("Clock has Stopped");
            Platform.runLater(() -> rectangleColor.setFill(Color.web("#ff7063")));
            timeText.setText("Activity stopped on " + formattedStopTime);
            elapsedTime.setText("Elapsed time: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds.");

            count = 0;
            warningActivityStop.setText("");
            warningActivityStart.setText("");
        } else {
            warningActivityStop.setText("Have not started an activity yet.");
        }
    }

    public void showLogs(ActionEvent event) {
        logConsole.showLogWindow();
    }
}
