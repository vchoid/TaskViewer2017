package de.gokv.client.reader;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidID {

	boolean checkID = false;
	String idOK;
	
	public String validID(String id){
		
		
//			 System.out.print("Überprüfe id: " + id + "...");

			// Länge überprüfen
			if (StringUtils.length(id) != 32) {
//				 System.out.println("Nicht 32 Zeichen lang!");
			}

			Pattern p = Pattern.compile("[A-F0-9]{32}");
			java.util.regex.Matcher m = p.matcher(id);
			if (m.find()) {
//				System.out.println("OK");
				idOK = id;
				checkID = true;
			} else {
//				 System.out.println("Entspricht nicht dem Hexadezimal Format");
			}

		
		return idOK;
	}
	
	
}
