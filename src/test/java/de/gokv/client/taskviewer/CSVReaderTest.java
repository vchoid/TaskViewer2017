package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.naming.directory.SchemaViolationException;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.gokv.client.taskviewer.exceptions.ClientException;
import de.gokv.client.taskviewer.exceptions.InvalidCSVRecordException;

public class CSVReaderTest {

	private CSVReader reader;
	private String filePath;
	private Task oneTask;

	private void getFile(String filePath) {
		reader = new CSVReader(System.getProperty("user.dir") + filePath);
		reader.readCSVFile();
	}

	private void getValidFile() {
		filePath = "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	private void getInvalidKVNrFile() {
		filePath = "/src/test/resources/invalidKVNr_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	private void getInvalidTaskIdFile() {
		filePath = "/src/test/resources/invalidTaskId_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	private void getInvalidFile() {
		filePath = "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	// �berpr�ft ob die Datei vorhanden ist und wenn nicht dann ob die geworfene
	// Exception eine vom Typ FileNotFoundException ist.
	@Test(expected = ClientException.class)
	public void testCSVReaderWrongFileException() {
		String wrongfile_wrongPath = System.getProperty("user.dir")
				+ "/falscher/test/Pfad/falsche_famv_direct_input_monitoring_20170515131131.csv";
		reader = new CSVReader(wrongfile_wrongPath);
		try {
			reader.readCSVFile();
		} catch (ClientException e) {
			Assert.assertEquals("Datei " + wrongfile_wrongPath + " wurde nicht gefunden", e.getMessage());
			Assert.assertTrue(e.getCause() instanceof FileNotFoundException);
			throw e;
		}
	}

	// �berpr�ft ob die erwarteten Inhalte gelesen werden k�nnen.
	@Test
	public void testCSVReadContent() {
		getValidFile();
		oneTask = reader.getValidEntries().get(0);
		Assert.assertEquals(4, reader.getValidEntries().size());
		Assert.assertEquals("405750F0395F11E7BED7F726E269B87F", oneTask.getTask_id());
		Assert.assertEquals("famv_bestand", oneTask.getTasktype());
		Assert.assertEquals("2017-05-15", oneTask.getOrdered_date().toString());
		Assert.assertEquals("Q101084865", oneTask.getKvnr());
		Assert.assertEquals("KRUPPIO", oneTask.getName());
		Assert.assertEquals("Ulrich Moritz", oneTask.getVorname());
		Assert.assertEquals("", oneTask.getZsWort());
		Assert.assertEquals("", oneTask.getVsWort());
		Assert.assertEquals("1982-03-06", oneTask.getGeb_dat().toString());

	}

	// �berpr�ft, ob eine fehlerhafte TaskId eine Exception wirft.
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidTaskID() throws InvalidCSVRecordException {
		getInvalidTaskIdFile();
		// ung�ltige Hexadezimalzahl 				=> 405750F0395F11E7BED7F726E269B87G
		// ung�ltige Hexadezimalzahl 				=> 405750F0395F11E7BED7F726E269B87?
		// ung�ltige Hexadezimalzahl und zu lang 	=> 405750F0395F11E7BED7F726E269B87FFGG
		// ung�ltige Hexadezimalzahl und zu kurz	=> 405750F0395F11E7BED7F726E269G
		// g�ltige Hexadezimalzahl, aber zu kurz	=> 405750F0395F11E7BED7F726E269B
		// g�ltige Hexadezimalzahl, aber zu lang	=> 405750F0395F11E7BED7F726E269B87FFFF
		for (int i = 0; i < reader.getInvalidEntries().size(); i++) {
			// Record mit Invalider TASKID
			CSVRecord csvRecord = reader.getInvalidEntries().get(i);
			try {
				CSVReader.getValue(csvRecord, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID);
			} catch (InvalidCSVRecordException e) {
				throw e;

			} 
		}
	}

	// �berpr�ft, ob eine fehlerhafte KVNr eine Exception wirft.
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidKVNr() throws InvalidCSVRecordException {
		getInvalidKVNrFile();
		// enth�lt ung�ltiges Zeichen			=> ?101084865
		// enth�lt mehr als ein Gro�buchstabe	=> Q10104686Q
		// enh�lt keine Buchstaben				=> 1101045867
		// ung�ltiger Leerraum					=> Q1010 45867
		// ung�ltige Buchstabengr��e			=> q101084865
		// ung�ltige Position des Buchstabes	=> 101008486Q
		// KVNr ist zu kurz						=> Q1084865
		// KVNr ist zu lang						=> Q108486555435435435435
		for (int j = 0; j < reader.getInvalidEntries().size(); j++) {
			// Record mit Invalider KVNr
			CSVRecord csvRecord = reader.getInvalidEntries().get(j);
			try {
				CSVReader.getValue(csvRecord, CSVReader.COL_MITGLIED_KVNR, true, Task.PATTERN_KVNR);
			} catch (InvalidCSVRecordException e) {
				throw e;
			} 
		}
	}

}
