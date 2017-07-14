package de.gokv.client.taskviewer.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.gokv.client.taskviewer.exceptions.AbstractException;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.view.Frame_ExceptionMsg;

/**
 * Beinhaltet eine Methode zum Überprüfen des richtigen Formates (DD.MM.YYYY)
 * und zwei Methoden zum umwandeln von einem String in eine {@link LocalDate}-
 * Format.
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
 * TODO JAVADOC fertig schreiben.
 * <li><b>{@link #datetoString(String)}</b>: ...</li>
 * <li><b>{@link #localDateToString(LocalDate)}</b>: ...</li>
 * <li><b>{@link #dateFromHttpsClientToString(String)}</b>: ...</li>
 * <li><b>{@link #dateWithTimeFromHttpsClientToString(String)}</b>: ...</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.1
 * @see java.time.LocalDate
 *
 */
public class DateUtil {

	// ResolveStyle.STRICT ==> verhindert: z.B. 29.02.2017 => 01.03.2017
	private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.uuuu")
			.withResolverStyle(ResolverStyle.STRICT);
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	public final static DateTimeFormatter dTf_request = DateTimeFormatter.ofPattern("uuuu'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'");


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
		Matcher m = p.matcher(date);
		if (!m.matches()) {
			return false;
		}
		try {
			dateTimeFormat.parse(date);
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
		try {
			if (isDateValid(date)) {
				LocalDate d2;
				d2 = LocalDate.parse(date, dateTimeFormat);
				return d2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new InvalidDateException("Formatfehler vom Datum", "Fehler beim Konvertieren vom String \'%s\' in ein LocalDate-Format",0, date);
	}
	
	/**
	 * Konvertiert ein {@link Date}-Objekt in ein {@link LocalDate}.
	 * 
	 * @param date
	 * @return
	 * @throws InvalidDateException
	 */
	public static LocalDate parseDate(Date date) throws InvalidDateException{
		return parseDate(simpleDateFormat.format(date));
	}
	
	
//	public static LocalDate parseDate(Object date) throws InvalidDateException{
//		return parseDate(sDf_request.format(date));
//	}
	
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
			throw new InvalidDateException("Formatfehler vom Datum",msg, 0,date);
		}
	}

	/**
	 * Konvertiert ein Datum(String) zu einem String im Format "mm.dd.yyyy".
	 * 
	 * @param strDate
	 * @return
	 */
	public static String datetoString(String strDate) {
		LocalDate lDate = LocalDate.parse(strDate, DateUtil.dTf_request);
		strDate = lDate.getDayOfMonth() + "." + lDate.getMonthValue() + "." + lDate.getYear();
		return strDate;
	}

	/**
	 * Konvertiert ein Datum(LocalDate) zu einem String im Format "mm.dd.yyyy".
	 * 
	 * @param lDate
	 * @return
	 */
	public static String localDateToString(LocalDate lDate) {
		return lDate.getDayOfMonth() + "." + lDate.getMonthValue() + "." + lDate.getYear();
	}

	/**
	 * Holt ein Datum über den HTTPSClient im Format String und speichert es in
	 * einer Variable. Wandelt das Datum in eine LocalDate-Format um.
	 * Überschreibt die String Variable im Format "mm.dd.yyyy" und gibt sie
	 * zurück.
	 * 
	 * @param strDate
	 * @return
	 */
	public static String dateFromHttpsClientToString(String httpClientTitle) {
		String httpClient = HTTPSClient.task.get(httpClientTitle).toString();
		if (!(httpClient.equals("empty"))) {
			LocalDateTime lDate = LocalDateTime.parse(httpClient, DateUtil.dTf_request);
			httpClient = lDate.getDayOfMonth() + "." + lDate.getMonthValue() + "." + lDate.getYear();
		}
		return httpClient;
	}
	/**
	 * Holt ein Datum über den HTTPSClient im Format String und speichert es in
	 * einer Variable. Wandelt das Datum in eine LocalDateTime-Format um.
	 * Überschreibt die String Variable im Format "mm.dd.yyyy hh:mm:ss" und gibt sie
	 * zurück.
	 * 
	 * @param httpClientTitle
	 * @return
	 */
	public static String dateWithTimeFromHttpsClientToString(String httpClientTitle) {
		String httpClient = HTTPSClient.task.get(httpClientTitle).toString();
		if (!(httpClient.equals("empty"))) {
			LocalDateTime lDate = LocalDateTime.parse(httpClient, DateUtil.dTf_request);
			httpClient = lDate.getDayOfMonth() + "." + lDate.getMonthValue() + "." + lDate.getYear() + " (Uhrzeit: "
					+ lDate.getHour() + ":" + lDate.getMinute() + ":" + lDate.getSecond() + ")";
		}
		return httpClient;
	}
}
