package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Main extends Application {
	private static Stage stg; //testing to see if necessary
    protected Stage stage;
	protected Scene scene;
	protected Parent root;
	
	@Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	//Unused for now but keeping around just case
	public void changeStage(String fxml) throws IOException { // Method that changes the scene
		Parent anchor = FXMLLoader.load(getClass().getResource(fxml)); // Loads the argument fxml file
		stg.getScene().setRoot(anchor); // 
	}
    // The main method that launches the JavaFX application
    public static void main(String[] args) {
    	launch(args);
    }
}
