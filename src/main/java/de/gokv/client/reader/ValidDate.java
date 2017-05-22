package de.gokv.client.reader;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidDate {

	boolean checkDate = false;
	String dateOK;

	public String validDate(String date) {

		try {
			// System.out.print("Teste Datum: " + date + "...");

			// Länge überprüfen
			if (date.length() != 10) {
				// System.out.println("Nicht 10 Zeichen lang!");
			}

			// String überprfen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
			Pattern p = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
			java.util.regex.Matcher m = p.matcher(date);
			if (m.find()) {
				checkDate = true;
			} else {
				// System.out.println("Entspricht nicht dem RegEx");
			}

			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			df.setLenient(false);

			Date d1 = df.parse(date);
			dateOK = df.format(d1);
			// System.out.println("ok ==> Datumswert ist " + okDate);
			checkDate = true;
		} catch (ParseException e) {
			// System.out.println("nicht ok!!!");
			checkDate = false;
		}
		return dateOK;
	}
}
