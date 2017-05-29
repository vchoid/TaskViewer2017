package de.gokv.client.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

public class DateUtil {

	// ResolveStyle.STRICT ==> verhindert: z.B. 29.02.2017 => 01.03.2017
	private static DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd.MM.uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	public static boolean isDateValid(String date) {
		// String überprüfen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
		Pattern p = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
		java.util.regex.Matcher m = p.matcher(date);
		if (!m.matches()) {
			return false;
		}
		try {
			df2.parse(date);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static LocalDate parseDate(String date) throws InvalidDateException {
		if (isDateValid(date)) {
			LocalDate d2;
			d2 = LocalDate.parse(date, df2);
			return d2;
		}
		throw new InvalidDateException("Fehler beim Konvertieren vom String \"%s\" in ein LocalDate-Format", date);
	}
}
