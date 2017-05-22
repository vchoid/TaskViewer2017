package de.gokv.client.taskviewer;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.reader.DateUtil;
import de.gokv.client.reader.InvalidDateException;

public class DateUtilTest {


	@Test
	public void testValidDate() {
		Assert.assertTrue("..", DateUtil.isValidDate("18.03.2015"));
	}

	@Test
	public void testInvalidDateSchaltjahr(){
		Assert.assertFalse("",DateUtil.isValidDate("29.02.2017"));
	}
	
	@Test
	public void testInvalidDateMonth(){
		Assert.assertFalse("",DateUtil.isValidDate("29.13.2017"));
	}
	
	@Test
	public void testValidDateSchaltjahr(){
		Assert.assertTrue("",DateUtil.isValidDate("29.02.2016"));
	}
	
	@Test
	public void testInvalidShortDate(){
		Assert.assertFalse("",DateUtil.isValidDate("28.02.82"));
	}
	
	@Test
	public void testValidShortDate(){
		Assert.assertTrue("",DateUtil.isValidDate("28.02.0082"));
	}
	
	@Test
	public void testParseValidDate() throws InvalidDateException{
		Date date = DateUtil.parseDate("18.03.1982");
		Assert.assertEquals("Datum wurde erfolgreich formatiert", Date.class, date.getClass());
	}
	
	@Test(expected=InvalidDateException.class)
	public void testParseInvalidDate() throws InvalidDateException {
		try {
			Date date = DateUtil.parseDate("29.02.2017");
		} catch (InvalidDateException e) {
			System.out.println("Fehler beim umwandeln des Formats in ein Datumsformat.");
			throw e;
		}
	}
	
}
