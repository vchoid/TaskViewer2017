package de.gokv.client.taskviewer;

import org.junit.Assert;
import org.junit.Test;

public class TestIdeen {

	@Test
	public void testValidHexaId() {
		Assert.assertTrue("...g�ltige Id und Hexadezimalzahl.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87F"));
	}

	@Test
	public void testInvalidHexaId() {
		Assert.assertFalse("...keine g�ltige Hexadezimalzahl.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87G"));
	}

	@Test
	public void testTooLongValidHexaId() {
		Assert.assertFalse("...g�ltige Hexadezimalzahl, aber zu lang.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFFF"));
	}

	@Test
	public void testTooLongInvalidHexaId() {
		Assert.assertFalse("...ung�ltige Hexadezimalzahl und zu lang.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFGG"));
	}

	@Test
	public void testTooShortValidHexaId() {
		Assert.assertFalse("...g�ltige Hexadezimalzahl, aber zu kurz.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B"));
	}

	@Test
	public void testTooShortInvalidHexaId() {
		Assert.assertFalse("...ung�ltige Hexadezimalzahl und zu kurz.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269G"));
	}

	@Test
	// genau 10 Zeichen, Erstes Zeichen ein Gro�buchstabe von A-Z, letzten 9
	// Stellen Zahlen von 0-9
	public void testValidKVNr() {
		Assert.assertTrue("...g�ltige KVNr.", KVNrUtil.isKVNrValid("Q101084865"));
	}

	@Test
	public void testInvalidKVNr_wrongChar() {
		Assert.assertFalse("...enth�lt ung�ltiges Zeichen.", KVNrUtil.isKVNrValid("?101084865"));
	}

	@Test
	public void testInvalidKVNr_twoChars() {
		Assert.assertFalse("...enth�lt mehr als ein Gro�buchstabe.", KVNrUtil.isKVNrValid("Q10104686Q"));
	}

	@Test
	public void testInvalidKVNr_NoChars() {
		Assert.assertFalse("...enh�lt keine Buchstaben.", KVNrUtil.isKVNrValid("1101045867"));
	}

	@Test
	public void testInvalidKVNr_withSpace() {
		Assert.assertFalse("...ung�ltiger Leerraum.", KVNrUtil.isKVNrValid("Q1010 45867"));
	}

	@Test
	public void testInvalidKVNr_char_capital() {
		Assert.assertFalse("...ung�ltige Buchstabengr��e.", KVNrUtil.isKVNrValid("q101084865"));
	}

	@Test
	public void testInvalidKVNr_wrongCharPos() {
		Assert.assertFalse("...ung�ltige Position des Buchstabes.", KVNrUtil.isKVNrValid("101008486Q"));
	}

	@Test
	public void testValidKVNr_lenght_tooShort() {
		Assert.assertFalse("...KVNr ist zu kurz.", KVNrUtil.isKVNrValid("Q1084865"));
	}

	@Test
	public void testValidKVNr_lenght_tooLong() {
		Assert.assertFalse("...KVNr ist zu lang.", KVNrUtil.isKVNrValid("Q108486555435435435435"));
	}

	@Test
	public void testReadEmptyLine() {

	}

	@Test
	public void testReadLineTooMuchCont() {

	}

	@Test
	public void testReadLineTooLittleCont() {

	}

	@Test
	public void testReadWrongHeader() {

	}