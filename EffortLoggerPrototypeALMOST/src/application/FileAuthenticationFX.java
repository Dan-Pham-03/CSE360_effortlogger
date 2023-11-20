package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FileAuthenticationFX extends Application {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, ZonedDateTime> userAccessStartTimes = new HashMap<>();
    private static Map<String, ZonedDateTime> userEditingStartTimes = new HashMap<>();
    private static final Map<String, ZonedDateTime> userEditingEndTimes = new HashMap<>();
    private String username;
    private TextArea logTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Authentication");
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        VBox root = new VBox(10);
        root.setMinSize(400, 300);
        Label usernameLabel = new Label("Enter your username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Enter your password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        logTextArea = new TextArea();
        logTextArea.setEditable(false);
        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(e -> showAddUserDialog());
        loginButton.setOnAction(e -> {
            username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticate(username, password)) {
                logTextArea.appendText("Authentication successful.\n\n");
                logAccessTime(username);

                // Create or append to the access log file
                createAccessLogFile();
                String filePath = "sample.txt"; // Windows

                try {
                    logTextArea.appendText("----------------------------------------------\n\n");
                    printFileContents(filePath);
                    logTextArea.appendText("----------------------------------------------\n\n");
                    logTextArea.appendText("\nDo you want to edit the file? (yes/no): ");

                    // Add a TextField for the user's response
                    TextField editResponseField = new TextField();
                    editResponseField.setOnAction(event -> handleFileEditingResponse(editResponseField.getText(), filePath));
                    root.getChildren().add(editResponseField);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                logTextArea.appendText("Authentication failed. Access denied.\n");
            }
        });

        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, logTextArea, addUserButton);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddUserDialog() {
        Stage addUserStage = new Stage();
        addUserStage.initModality(Modality.APPLICATION_MODAL);
        addUserStage.setTitle("Add User");
        VBox dialogVBox = new VBox(10);
        dialogVBox.setMinSize(300, 150);
        Label usernameLabel = new Label("New username:");
        TextField newUsernameField = new TextField();
        Label passwordLabel = new Label("New password:");
        PasswordField newPasswordField = new PasswordField();
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String newUsername = newUsernameField.getText();
            String newPassword = newPasswordField.getText();
            if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                userCredentials.put(newUsername, newPassword);
                logTextArea.appendText("User " + newUsername + " added successfully.\n");
                addUserStage.close();
            }
        });

        dialogVBox.getChildren().addAll(usernameLabel, newUsernameField, passwordLabel, newPasswordField, submitButton);
        Scene dialogScene = new Scene(dialogVBox);
        addUserStage.setScene(dialogScene);
        addUserStage.show();
    }

    private boolean authenticate(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    private void logAccessTime(String username) {
        userAccessStartTimes.put(username, ZonedDateTime.now(ZoneId.systemDefault()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T' HH:mm:ss");
        ZonedDateTime accessStartTime = userAccessStartTimes.get(username);
        String formattedStartTime = accessStartTime.format(formatter);
        logTextArea.appendText(username + " started accessing the file at: " + formattedStartTime + "\n");
    }

    private void logAccessToFile(String username, ZonedDateTime accessStartTime) {
        ZonedDateTime endTime = ZonedDateTime.now(ZoneId.systemDefault());
        long accessDuration = Duration.between(accessStartTime, endTime).getSeconds();
        storeUserAccessData(username, accessDuration, userEditingStartTimes.get(username), endTime);
    }

    private static void createAccessLogFile() {
        String trackingFilePath = "access_log.txt";
        File file = new File(trackingFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void storeUserAccessData(String username, long accessDuration, ZonedDateTime editingStartTime, ZonedDateTime editingEndTime) {
        String trackingFilePath = "access_log.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(trackingFilePath, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T' HH:mm:ss");
            String accessStartTime = userAccessStartTimes.get(username).format(formatter);
            String editingStartTimeStr = editingStartTime == null ? "N/A" : editingStartTime.format(formatter);
            String editingEndTimeStr = editingEndTime == null ? "N/A" : editingEndTime.format(formatter);
            String logEntry = "User: " + username + " | Access Start Time: " + accessStartTime +
                    " | Access Duration (seconds): " + accessDuration +
                    " | Editing Start Time: " + editingStartTimeStr +
                    " | Editing End Time: " + editingEndTimeStr;
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleFileEditingResponse(String editResponse, String filePath) {
        if (editResponse.equalsIgnoreCase("yes")) {
            openFileForEditing(filePath);
            logAccessToFile(username, userAccessStartTimes.get(username));
            userEditingStartTimes.put(username, ZonedDateTime.now(ZoneId.systemDefault()));
            logTextArea.appendText("The file is open for editing. Make your changes, save, and then close the editor.\n");
            logTextArea.appendText("Waiting for the file to be closed...\n");
        } else {
            logTextArea.appendText("File editing skipped.\n");
        }
    }
    private void openFileForEditing(String filePath) {
        try {
            File fileToEdit = new File(filePath);
            ProcessBuilder processBuilder = new ProcessBuilder("open", fileToEdit.getAbsolutePath());
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printFileContents(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            logTextArea.appendText(line + "\n");
        }
        reader.close();
    }
}
