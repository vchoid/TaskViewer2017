package de.gokv.client.taskviewer;

import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.reader.HexaIdUtil;

public class HexaIdUtilTest {

	@Test
	public void testValidHexaId() {
		Assert.assertTrue("...gültige Id.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87F"));
	}
		
	@Test
	public void testInvalidHexaId() {
		Assert.assertFalse("...ungültige Id.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87G"));
	}
	
	@Test
	public void testTooLongValidHexaId() {
		Assert.assertFalse("...gültige Id, aber zu lang.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFFF"));
	}
	
	@Test
	public void testTooLongInvalidHexaId() {
		Assert.assertFalse("...ungültige Id und zu lang.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFGG"));
	}
	
	@Test
	public void testTooShortValidHexaId() {
		Assert.assertFalse("...gültige Id, aber zu kurz.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B"));
	}
	
	@Test
	public void testTooShortInvalidHexaId() {
		Assert.assertFalse("...ungültige Id und zu kurz.", HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269G"));
	}
	
	
	
	
}
