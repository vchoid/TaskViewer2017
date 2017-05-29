package de.gokv.client.taskviewer;

import java.time.LocalDate;


import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.reader.DateUtil;
import de.gokv.client.reader.InvalidDateException;

public class DateUtilTest {

	@Test
	public void testValidDate() {
		Assert.assertTrue("...gültiges Datum", DateUtil.isDateValid("18.03.2015"));
	}

	@Test
	public void testInvalidDateSchaltjahr() {
		Assert.assertFalse("...ungültiges Datum, weil es kein Schaltjahr ist.", DateUtil.isDateValid("29.02.2017"));
	}
	@Test
	public void testValidDateSchaltjahr() {
		Assert.assertTrue("...gültiges Datum im Schaltjahr.", DateUtil.isDateValid("29.02.2016"));
	}

	@Test
	public void testInvalidDateMonth() {
		Assert.assertFalse("...ungültiger Monat", DateUtil.isDateValid("29.13.2017"));
	}


	@Test
	public void testInvalidShortDate() {
		Assert.assertFalse("...ungültiges Datumsformat", DateUtil.isDateValid("28.02.82"));
	}

		@Test
	public void testParseValidDate() throws InvalidDateException {
		String testDate = "18.03.1982";
		LocalDate date = DateUtil.parseDate(testDate);
		Assert.assertEquals("Valides Datum \"" + testDate + "\" wurde erfolgreich konvertiert", LocalDate.class, date.getClass());
	}

	@Test(expected = InvalidDateException.class)
	public void testParseInvalidDate() throws InvalidDateException {
		String date = "2.2.2017";
		try {
			DateUtil.parseDate(date);
		} catch (InvalidDateException e) {
			Assert.assertEquals("Invalid Exception Message", String.format("Fehler beim Konvertieren vom String \"%s\" in ein LocalDate-Format", date), e.getMessage());
			throw e;
		}
	}

}
