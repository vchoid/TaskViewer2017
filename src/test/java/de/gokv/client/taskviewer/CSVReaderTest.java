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

/**
 * Testen der {@link CSVReader}-Klasse.
 * 
 * <p><b>Methoden:</b>
 * <ul>
 * <li><b>{@link #getFile()}</b>: Startet den CSVreader.</li>
 * <li><b>{@link #getValidFile()}</b>: Gibt den Dateipfad der validen CSV an.</li>
 * <li><b>{@link #getInvalidTaskIdFile()}</b>: Gibt den Dateipfad der invaliden CSV an,
 * die ungültige Einträge in der TaskId-Spalte haben.</li>
 * <li><b>{@link #getInvalidKVNrFile()}</b>: Gibt den Dateipfad der invaliden CSV an,
 * die ungültige Einträge in der KvNr-Spalte haben.</li>
 * <li><b>{@link #getNoNumbAllowedFile()}</b>: Gibt den Dateipfad der invaliden CSV an,
 * die ungültige Zahlen in den Spalten Name, Vorname, Titel, vsWort und zsWort haben.</li>
 * </ul>
 * </p>
 * 
 * <p><b>Tests:</b>
 * <ul>
 * <li><b>{@link #testCSVReaderWrongFileException()}</b>: Legt ein falschen Pfad oder gibt einen falschen Dateinamen  als String an.
 *  Erstellt ein neues Objekt von der Klasse {@link CSVReader}.
 *  Versucht in einem try/catch-Block die CSV-Datei oder den angebenen Pfad zu lesen.
 *  Überprüft, ob die geworfene {@link ClientException} eine vom Typ {@link FileNotFoundException} ist.</li>
 * <li><b>{@link #testCSVReadContent()}</b>: Ließt zuerst die valide Datei mit der {@link #getValidFile}-Methode.
 * Holt sich eine Zeile aus der CSV mit der {@link CSVReader#getValidEntries() getValidEntries}-Methode
 * und überprüft mit den Getter-Methoden für die einzelnen Spalte, der {@link Task}-Klasse,
 * ob die erwarteten Inhalte mit den gelesen Daten aus der CSV übereinstimmen.</li>
 * <li><b>{@link #testInvalidTaskID()}</b>: Ließt zuerst die invalide Datei mit der {@link #getInvalidTaskIdFile()}-Methode.
 * Überprüft, ob fehlerhafte TaskId-Einträge, eine {@link InvalidCSVRecordException} wirft.</li>
 * <li><b>{@link #testInvalidKVNr()}</b>:Ließt zuerst die invalide Datei mit der {@link #getInvalidKVNrFile()}-Methode.
 * Überprüft, ob fehlerhafte KVNr-Einträge, eine {@link InvalidCSVRecordException} wirft.</li>
 * <li><b>{@link #testInvalidNumbInText()}</b>: Ließt zuerst die invalide Datei mit der {@link #getNoNumbAllowedFile()}-Methode.
 * Überprüft, ob sich Zahlen in den Spalten Namen, Vornamen, zsWort, vsWort, Titel befinden
 * und wenn ja ob eine {@link InvalidCSVRecordException} geworfen wird.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Christoph Kiank
 * @version 0.9.8
 *
 */
public class CSVReaderTest {

	private CSVReader reader;
	private String filePath;
	private Task oneTask;

