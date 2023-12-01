package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogConsole {
    private Stage logWindow;
    private final ObservableList<LogEntry> logEntries;

    public LogConsole(ObservableList<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public void showLogWindow() {
        if (logWindow == null) {
            logWindow = new Stage();
            logWindow.setTitle("Activity Logs");

            TableView<LogEntry> logTable = new TableView<>(logEntries);
            setupLogTable(logTable);

            VBox logLayout = new VBox(10);
            logLayout.getChildren().add(logTable);

            Scene logScene = new Scene(logLayout, 800, 400);
            logWindow.setScene(logScene);
        }
        logWindow.show();
    }


    private void setupLogTable(TableView<LogEntry> logTable) {
        // Columns setup
        TableColumn<LogEntry, Integer> indexCol = new TableColumn<>("Index");
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));

        TableColumn<LogEntry, String> startTimeCol = new TableColumn<>("Start Time");
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartTime"));

        TableColumn<LogEntry, String> stopTimeCol = new TableColumn<>("Stop Time");
        stopTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedStopTime"));

        TableColumn<LogEntry, String> elapsedTimeCol = new TableColumn<>("Elapsed Time");
        elapsedTimeCol.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));

        TableColumn<LogEntry, String> lifeCycleCol = new TableColumn<>("Life Cycle");
        lifeCycleCol.setCellValueFactory(new PropertyValueFactory<>("lifeCycle"));

        TableColumn<LogEntry, String> projectCol = new TableColumn<>("Project");
        projectCol.setCellValueFactory(new PropertyValueFactory<>("project"));

        logTable.getColumns().addAll(indexCol, startTimeCol, stopTimeCol, elapsedTimeCol, lifeCycleCol, projectCol);
    }
}
