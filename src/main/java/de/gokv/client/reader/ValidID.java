package de.gokv.client.reader;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidID {

	boolean checkID = false;
	String idOK;
	
	public String validID(String id){
		
		
			 System.out.print("Überprüfe id: " + id + "...");

			// Länge überprüfen
			if (StringUtils.length(id) != 32) {
				 System.out.println("Nicht 32 Zeichen lang!");
			}

			Pattern p = Pattern.compile("\\w");
			java.util.regex.Matcher m = p.matcher(id);
			if (m.find()) {
				checkID = true;
				System.out.println("OK");
			} else {
				 System.out.println("Entspricht nicht dem RegEx");
			}

		
		return idOK;
	}
	
	
}
