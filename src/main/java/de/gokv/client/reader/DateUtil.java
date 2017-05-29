package de.gokv.client.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

/**
 * Beinhaltet diverse Methoden zum Umgang mit Datums-Objekten und deren
 * Umwandlung.
 * 
 * Funktionen:
 * <ul>
 * <li><b>isDateValid(String)</b>: prüft ein Datum auf syntaktische Korrektheit</li>
 * <li><b>parseDate(String)</b>: wandelt einen {@link String} in ein {@link LocalDate} um</li>
 * <li><b>parseDate(String, String)</b>: wandelt einen {@link String} in ein {@link LocalDate} um und gibt den Spaltennamen in der Fehlermeldung zurück</li>
 * </ul>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * @see java.time.LocalDate
 *
 */
public class DateUtil {

	// ResolveStyle.STRICT ==> verhindert: z.B. 29.02.2017 => 01.03.2017
	private static DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd.MM.uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	/**
	 * @param date
	 * @return
	 */
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

	/**
	 * Überprüft einen Text auf syntaktische Korrektheit und wandelt ihn in ein
	 * Datums-Objekt um
	 * 
	 * @param date
	 *            {@link String}: Zu konvertierendes Datum im Format TT.MM.JJJJ
	 * @return Ein {@link LocalDate}-Objekt des zu konvertierenden Strings
	 * @throws InvalidDateException
	 *             ohne Spaltenname
	 */
	public static LocalDate parseDate(String date) throws InvalidDateException {
		if (isDateValid(date)) {
			LocalDate d2;
			d2 = LocalDate.parse(date, df2);
			return d2;
		}
		throw new InvalidDateException("Fehler beim Konvertieren vom String \"%s\" in ein LocalDate-Format", date);
	}

	/**
	 * Überprüft einen Text auf syntaktische Korrektheit und wandelt ihn in ein
	 * Datums-Objekt um
	 * 
	 * @param date
	 *            {@link String}: Zu konvertierendes Datum im Format TT.MM.JJJJ
	 * @param columnName
	 *            {@link String}: Name der Spalte in der CSV-Datei
	 * @return Ein {@link LocalDate}-Objekt des zu konvertierenden Strings
	 * @throws InvalidDateException
	 *             inkl. Spaltenname
	 */
	public static LocalDate parseDate(String date, String columnName) throws InvalidDateException {
		try {
			return parseDate(date);
		} catch (InvalidDateException e) {
			String msg = String.format("%s in der Spalte \"%s\"", e.getMessage(),
					columnName);
			throw new InvalidDateException(msg, date);
		}
	}
}
