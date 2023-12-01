package application;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private final SimpleStringProperty index;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;
    private final SimpleStringProperty elapsedTime;
    private final SimpleStringProperty lifeCycle;
    private final SimpleStringProperty project;

    public LogEntry(int index, LocalDateTime startTime, LocalDateTime stopTime, String elapsedTime, String lifeCycle, String project) {
        this.index = new SimpleStringProperty(String.valueOf(index));
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.elapsedTime = new SimpleStringProperty(elapsedTime);
        this.lifeCycle = new SimpleStringProperty(lifeCycle);
        this.project = new SimpleStringProperty(project);
    }

    // Getters
    public String getIndex() {
        return index.get();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public String getElapsedTime() {
        return elapsedTime.get();
    }

    public String getLifeCycle() {
        return lifeCycle.get();
    }

    public String getProject() {
        return project.get();
    }

    // Formatters for DateTime
    public String getFormattedStartTime() {
        return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getFormattedStopTime() {
        return stopTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
