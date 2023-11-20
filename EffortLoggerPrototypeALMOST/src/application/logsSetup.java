package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class logsSetup {
    
    private final SimpleIntegerProperty index;
    private final SimpleStringProperty date;
    private final SimpleStringProperty startTime;
    private final SimpleStringProperty stopTime;
    private final SimpleStringProperty minsPassedColumn;
    private final SimpleStringProperty lifeCycle;
    private final SimpleStringProperty category;
    //private final SimpleStringProperty plan;

    public logsSetup(int index, String date, String startTime, String stopTime, String minsPassedColumn, String lifeCycle, String category) {
        this.index = new SimpleIntegerProperty(index);
        this.date = new SimpleStringProperty(date);
        this.startTime = new SimpleStringProperty(startTime);
        this.stopTime = new SimpleStringProperty(stopTime);
        this.minsPassedColumn = new SimpleStringProperty(minsPassedColumn);
        this.lifeCycle = new SimpleStringProperty(lifeCycle);
        this.category = new SimpleStringProperty(category);
        //this.plan = new SimpleStringProperty(plan);
    }

    // getters
    public int getIndex() {
        return index.get();
    }
    
    public String getDate() {
        return date.get();
    }
    
    public String getStartTime() {
        return startTime.get();
    }

    public String getStopTime() {
        return stopTime.get();
    }

    public String getMinsPassedColumn() {
        return minsPassedColumn.get();
    }

    public String getLifeCycle() {
        return lifeCycle.get();
    }

    public String getCategory() {
        return category.get();
    }

    /*public String getPlan() {
        return plan.get();
    }*/

    // setters
    public void setIndex(int value) {
        index.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setStartTime(String value) {
        startTime.set(value);
    }

    public void setStopTime(String value) {
        stopTime.set(value);
    }

    public void setMinsPassedColumn(String value) {
        minsPassedColumn.set(value);
    }

    public void setLifeCycle(String value) {
        lifeCycle.set(value);
    }

    public void setCategory(String value) {
        category.set(value);
    }

    /*public void setPlan(String value) {
        plan.set(value);
    }*/
}
