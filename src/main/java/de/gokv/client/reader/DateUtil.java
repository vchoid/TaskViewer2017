package de.gokv.client.reader;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {

	private static DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public static boolean isValidDate(String date) {
		// String überprüfen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
		Pattern p = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
		java.util.regex.Matcher m = p.matcher(date);
		if (!m.matches()) {
			return false;
		}

		df.setLenient(false);
		try {
			df.parse(date);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}
	
	public static Date parseDate(String date) throws InvalidDateException{
		if(isValidDate(date)){
			Date d;
			try {
				d = df.parse(date);
				return d;
			} catch (ParseException e) {
				throw new InvalidDateException(e);
			}
		}
		throw new InvalidDateException("Fehler beim formatieren von String in Date");
	}
}
