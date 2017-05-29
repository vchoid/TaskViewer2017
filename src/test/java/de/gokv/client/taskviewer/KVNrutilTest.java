package de.gokv.client.taskviewer;

import org.junit.Assert;
import org.junit.Test;

import de.gokv.client.reader.HexaIdUtil;
import de.gokv.client.reader.KVNrUtil;

public class KVNrutilTest {

	@Test
	// genau 10 Zeichen, Erstes Zeichen ein Gro�buchstabe von A-Z, letzten 9 Stellen Zahlen von 0-9
	public void testValidKVNr() {
		Assert.assertTrue("...g�ltige KVNr.", KVNrUtil.isKVNrValid("Q101084865"));
	}
	
	@Test
	public void testInvalidKVNr_char() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("?101084865"));
	}
	@Test
	public void testInvalidKVNr_twoChars() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("Q10104686Q"));
	}
	@Test
	public void testInvalidKVNr_NoChars() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("1101045867"));
	}
	@Test
	public void testInvalidKVNr_withSpace() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("Q1010 45867"));
	}
	@Test
	public void testInvalidKVNr_char_capital() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("q101084865"));
	}
	@Test
	public void testInvalidKVNr_wrongCharPos() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("101008486Q"));
	}
	@Test
	public void testInvalidKVNr_lenght_tooShort() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("Q1084865"));
	}
	@Test
	public void testInvalidKVNr_lenght_tooLong() {
		Assert.assertFalse("...ung�ltige KVNr.", KVNrUtil.isKVNrValid("Q108486555435435435435"));
	}
	
	
}
