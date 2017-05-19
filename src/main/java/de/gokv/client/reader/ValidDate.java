package de.gokv.client.reader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidDate {

	boolean dateCheck = false;
	
	public void validDate(String date) {
		
		String[] testDates = {date};
		
		for (String testDate : testDates) {
//			System.out.print("Teste Datum: " + testDate + "...");

			// Länge überprfen
			if (testDate.length() != 10) {
//				System.out.println("Nicht 10 Zeichen lang!");
				
				continue;
			}

			// String überprfen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
			Pattern p = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
			java.util.regex.Matcher m = p.matcher(testDate);
			if (m.find()) {
				; // empty Statement
			} else {
//				System.out.println("Entspricht nicht dem RegEx");
				continue;
			}

			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			df.setLenient(false);

			try {
				
				Date d1 = df.parse(testDate);
				String okDate = df.format(d1);
//				System.out.println("ok ==> Datumswert ist " + okDate);
				dateCheck = true;
			} catch (ParseException e) {
//				System.out.println("nicht ok!!!");
				dateCheck = false;
			}
		}
	}
}
