package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class logsController extends sceneController implements Initializable {

    @FXML
    private TableView<LogEntry> table;

    @FXML
    private TableColumn<LogEntry, Integer> index;

    @FXML
    private TableColumn<LogEntry, String> date;

    @FXML
    private TableColumn<LogEntry, String> start;

    @FXML
    private TableColumn<LogEntry, String> stop;

    @FXML
    private TableColumn<LogEntry, String> elapsed;

    @FXML
    private TableColumn<LogEntry, String> lifeCycle;

    @FXML
    private TableColumn<LogEntry, String> project;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        stop.setCellValueFactory(new PropertyValueFactory<>("stop"));
        elapsed.setCellValueFactory(new PropertyValueFactory<>("elapsed"));
        lifeCycle.setCellValueFactory(new PropertyValueFactory<>("lifeCycle"));
        project.setCellValueFactory(new PropertyValueFactory<>("project"));

        // Set the table's items
        table.setItems(LogData.getLogEntries());

        LogData.getLogEntries().addListener((ListChangeListener.Change<? extends LogEntry> c) -> {
            table.refresh();
            
        });
    }
}
