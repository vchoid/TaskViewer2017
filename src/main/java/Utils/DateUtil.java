package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

import Exceptions.InvalidDateException;

/**
 * Beinhaltet eine Methode zum �berpr�fen des richtigen Formates (DD.MM.YYYY)
 * und zwei Methoden zum umwandeln von einem String in eine {@link LocalDate}-
 * Format.
 * 
 * <p><b>Funktionen:</b>
 * <ul>
 * <li><b>isDateValid({@link String})</b>: pr�ft ein Datum auf syntaktische Korrektheit
 * </li>
 * <li><b>parseDate({@link String})</b>: wandelt einen {@link String} in ein
 * {@link LocalDate} um</li>
 * <li><b>parseDate({@link String}, {@link String})</b>: wandelt einen {@link String} in ein
 * {@link LocalDate} um und gibt den Spaltennamen in der Fehlermeldung zur�ck
 * </li>
 * </ul>
 * </p>
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
	 * �berpr�ft ob ein sich String im Datums-Format (dd.mm.yyyy) befindet und
	 * ins LocalDate-Format umgewandelt werden kann und gibt anschlie�end ein
	 * {@link Boolean} zur�ck.
	 * 
	 * @param date
	 *            {@link String}: Zu konvertierendes Datum im Format TT.MM.JJJJ
	 * @return
	 * 		<ul>
	 *         <li><i>true</i>, wenn der String im korrektem Format vorliegt und
	 *         gleichzeitig umgewandelt werden kann.</li>
	 *         <li><i>false</i>, wenn der String im nicht korrektem Format
	 *         vorliegt oder nicht umgewandelt werden kann.</li>
	 *         </ul>
	 */
	public static boolean isDateValid(String date) {
		// String �berpr�fen, ob "ZZ.ZZ.ZZZZ" (Z = Ziffer)
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
	 * �berpr�ft einen Text auf syntaktische Korrektheit und wandelt ihn in ein
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
	 * �berpr�ft einen Text auf syntaktische Korrektheit und wandelt ihn in ein
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
			String msg = String.format("%s %s in der Spalte \"%s\"", e.getMessage(), System.lineSeparator(),
					columnName);
			throw new InvalidDateException(msg, date);
		}
	}
}
