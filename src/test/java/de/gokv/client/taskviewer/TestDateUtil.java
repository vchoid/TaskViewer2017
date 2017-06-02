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
 * <li><b>{@link #testValidDate()}</b>: Überprüft, ob bei einem gültigem Datum, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>true</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidDateWithChar()}</b>: Überprüft, ob bei einem ungültigem Datum, mit einem ungültigem Zeichen, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidDateSchaltjahr()}</b>: Überprüft, ob bei einem ungültigem Schaltjahr, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testValidDateSchaltjahr()}</b>:  Überprüft, ob bei einem gültigem Schaltjahr, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>true</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidDateMonth()}</b>: Überprüft, ob bei einem ungültigem Monat, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidShortYear()}</b>: Überprüft, ob bei einem ungültigem Datum, mit zu kurzer Jahreszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidShortMonth()}</b>: Überprüft, ob bei einem ungültigem Datum, mit zu kurzer Monatszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testInvalidShortDay()}</b>: Überprüft, ob bei einem ungültigem Datum, mit zu kurzer Tageszahl, die {@link DateUtil#isDateValid(String) isDatevalid}-Methode der Wert <i>false</i> zurückgegeben wird.</li>
 * <li><b>{@link #testParseValidDate()}</b>: Überprüft, ob bei einem gültigem Datum, die {@link InvalidDateException} geworfen wird</li>
 * <li><b>{@link #testParseInvalidDate()}</b>: Überprüft, ob bei einem ungültigem Datum, die {@link InvalidDateException} geworfen wird</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 *
 */

public class TestDateUtil {

	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein gültiges Datum.
	 */
	@Test
	public void testValidDate() {
		Assert.assertTrue("...gültiges Datum", DateUtil.isDateValid("18.03.2015"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ungültiges Datum, weil es ein ungültiges Zeichen enthält.
	 */
	@Test
	public void testInvalidDateWithChar() {
		Assert.assertFalse("...ungültiges Datum, weil es ein ungültiges Zeichen enthält.",
				DateUtil.isDateValid("O9.02.20l7"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ungültiges Datum, weil es kein Schaltjahr ist.
	 */
	@Test
	public void testInvalidDateSchaltjahr() {
		Assert.assertFalse("...ungültiges Datum, weil es kein Schaltjahr ist.", DateUtil.isDateValid("29.02.2017"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein gültiges Datum im Schaltjahr.
	 */
	@Test
	public void testValidDateSchaltjahr() {
		Assert.assertTrue("...gültiges Datum im Schaltjahr.", DateUtil.isDateValid("29.02.2016"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ein ungültigen Monat.
	 */
	@Test
	public void testInvalidDateMonth() {
		Assert.assertFalse("...ungültiger Monat", DateUtil.isDateValid("29.13.2017"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ungültiges Datumsformat vom Jahr.
	 */
	@Test
	public void testInvalidShortYear() {
		Assert.assertFalse("...ungültiges Datumsformat vom Jahr", DateUtil.isDateValid("28.02.82"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ungültiges Datumsformat vom Monat.
	 */
	@Test
	public void testInvalidShortMonth() {
		Assert.assertFalse("...ungültiges Datumsformat vom Monat", DateUtil.isDateValid("28.2.1982"));
	}
	/**
	 *  Test mit der {@link DateUtil#isDateValid(String) isDatevalid}-Methode auf ungültiges Datumsformat vom Tag.
	 */
	@Test
	public void testInvalidShortDay() {
		Assert.assertFalse("...ungültiges Datumsformat vom Tag", DateUtil.isDateValid("2.2.1982"));
	}
	/**
	 *  Test mit der {@link DateUtil#parseDate(String) parseDate}-Methode ob beim parsen eines gültigem Datumformates die {@link InvalidDateException} geworfen wird.
	 */
	@Test
	public void testParseValidDate() throws InvalidDateException {
		String testDate = "18.03.1982";
		LocalDate date = DateUtil.parseDate(testDate);
		Assert.assertEquals("Valides Datum \"" + testDate + "\" wurde erfolgreich konvertiert", LocalDate.class,
				date.getClass());
	}
	/**
	 *  Test mit der {@link DateUtil#parseDate(String) parseDate}-Methode ob beim parsen eines ungültigem Datumformates die {@link InvalidDateException} geworfen wird.
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
