package application;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;

public class PPcardHandler extends sceneController implements Initializable{
	
	@FXML
	private ComboBox<String> comboBoxUS;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		comboBoxUS.setItems(FXCollections.observableArrayList("User Story Name 1", "User Story Name 2"));
	}
	//Jframe
	protected JFrame ppCframe;
	protected JLabel name;
	protected JLabel points;
	protected JTextArea tagsarea;
	protected JTextArea textarea;
	protected JScrollPane scroll;
	private int usPoints;
	
	public void createPPcard(ActionEvent pp_C) throws IOException {
		ppCframe = new JFrame("Planning Poker Card");
		ppCframe.setLayout(new BorderLayout());
		
		//Creating Format/parameters for a new pane
		JPanel topleftp = new JPanel(new GridBagLayout());
		ppCframe.add(topleftp, BorderLayout.CENTER);
		topleftp.setBorder(BorderFactory.createTitledBorder("Information"));
		GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.weightx = 0.5;
        labelConstraints.weighty = 1;
        //labelConstraints.insets = new Insets(5, 10, 5, 10);  
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.anchor = GridBagConstraints.WEST;
        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.weightx = 0.5;
        fieldConstraints.weighty = 1;
        //fieldConstraints.insets = new Insets(5, 10, 5, 10);
        
        //Insert getter for the name titles for the User Story
      	String text = comboBoxUS.getValue();
      	name = new JLabel(text);
        topleftp.add(new JLabel(text), labelConstraints);
		
        //Insert Getter for Project/Phase if exists
        
        //Insert getter for the tags
		String tags = "Development, privacy, etc";
		text = "Tags: " + tags;
		tagsarea = new JTextArea(text);
		topleftp.add(tagsarea, fieldConstraints);
		
		//insert getter for the US Points using value from comboBox
		usPoints = 10;
		String usp = "User Points: "+usPoints; 
		points = new JLabel(usp);
		topleftp.add(points);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		ppCframe.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBorder(BorderFactory.createTitledBorder("User Story Details"));
		
		//Insert Area to improve to grab logs and fit into this textarea
		//Call textdataHandler and grab Active user account
		JTextArea detailArea = new JTextArea("LOGS~~~~~~~~~~~~ DEFECTS~~~~~~~~~~~~~~~~~~",6, 30);
		bottomPanel.add(new JScrollPane(detailArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		
		//grabs ActiveAccount, accesses role
		UserAccounts activeAccount = UserAccounts.getActiveAccount();
		String role = activeAccount.getRole();
		boolean hasAccess = false;
		if (role.equals("DEVELOPER") || role.equals("SUPERVISOR") || role.equals("ADMINISTRATOR") ) {
			hasAccess = true;
		}
		//seeing if this content is correctly censcored 
		String data = "Private Information: blah blah blah" 
				+ "Information about funding: blah blah blah" 
				+ "Information about what needs to be planned: blah blah";
		textdataHandler newtext = new textdataHandler(data);
		//insert if
		if (!hasAccess) { //if doesn't have access, censor data
			data = newtext.censorDataField(newtext.getData());
		}	
		detailArea = new JTextArea(data, 6, 30);
		bottomPanel.add(new JScrollPane(detailArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		
		ppCframe.pack();
		ppCframe.setVisible(true);
		
		//text = String.format("| %-10s | %-8s |%n\", \"LOGS\", \"DEFECTS\")"
		
	}

	
}
