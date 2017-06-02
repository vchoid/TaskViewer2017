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
import de.gokv.client.taskviewer.exceptions.InvalidDateException;

/**
 * Testen der {@link CSVReader}-Klasse.
 * 
 * <p><b>Methoden:</b>
 * <ul>
 * <li><b>{@link #getFile()}</b>: Startet den {@link CSVReader}.</li>
 * <li><b>{@link #getValidFile()}</b>: Gibt den Dateipfad der CSV an, 
 * in der alle Eintr�ge korrekt sind.</li>
 * <li><b>{@link #getInvalidTaskIdFile()}</b>: Gibt den Dateipfad der CSV-Datei an,
 * die ung�ltige Eintr�ge in der TaskId-Spalte haben.</li>
 * <li><b>{@link #getInvalidKVNrFile()}</b>: Gibt den Dateipfad der CSV-Datei an,
 * die ung�ltige Eintr�ge in der KvNr-Spalte haben.</li>
 * <li><b>{@link #getNoNumbAllowedFile()}</b>: Gibt den Dateipfad der CSV-Datei an,
 * die ung�ltige Zahlen in den Spalten Name, Vorname, Titel, vsWort und zsWort haben.</li>
 * <li><b>{@link #getInvalidReqFieldsFile()}</b>: Gibt den Dateipfad der CSV-Datei an,
 * in der die Pflichtfelder nicht gef�llt sind.</li>
 * </ul>
 * </p>
 * 
 * <p><b>Tests:</b>
 * <ul>
 * <li><b>{@link #testCSVReaderWrongFileException()}</b>: Legt ein falschen Pfad oder gibt einen falschen Dateinamen als String an.
 *  Erstellt ein neues Objekt von der Klasse {@link CSVReader}.
 *  Versucht in einem try/catch-Block die CSV-Datei oder den angebenen Pfad zu lesen.
 *  �berpr�ft, ob die geworfene {@link ClientException} eine vom Typ {@link FileNotFoundException} ist.</li>
 * <li><b>{@link #testCSVReadContent()}</b>: Lie�t zuerst die CSVDatei,
 * mit korrekten Eintr�gen in jeder Spalte, mit der {@link #getValidFile}-Methode.
 * Holt sich eine Zeile aus der CSV mit der {@link CSVReader#getValidEntries() getValidEntries}-Methode
 * und �berpr�ft mit den Getter-Methoden f�r die einzelnen Spalte, der {@link Task}-Klasse,
 * ob die erwarteten Werte mit den geholten Werten aus der CSV �bereinstimmen.</li>
 * <li><b>{@link #testInvalidTaskID()}</b>: Lie�t zuerst die Datei,
 * mit fehlerhaften Eintr�gen in der TaskId-Spalte, mit der {@link #getInvalidTaskIdFile()}-Methode.
 * �berpr�ft, ob fehlerhafte TaskId-Eintr�ge, eine {@link InvalidCSVRecordException} wirft.</li>
 * <li><b>{@link #testInvalidKVNr()}</b>:Lie�t zuerst die Datei
 * mit den fehlerhaften Eintr�gen in der KvNr-Spalte mit der {@link #getInvalidKVNrFile()}-Methode.
 * �berpr�ft, ob fehlerhafte KVNr-Eintr�ge, eine {@link InvalidCSVRecordException} wirft.</li>
 * <li><b>{@link #testInvalidNumbInText()}</b>: Lie�t zuerst die Datei
 * mit den fehlerhaften Eintr�gen in den Spalten Namen, Vornamen, zsWort, vsWort, Titel mit der {@link #getNoNumbAllowedFile()}-Methode.
 * �berpr�ft, ob sich Zahlen in den Spalten befinden und wenn ja ob eine {@link InvalidCSVRecordException} geworfen wird.</li>
 * <li><b>{@link #testInvalidRequiereFields()}</b>: </li>
 * <li><b>{@link #testInvalidNonRequiereFields()}</b>: </li>
 * <li><b>{@link #testEmptyRow}</b>: </li>
 * </ul>
 * </p>
 * 
 * 
 * @author Christoph Kiank
 * @version 0.9.9
 *
 */
