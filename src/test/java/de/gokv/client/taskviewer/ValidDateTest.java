package de.gokv.client.taskviewer;

import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.reader.DateUtil;

public class ValidDateTest {


	@Test
	public void testValidDate() {
		Assert.assertTrue("", DateUtil.isValidDate("18.03.2015"));
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

	
}
