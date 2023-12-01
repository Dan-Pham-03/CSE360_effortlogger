package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogData {
    private static final ObservableList<LogEntry> logEntries = FXCollections.observableArrayList();

    public static void addLogEntry(LogEntry entry) {
        logEntries.add(entry);
    }

    public static ObservableList<LogEntry> getLogEntries() {
        return logEntries;
    }
}