public class TestCSVReader {

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
	 * die ung�ltige Eintr�ge in der TaskId-Spalte haben.
	 */
	private void getInvalidTaskIdFile() {
		filePath = "/src/test/resources/invalidTaskId_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * die ung�ltige Eintr�ge in der KvNr-Spalte haben.
	 */
	private void getInvalidKVNrFile() {
		filePath = "/src/test/resources/invalidKVNr_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * die ung�ltige Zahlen in den Spalten Name, Vorname, Titel, vsWort und zsWort haben.
	 */
	private void getNoNumbAllowedFile() {
		filePath = "/src/test/resources/NoNumbAllowed_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	/**
	 * Gibt den Dateipfad der invaliden CSV an,
	 * in der die Pflichtfelder nicht gef�llt sind.
	 */
	private void getInvalidReqFieldsFile() {
		filePath = "/src/test/resources/invalidReqFields_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	/**
	 *  Legt ein falschen Pfad oder gibt einen falschen Dateinamen  als String an.
	 *  Erstellt ein neues Objekt von der Klasse {@link CSVReader}.
	 *  Versucht in einem try/catch-Block die CSV-Datei oder den angebenen Pfad zu lesen.
	 *  �berpr�ft, ob die geworfene {@link ClientException} eine vom Typ {@link FileNotFoundException} ist.
	 */
	@Test(expected = ClientException.class)
	public void testCSVReaderWrongFileException() {
		String wrongfile_wrongPath = System.getProperty("user.dir")
				+ "/falscher/test/Pfad/falsche_famv_direct_input_monitoring_20170515131131.csv";
		reader = new CSVReader(wrongfile_wrongPath);
		try {
			reader.readCSVFile();
		} catch (ClientException e) {
			Assert.assertEquals("Datei " + wrongfile_wrongPath + " wurde nicht gefunden" + "(ClientException)", e.getMessage());
			Assert.assertTrue(e.getCause() instanceof FileNotFoundException);
			throw e;
		}
	}

	/**
	 *  Lie�t zuerst die valide Datei mit der {@link #getValidFile}-Methode.
	 *  Holt sich eine Zeile aus der CSV mit der {@link CSVReader#getValidEntries() getValidEntries}-Methode
	 *  und �berpr�ft mit den Getter-Methoden f�r die einzelnen Spalte, der {@link Task}-Klasse,
	 *  ob die erwarteten Inhalte mit den gelesen Daten aus der CSV �bereinstimmen.
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

	
	/**
	 * Lie�t zuerst die fehlerhafte Datei mit der {@link #getInvalidTaskIdFile()}-Methode.
	 * �berpr�ft, ob fehlerhafte TaskId-Eintr�ge, eine {@link InvalidCSVRecordException} wirft.
	 * 
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidTaskID() throws InvalidCSVRecordException {
		getInvalidTaskIdFile();
		// ung�ltige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87G
		// ung�ltige Hexadezimalzahl => 			405750F0395F11E7BED7F726E269B87?
		// ung�ltige Hexadezimalzahl und zu lang =>	405750F0395F11E7BED7F726E269B87FFGG
		// ung�ltige Hexadezimalzahl und zu kurz =>	405750F0395F11E7BED7F726E269G
		// g�ltige Hexadezimalzahl, aber zu kurz =>	405750F0395F11E7BED7F726E269B
		// g�ltige Hexadezimalzahl, aber zu lang =>	405750F0395F11E7BED7F726E269B87FFFF
		for (int i = 0; i < reader.getInvalidEntries().size(); i++) {
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
	 * Lie�t zuerst die fehlerhafte Datei mit der {@link #getInvalidKVNrFile()}-Methode.
	 * �berpr�ft, ob fehlerhafte KVNr-Eintr�ge, eine {@link InvalidCSVRecordException} wirft.
	 * 
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidKVNr() throws InvalidCSVRecordException {
		getInvalidKVNrFile();
		// enth�lt ung�ltiges Zeichen => 			?101084865
		// enth�lt mehr als ein Gro�buchstabe => 	Q10104686Q
		// enh�lt keine Buchstaben => 				1101045867
		// ung�ltiger Leerraum => 					Q1010 45867
		// ung�ltige Buchstabengr��e => 			q101084865
		// ung�ltige Position des Buchstabes => 	101008486Q
		// KVNr ist zu kurz => 						Q1084865
		// KVNr ist zu lang => 						Q108486555435435435435
		for (int j = 0; j < reader.getInvalidEntries().size(); j++) {
			CSVRecord csvRecord = reader.getInvalidEntries().get(j);
			try {
				CSVReader.getValue(csvRecord, CSVReader.COL_MITGLIED_KVNR, true, Task.PATTERN_KVNR);
			} catch (InvalidCSVRecordException e) {
				throw e;
			}
		}
	}

	/**
	 * 	Lie�t zuerst die fehlerhafte Datei mit der {@link #getNoNumbAllowedFile()}-Methode.
	 *  �berpr�ft, ob sich Zahlen in den Spalten Namen, Vornamen, zsWort, vsWort, Titel befinden
	 *  und wenn ja ob eine {@link InvalidCSVRecordException} geworfen wird.
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidNumbInText() throws InvalidCSVRecordException {
		getNoNumbAllowedFile();
		CSVRecord row;
		
		for (int i = 0; i < reader.getInvalidEntries().size(); i++) {
			 row = reader.getInvalidEntries().get(i);
			try {
				// Beispiel: KRUPPI0
				CSVReader.getValue(row, CSVReader.COL_MITGLIED_NAME, true, Task.PATTERN_NONUMB);
				// Beispiel: He1mut-Peter
				CSVReader.getValue(row, CSVReader.COL_MITGLIED_VORNAME, true, Task.PATTERN_NONUMB);
				// Beispiel: Dr.2
				CSVReader.getValue(row, CSVReader.COL_MITGLIED_TITEL, false, Task.PATTERN_NONUMB);
				// Beispiel: vs3
				CSVReader.getValue(row, CSVReader.COL_MITGLIED_VSWORT, false, Task.PATTERN_NONUMB);
				// Beispiel: zs4
				CSVReader.getValue(row, CSVReader.COL_MITGLIED_ZSWORT, false, Task.PATTERN_NONUMB);
			} catch (InvalidCSVRecordException e) {
				throw e;
			} 
		}
		
	}
	//TODO pr�fen ob auf true gesetze Pflichtfelder erkannt werden und wenn nicht ob sie eine Exception werfen.
	
	/**
	 * Lie�t zuerst die fehlerhafte Datei mit der {@link #getInvalidReqFieldsFile()}-Methode.
	 * Legt eine CSVRecord Variable an.
	 * Holt sich in einer For-Schleife jede Zeile und pr�ft bei jedem Durchgang,
	 * ob auf true gesetzte Spalten(=Pflichtfelder) eine <u><b>erwartete {@link InvalidCSVRecordException}</b></u> wirft,
	 * wenn die abgefragte Spalte leer ist.
	 * @throws InvalidCSVRecordException
	 */
	@Test(expected = InvalidCSVRecordException.class)
	public void testInvalidRequiereFields() throws InvalidCSVRecordException {
		
		getInvalidReqFieldsFile();
		CSVRecord row;
//		 Pflichtfelder sind leer
		for (int i = 0; i < reader.getValidEntries().size(); i++) {
				row = reader.getInvalidEntries().get(i);
				try {
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_GEB_DAT, true);
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_KVNR, true, Task.PATTERN_KVNR);
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_NAME, true, Task.PATTERN_NONUMB);
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_VORNAME, true, Task.PATTERN_NONUMB);
					CSVReader.getValue(row, CSVReader.COL_ORDERED_DATE, true);
					CSVReader.getValue(row, CSVReader.COL_TASK_ID, true, Task.PATTERN_TASKID);
					CSVReader.getValue(row, CSVReader.COL_TASK_TYPE, true, Task.PATTERN_NONUMB);
				} catch (InvalidCSVRecordException e) {
					throw e;
				} 
			}
	}
	