	/**
	 * Startet den CSVreader.
	 * @param filePath Dateipfad als String.
	 */
	private void getFile(String filePath) {
		reader = new CSVReader(System.getProperty("user.dir") + filePath);
		reader.readCSVFile();
	}
	/**
	 * Gibt den Dateipfad der validen CSV an.
	 */
	private void getValidFile() {
		filePath = "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * die ungültige Einträge in der TaskId-Spalte haben.
	 */
	private void getInvalidTaskIdFile() {
		filePath = "/src/test/resources/invalidTaskId_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * die ungültige Einträge in der KvNr-Spalte haben.
	 */
	private void getInvalidKVNrFile() {
		filePath = "/src/test/resources/invalidKVNr_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * die ungültige Zahlen in den Spalten Name, Vorname, Titel, vsWort und zsWort haben.
	 */
	private void getNoNumbAllowedFile() {
		filePath = "/src/test/resources/NoNumbAllowed_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	/**
	 *  Legt ein falschen Pfad oder gibt einen falschen Dateinamen  als String an.
	 *  Erstellt ein neues Objekt von der Klasse {@link CSVReader}.
	 *  Versucht in einem try/catch-Block die CSV-Datei oder den angebenen Pfad zu lesen.
	 *  Überprüft, ob die geworfene {@link ClientException} eine vom Typ {@link FileNotFoundException} ist.
	 */
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

	/**
	 *  Ließt zuerst die valide Datei mit der {@link #getValidFile}-Methode.
	 *  Holt sich eine Zeile aus der CSV mit der {@link CSVReader#getValidEntries() getValidEntries}-Methode
	 *  und überprüft mit den Getter-Methoden für die einzelnen Spalte, der {@link Task}-Klasse,
	 *  ob die erwarteten Inhalte mit den gelesen Daten aus der CSV übereinstimmen.
	 *  @see Task
	 *  @see CSVReader
	 */
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

//	/**
//	 * Ließt zuerst die invalide Datei mit der {@link #getInvalidTaskIdFile()}-Methode.
//	 * Überprüft, ob fehlerhafte TaskId-Einträge, eine {@link InvalidCSVRecordException} wirft.
//	 * @throws InvalidCSVRecordException
//	 */
//	@Test(expected = InvalidCSVRecordException.class)
//	public void testInvalidTaskID2() throws InvalidCSVRecordException {
//		getInvalidTaskIdFile();
//		// ungültige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87G
//		// ungültige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87?
//		// ungültige Hexadezimalzahl und zu lang =>	405750F0395F11E7BED7F726E269B87FFGG
//		// ungültige Hexadezimalzahl und zu kurz =>	405750F0395F11E7BED7F726E269G
//		// gültige Hexadezimalzahl, aber zu kurz =>	405750F0395F11E7BED7F726E269B
//		// gültige Hexadezimalzahl, aber zu lang =>	405750F0395F11E7BED7F726E269B87FFFF
//
//		// Record mit Invalider TASKID
//		try {
//			CSVRecord zeile0 = reader.getInvalidEntries().get(0);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269B87G",
//					CSVReader.getValue(zeile0, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//			CSVRecord zeile1 = reader.getInvalidEntries().get(1);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269B87?",
//					CSVReader.getValue(zeile1, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//			CSVRecord zeile2 = reader.getInvalidEntries().get(2);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269B87FFGG",
//					CSVReader.getValue(zeile2, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//			CSVRecord zeile3 = reader.getInvalidEntries().get(3);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269G",
//					CSVReader.getValue(zeile3, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//			CSVRecord zeile4 = reader.getInvalidEntries().get(4);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269B",
//					CSVReader.getValue(zeile4, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//			CSVRecord zeile5 = reader.getInvalidEntries().get(5);
//			Assert.assertEquals("405750F0395F11E7BED7F726E269B87FFFF",
//					CSVReader.getValue(zeile5, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID));
//
//		} catch (InvalidCSVRecordException e) {
//			Assert.assertTrue(e instanceof InvalidCSVRecordException);
//			throw e;
//
//		}
//
//	}

	/**
	 * Ließt zuerst die invalide Datei mit der {@link #getInvalidTaskIdFile()}-Methode.
	 * Überprüft, ob fehlerhafte TaskId-Einträge, eine {@link InvalidCSVRecordException} wirft.
	 * 
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidTaskID() throws InvalidCSVRecordException {
		getInvalidTaskIdFile();
		// ungültige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87G
		// ungültige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87?
		// ungültige Hexadezimalzahl und zu lang =>	405750F0395F11E7BED7F726E269B87FFGG
		// ungültige Hexadezimalzahl und zu kurz =>	405750F0395F11E7BED7F726E269G
		// gültige Hexadezimalzahl, aber zu kurz =>	405750F0395F11E7BED7F726E269B
		// gültige Hexadezimalzahl, aber zu lang =>	405750F0395F11E7BED7F726E269B87FFFF
		for (int i = 0; i < reader.getInvalidEntries().size(); i++) {
			// Record mit Invalider TASKID
			CSVRecord csvRecord = reader.getInvalidEntries().get(i);
			try {
				CSVReader.getValue(csvRecord, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID);
			} catch (InvalidCSVRecordException e) {
				Assert.assertTrue(e instanceof InvalidCSVRecordException);
				throw e;

			}
		}
	}

	/**
	 * Ließt zuerst die invalide Datei mit der {@link #getInvalidKVNrFile()}-Methode.
	 * Überprüft, ob fehlerhafte KVNr-Einträge, eine {@link InvalidCSVRecordException} wirft.
	 * 
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidKVNr() throws InvalidCSVRecordException {
		getInvalidKVNrFile();
		// enthält ungültiges Zeichen => ?101084865
		// enthält mehr als ein Großbuchstabe => Q10104686Q
		// enhält keine Buchstaben => 1101045867
		// ungültiger Leerraum => Q1010 45867
		// ungültige Buchstabengröße => q101084865
		// ungültige Position des Buchstabes => 101008486Q
		// KVNr ist zu kurz => Q1084865
		// KVNr ist zu lang => Q108486555435435435435
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

	/**
	 * 	Ließt zuerst die invalide Datei mit der {@link #getNoNumbAllowedFile()}-Methode.
	 *  Überprüft, ob sich Zahlen in den Spalten Namen, Vornamen, zsWort, vsWort, Titel befinden
	 *  und wenn ja ob eine {@link InvalidCSVRecordException} geworfen wird.
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidNumbInText() throws InvalidCSVRecordException {
		getNoNumbAllowedFile();

		// Record mit Invalider Name
		CSVRecord zeile0 = reader.getInvalidEntries().get(0);
		try {
			// Beispiel: KRUPPI0
			CSVReader.getValue(zeile0, CSVReader.COL_MITGLIED_NAME, true, Task.PATTERN_NONUMB);
		} catch (InvalidCSVRecordException e) {
			throw e;
		}
		// Record mit Invalider Vorname
		CSVRecord zeile1 = reader.getInvalidEntries().get(1);
		try {
			// Beispiel: He1mut-Peter
			CSVReader.getValue(zeile1, CSVReader.COL_MITGLIED_VORNAME, true, Task.PATTERN_NONUMB);
		} catch (InvalidCSVRecordException e) {
			throw e;
		}
		// Record mit Invalider Titel
		CSVRecord zeile2 = reader.getInvalidEntries().get(2);
		try {
			// Beispiel: Dr.2
			CSVReader.getValue(zeile2, CSVReader.COL_MITGLIED_TITEL, false, Task.PATTERN_NONUMB);
		} catch (InvalidCSVRecordException e) {
			throw e;
		}
		// Record mit Invalider vsWort
		CSVRecord zeile3 = reader.getInvalidEntries().get(3);
		try {
			// Beispiel: vs3
			CSVReader.getValue(zeile3, CSVReader.COL_MITGLIED_VSWORT, false, Task.PATTERN_NONUMB);
		} catch (InvalidCSVRecordException e) {
			throw e;
		}

		// Record mit Invalider zsWort
		CSVRecord zeile4 = reader.getInvalidEntries().get(4);
		try {
			// Beispiel: zs4
			CSVReader.getValue(zeile4, CSVReader.COL_MITGLIED_ZSWORT, false, Task.PATTERN_NONUMB);
		} catch (InvalidCSVRecordException e) {
			throw e;
		}
	}
	//TODO prüfen ob Pflichtfelder erkannt werden und gefüllt sind
	

}
