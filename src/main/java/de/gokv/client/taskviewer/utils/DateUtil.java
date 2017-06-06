package de.gokv.client.taskviewer.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

import de.gokv.client.taskviewer.exceptions.InvalidDateException;

/**
 * Beinhaltet eine Methode zum Überprüfen des richtigen Formates (DD.MM.YYYY)
 * und zwei Methoden zum umwandeln von einem String in eine {@link LocalDate}-
 * Format.
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #isDateValid(String)}</b>: prüft ein Datum auf syntaktische
 * Korrektheit</li>
 * <li><b>{@link #parseDate(String)}</b>: wandelt einen {@link String} in ein
 * {@link LocalDate} um</li>
 * <li><b>{@link #parseDate(String, String)}</b>: wandelt einen {@link String}
 * in ein {@link LocalDate} um und gibt den Spaltennamen in der Fehlermeldung
 * zurück</li>
 * </ul>
 * </p>
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
	 * Legt ein {@link Pattern} im Format "ZZ.ZZ.ZZZZ" (Z = Ziffer) an.
	 * Vergleicht mit der {@link Pattern#matcher(CharSequence) matcher()}-Methode,
	 * ob der Parameter im erwartetem Format vorliegt und gibt ein boolischen Wert zurück.
	 * Anschließend wird versucht den String in ein LocalDate-Format zu konvertieren und
	 * gibt ein wieder ein boolischen Wert zurück.
	 * Bei einem Fehler wird die {@link DateTimeParseException} geworfen.
	 * 
	 * @param date
	 *            {@link String}: Zu konvertierendes Datum im Format TT.MM.JJJJ
	 * @return
	 * 		<ul>
	 *         <li><i>true</i>, wenn der String im korrektem Format vorliegt und
	 *         gleichzeitig umgewandelt werden kann.</li>
	 *         <li><i>false</i>, wenn der String nicht im korrektem Format
	 *         vorliegt oder nicht umgewandelt werden kann.</li>
	 *         </ul>
	 */
	public static boolean isDateValid(String date) {
		// String überprüfen, ob 
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
	 * Überprüft zuerst mit der {@link #isDateValid(String) is DateValid()}-Methode
	 * ein String auf syntaktische Korrektheit und
	 * konvertiert ihn zu einem Datums-Objekt um.
	 * Bei einem Fehler wird {@link InvalidDateException} geworfen.
	 * 
	 * @param date
	 *            {@link String}: Zu konvertierendes Datum im Format TT.MM.JJJJ
	 * @return Ein {@link LocalDate}-Objekt.
	 * @throws InvalidDateException
	 *             ohne Spaltenname
	 */
	public static LocalDate parseDate(String date) throws InvalidDateException {
		if (isDateValid(date)) {
			LocalDate d2;
			d2 = LocalDate.parse(date, df2);
			return d2;
		}
		throw new InvalidDateException("Fehler beim Konvertieren vom String \'%s\' in ein LocalDate-Format", date);
	}

	/**
	 * Versucht mit der {@link #parseDate(String) parseDate()}-Methode ein Strind
	 * in ein LocaDate-Format zu konverieren und wirf bei einem Fehler die
	 * {@link InvalidDateException}, mit der genauen Zeilen- und Spaltenangabe
	 * in der der Fehler auftrat.
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
			String msg = String.format("%s %s in der Spalte \'%s\'", e.getMessage(), System.lineSeparator(),
					columnName);
			throw new InvalidDateException(msg, date);
		}
	}
}
