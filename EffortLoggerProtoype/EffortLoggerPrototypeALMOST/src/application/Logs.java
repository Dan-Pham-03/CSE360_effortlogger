/*package application;

import java.util.ArrayList;
import java.util.List;

public class Logs {
    private String creator;
    private String team;
    private String dateCreated;
    private String timeStart;
    private String timeEnd;
    private String project;    
    private String effortCategory;
    private String lifeCycleStep;

    // Lists to store log entries
    private List<String> effortLogs;
    private List<String> defectLogs;

    public Logs() {
        this.effortLogs = new ArrayList<>();
        this.defectLogs = new ArrayList<>();
    }

    // Getters and Setters
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getEffortCategory() {
        return effortCategory;
    }

    public void setEffortCategory(String effortCategory) {
        this.effortCategory = effortCategory;
    }

    public String getLifeCycleStep() {
        return lifeCycleStep;
    }

    public void setLifeCycleStep(String lifeCycleStep) {
        this.lifeCycleStep = lifeCycleStep;
    }

    // Methods to create logs
    public void createEffortLog(String creator, String team, String project, String dateCreated, String timeStart, String timeEnd, String lifeCycleStep, String effortCategory) {
        this.creator = creator;
        this.team = team;
        this.dateCreated = dateCreated;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.project = project;
        this.lifeCycleStep = lifeCycleStep;
        this.effortCategory = effortCategory;
        String effortLog = produceEntry();
        effortLogs.add(effortLog);
    }

    public void createDefectLog(String creator, String defectDescription, String impact, String stepsToReproduce) {
        // Assuming these parameters are relevant for a defect log
        String defectLog = "Defect Log: " + creator + ", " + defectDescription + ", " + impact + ", " + stepsToReproduce;
        defectLogs.add(defectLog);
    }

    private String produceEntry() {
        return creator + "|" + team + "|"+ project+"|Date Created:"+ dateCreated+"|"+timeStart+"-"+
                timeEnd+"|"+lifeCycleStep+"|"+effortCategory;
    }

    // Methods to display logs
    public void displayEffortLogs() {
        System.out.println("Effort Logs:");
        for (String log : effortLogs) {
            System.out.println(log);
        }
    }

    public void displayDefectLogs() {
        System.out.println("Defect Logs:");
        for (String log : defectLogs) {
            System.out.println(log);
        }
    }
}
*/