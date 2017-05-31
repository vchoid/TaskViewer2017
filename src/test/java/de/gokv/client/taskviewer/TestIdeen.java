package de.gokv.client.taskviewer;

import org.junit.Assert;
import org.junit.Test;

public class TestIdeen {

	@Test
	public void testValidHexaId() {
		Assert.assertTrue("...gültige Id und Hexadezimalzahl.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87F"));
	}

	@Test
	public void testInvalidHexaId() {
		Assert.assertFalse("...keine gültige Hexadezimalzahl.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87G"));
	}

	@Test
	public void testTooLongValidHexaId() {
		Assert.assertFalse("...gültige Hexadezimalzahl, aber zu lang.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFFF"));
	}

	@Test
	public void testTooLongInvalidHexaId() {
		Assert.assertFalse("...ungültige Hexadezimalzahl und zu lang.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B87FFGG"));
	}

	@Test
	public void testTooShortValidHexaId() {
		Assert.assertFalse("...gültige Hexadezimalzahl, aber zu kurz.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269B"));
	}

	@Test
	public void testTooShortInvalidHexaId() {
		Assert.assertFalse("...ungültige Hexadezimalzahl und zu kurz.",
				HexaIdUtil.isIdValid("405750F0395F11E7BED7F726E269G"));
	}

	@Test
	// genau 10 Zeichen, Erstes Zeichen ein Großbuchstabe von A-Z, letzten 9
	// Stellen Zahlen von 0-9
	public void testValidKVNr() {
		Assert.assertTrue("...gültige KVNr.", KVNrUtil.isKVNrValid("Q101084865"));
	}

	@Test
	public void testInvalidKVNr_wrongChar() {
		Assert.assertFalse("...enthält ungültiges Zeichen.", KVNrUtil.isKVNrValid("?101084865"));
	}

	@Test
	public void testInvalidKVNr_twoChars() {
		Assert.assertFalse("...enthält mehr als ein Großbuchstabe.", KVNrUtil.isKVNrValid("Q10104686Q"));
	}

	@Test
	public void testInvalidKVNr_NoChars() {
		Assert.assertFalse("...enhält keine Buchstaben.", KVNrUtil.isKVNrValid("1101045867"));
	}

	@Test
	public void testInvalidKVNr_withSpace() {
		Assert.assertFalse("...ungültiger Leerraum.", KVNrUtil.isKVNrValid("Q1010 45867"));
	}

	@Test
	public void testInvalidKVNr_char_capital() {
		Assert.assertFalse("...ungültige Buchstabengröße.", KVNrUtil.isKVNrValid("q101084865"));
	}

	@Test
	public void testInvalidKVNr_wrongCharPos() {
		Assert.assertFalse("...ungültige Position des Buchstabes.", KVNrUtil.isKVNrValid("101008486Q"));
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
