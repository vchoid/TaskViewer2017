package de.gokv.client.taskviewer;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;


/**
 *  Testen der {@link DateUtil}-Klasse.
 * 
 * <p><b>Tests:</b>
 * <ul>
 * <li><b>{@link #testValidDate()}</b>: �berpr�ft, ob bei einem g�ltigem Datum, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>true</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidDateWithChar()}</b>: �berpr�ft, ob bei einem ung�ltigem Datum, mit einem ung�ltigem Zeichen, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidDateSchaltjahr()}</b>: �berpr�ft, ob bei einem ung�ltigem Schaltjahr, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testValidDateSchaltjahr()}</b>:  �berpr�ft, ob bei einem g�ltigem Schaltjahr, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>true</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidDateMonth()}</b>: �berpr�ft, ob bei einem ung�ltigem Monat, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidShortYear()}</b>: �berpr�ft, ob bei einem ung�ltigem Datum, mit zu kurzer Jahreszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidShortMonth()}</b>: �berpr�ft, ob bei einem ung�ltigem Datum, mit zu kurzer Monatszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testInvalidShortDay()}</b>: �berpr�ft, ob bei einem ung�ltigem Datum, mit zu kurzer Tageszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zur�ckgegeben wird.</li>
 * <li><b>{@link #testParseValidDate()}</b>: �berpr�ft, ob bei einem g�ltigem Datum, die {@link InvalidDateException} geworfen wird</li>
 * <li><b>{@link #testParseInvalidDate()}</b>: �berpr�ft, ob bei einem ung�ltigem Datum, die {@link InvalidDateException} geworfen wird</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 *
 */

public class TestDateUtil {

	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein g�ltiges Datum.
	 */
	@Test
	public void testValidDate() {
		Assert.assertTrue("...g�ltiges Datum", DateUtil.isDateValid("18.03.2015"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ung�ltiges Datum, weil es ein ung�ltiges Zeichen enth�lt.
	 */
	@Test
	public void testInvalidDateWithChar() {
		Assert.assertFalse("...ung�ltiges Datum, weil es ein ung�ltiges Zeichen enth�lt.",
				DateUtil.isDateValid("O9.02.20l7"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ung�ltiges Datum, weil es kein Schaltjahr ist.
	 */
	@Test
	public void testInvalidDateSchaltjahr() {
		Assert.assertFalse("...ung�ltiges Datum, weil es kein Schaltjahr ist.", DateUtil.isDateValid("29.02.2017"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein g�ltiges Datum im Schaltjahr.
	 */
	@Test
	public void testValidDateSchaltjahr() {
		Assert.assertTrue("...g�ltiges Datum im Schaltjahr.", DateUtil.isDateValid("29.02.2016"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ung�ltigen Monat.
	 */
	@Test
	public void testInvalidDateMonth() {
		Assert.assertFalse("...ung�ltiger Monat", DateUtil.isDateValid("29.13.2017"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ung�ltiges Datumsformat vom Jahr.
	 */
	@Test
	public void testInvalidShortYear() {
		Assert.assertFalse("...ung�ltiges Datumsformat vom Jahr", DateUtil.isDateValid("28.02.82"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ung�ltiges Datumsformat vom Monat.
	 */
	@Test
	public void testInvalidShortMonth() {
		Assert.assertFalse("...ung�ltiges Datumsformat vom Monat", DateUtil.isDateValid("28.2.1982"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ung�ltiges Datumsformat vom Tag.
	 */
	@Test
	public void testInvalidShortDay() {
		Assert.assertFalse("...ung�ltiges Datumsformat vom Tag", DateUtil.isDateValid("2.2.1982"));
	}
	/**
	 *  Test mit der {@link DateUtil#parseDate(String) parseDate}-Methode ob beim parsen eines g�ltigem Datumformates die {@link InvalidDateException} geworfen wird.
	 */
	@Test
	public void testParseValidDate() throws InvalidDateException {
		String testDate = "18.03.1982";
		LocalDate date = DateUtil.parseDate(testDate);
		Assert.assertEquals("Valides Datum \"" + testDate + "\" wurde erfolgreich konvertiert", LocalDate.class,
				date.getClass());
	}
	/**
	 *  Test mit der {@link DateUtil#parseDate(String) parseDate}-Methode ob beim parsen eines ung�ltigem Datumformates die {@link InvalidDateException} geworfen wird.
	 */
	@Test(expected = InvalidDateException.class)
	public void testParseInvalidDate() throws InvalidDateException {
		String date = "2.2.2017";
		try {
			DateUtil.parseDate(date);
		} catch (InvalidDateException e) {
			Assert.assertEquals("Invalid Exception Message",
					String.format("Fehler beim Konvertieren vom String \"%s\" in ein LocalDate-Format", date),
					e.getMessage());
			throw e;
		}
	}

}
