package de.gokv.client.reader;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidDate {

	boolean checkDate = false;
	String dateOK;

	public boolean validDate(String date) {

//		System.out.print("Teste /Datum: " + date + "...");

		// Länge überprüfen
		if (date.length() != 10) {
//			System.out.println("Nicht 10 Zeichen lang!");
			checkDate = false;
		} else {
			checkDate = true;
		}

		// String überprfen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
		Pattern p = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{2}");
		java.util.regex.Matcher m = p.matcher(date);
		if (m.find()) {
			checkDate = true;
		} else {
//			System.out.println("Entspricht nicht dem RegEx");
			checkDate = false;
		}
		
		if (checkDate) {
			try {

				DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				df.setLenient(false);

				Date d1 = df.parse(date);
				dateOK = df.format(d1);
//				System.out.println("ok ==> Datumswert ist " + dateOK);

			} catch (ParseException e) {
//				System.out.println("nicht ok!!!");
				checkDate = false;
			}
		}
		return checkDate;
	}
}
