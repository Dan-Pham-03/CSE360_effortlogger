package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textdataHandler {
	//Function to be utilized to make information censored
		//if Roles don't align with access
	String information;
		public textdataHandler(String data) {
			information = data;
		}
		public String getData() {
			return information;
		}
		public String censorDataField(String data) { //multipurpose for any inserted datafieid
			String df = data;
			char[] chars = df.toCharArray();
			String newdf;
			for (int i = 1; i < chars.length; i++) {
				chars[i] = '*'; //replaces with *
			}
			newdf = String.valueOf(chars);
			return newdf;
		}
		/*If wanting to develop a text filter for cuss words
		public String cussWordFilter(String data) {
			String df = data; 
			char [] thing = df.toCharArray();
			String newdf = "blahblah";
			
			return newdf;
		}*/
		public boolean containsIllegal (String toExamine){
			Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
		    Matcher matcher = pattern.matcher(toExamine);
		    return matcher.find();
		}
		public boolean textRestrictor(String text, int limit) {
			boolean notAllowed = false;
			if (text.length() >= limit && containsIllegal(text)){
				notAllowed = true;
			}
			return notAllowed;
		}
}
