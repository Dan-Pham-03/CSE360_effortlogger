package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.scene.Node;


public class sceneController {
	protected Stage stage;
	protected Scene scene;
	protected Parent root;
	
	//PAGE SHIFTING RELATED BUTTON EVENTS
	public void switchToLogin(ActionEvent event_LO) throws IOException{
		stage = (Stage) ((Node)event_LO.getSource()).getScene().getWindow(); // Gets the window of the application where the button was clicked, and stores it in the stage variable
		stage.setResizable(false); // User can't adjust window
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml")); // Loads the scene from the LoginScreen.fxml
		Scene scene = new Scene(root, 575, 400); // Sets width and height of login screen
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene); // Set the scene to the primary stage
		stage.show(); // Display the stage
	}
	
	// Changes the window to PP card
	public void switchToPPCard(ActionEvent eventPP) throws IOException{
		root = FXMLLoader.load(getClass().getResource("PPcard.fxml"));
		stage = (Stage)((Node)eventPP.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Changes the window to the EffortLogger console
	public void switchToEffortConsole(ActionEvent eventEC) throws IOException {
		root = FXMLLoader.load(getClass().getResource("EffortConsole.fxml"));
		stage = (Stage)((Node)eventEC.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Changes the window to the EffortLogger editor card
	public void switchToELogEditor(ActionEvent eventELE)throws IOException {
		root = FXMLLoader.load(getClass().getResource("ELogEditor.fxml"));
		stage = (Stage)((Node)eventELE.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Changes the window to the Defect console
	public void switchToDefectConsole(ActionEvent eventDC) throws IOException{
		root = FXMLLoader.load(getClass().getResource("DefectConsole.fxml"));
		stage = (Stage)((Node)eventDC.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Changes the window to the definitions console
	public void switchToDefinitions(ActionEvent eventD) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Definitions.fxml"));
		stage = (Stage)((Node)eventD.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToLogs(ActionEvent eventL) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Logs.fxml"));
		stage = (Stage)((Node)eventL.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToLogManager(ActionEvent eventLM) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LogManager.fxml"));
		stage = (Stage)((Node)eventLM.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}	
}