	/**
	 * Lie�t zuerst die fehlerhafte Datei mit der {@link #getInvalidReqFieldsFile()}-Methode.
	 * Legt eine CSVRecord Variable an.
	 * Holt sich in einer For-Schleife jede Zeile und pr�ft bei jedem Durchgang,
	 * ob auf false gesetzte Spalten(= keine Pflichtfelder) eine {@link InvalidCSVRecordException} wirft,
	 * wenn die abgefragte Spalte leer ist.
	 * @throws InvalidCSVRecordException
	 */
	@Test
	public void testInvalidNonRequiereFields() throws InvalidCSVRecordException  {
		// NonPflichtfelder(Spalten auf false gesetzt) sind leer.
		getInvalidReqFieldsFile();
		CSVRecord row;
			for (int i = 0; i < reader.getValidEntries().size(); i++) {
				row = reader.getInvalidEntries().get(i);
				try {
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_TITEL, false, Task.PATTERN_NONUMB);
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_VSWORT, false, Task.PATTERN_NONUMB);
					CSVReader.getValue(row, CSVReader.COL_MITGLIED_ZSWORT, false, Task.PATTERN_NONUMB);
				} catch (InvalidCSVRecordException e) {
					throw e;
				} 
			}
	}
	//TODO Pr�fen ob bei Leerzeile richtige Exception
	//TODO Pr�fen ob bei parseDate oder isDateValid richtige Exception 
}
