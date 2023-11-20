package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Logs extends logsSetup{
	public Logs(int index, String date, String startTime, String stopTime, String minsPassedColumn, String lifeCycle,
			String category) {
		super(index, date, startTime, stopTime, minsPassedColumn, lifeCycle, category);
		// TODO Auto-generated constructor stub
	}

	private String creator;
	private String team;
	private String dateCreated;
	private String timeStart;
	private String timeEnd;
	private String project;	
	private String effortCategory;
	private String lifeCycleStep;
	
	public String produceLogEntry() { //getter
		String entry = "";
		entry = creator + "|" + team + "|"+ project+"|Date Created:"+ dateCreated+"|"+timeStart+"-"+
				timeEnd+"|"+lifeCycleStep+"|"+effortCategory;
		return entry;
	}
	
	
}
