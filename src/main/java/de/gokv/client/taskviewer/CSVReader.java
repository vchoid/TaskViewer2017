package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.oxbow.swingbits.dialog.task.TaskDialogs;

import de.gokv.client.taskviewer.exceptions.ClientException;
import de.gokv.client.taskviewer.exceptions.InvalidCSVRecordException;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;

/**
 * Eine CSV auslesen und Daten zur�ckgeben.
 * 
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #readCSVFile}</b>: Lie�t die CSV-Datei, setzt eine Header-Map
 * und versucht den Inhalt einer Reihe in eine Task zu speichern.</li>
 * <li><b>{@link #getValue(CSVRecord, String)}</b>: Holt ein Wert einer Spalte
 * aus der CSV und gibt ihn als String zur�ck.</li>
 * <li><b>{@link #getValue(CSVRecord, String, boolean)}</b>: Holt ein Wert einer
 * Spalte eines Pflichtfeldes aus der CSV und gibt ihn als String zur�ck.</li>
 * <li><b>{@link #getValue(CSVRecord, String, Pattern, boolean)}</b>: Holt ein
 * Wert einer Spalte, nach einem bestimmtem Pattern eines Pflichtfeldes aus der
 * CSV und gibt ihn als String zur�ck.</li>
 * <li><b>{@link #getValueAsDate( CSVRecord, String, boolean)}</b>: Holt ein
 * Wert einer Spalte eines Pflichtfeldes aus der CSV und gibt ihn als LocalDate
 * zur�ck.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * 
 *
 */
public class CSVReader {

	// title column - Titelzeile ++++++++++++++++++++++++++++++++++++++++
	public static final String COL_TASK_ID = "gokv_task_id";
	public static final String COL_TASK_TYPE = "gokv_tasktype";
	public static final String COL_ORDERED_DATE = "gokv_ordered_date";
	public static final String COL_MITGLIED_KVNR = "mitglied_kvnr";
	public static final String COL_MITGLIED_NAME = "mitglied_name";
	public static final String COL_MITGLIED_VORNAME = "mitglied_vorname";
	public static final String COL_MITGLIED_TITEL = "mitglied_titel";
	public static final String COL_MITGLIED_ZSWORT = "mitglied_zswort";
	public static final String COL_MITGLIED_VSWORT = "mitglied_vswort";
	public static final String COL_MITGLIED_GEB_DAT = "mitglied_gebdat";

	// ung�ltige Eintr�ge aus der CSV ++++++++++++++++++++++++++++++++++
	private List<CSVRecord> invalidEntries = new ArrayList<CSVRecord>();
	private static List<String> abstrExc = new ArrayList<String>();
	private static List<String> invFilePath = new ArrayList<String>();

	public int invEntSize;

	// g�ltige Eintr�ge aus der CSV
	private List<Task> validEntries = new ArrayList<Task>();

	// Datei-Abfrage - Dateipfad +++++++++++++++++++++++++++++++++++++++
	private static String filePath;
	// private static String invFilePath;

	public CSVReader(String path) {
		filePath = path;
	}

	// CSV-Datei auslesen +++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * Lie�t eine CSV-Datei, setzt die erste Zeile als Header-Map und versucht
	 * den Inhalt einer Reihe in eine Task zu speichern. Beim Versuch
	 * fehlerhafte Eintr�ge zu speichern, wird ein
	 * {@link InvalidCSVRecordException} geworfen und die fehlerhaften Eintr�ge
	 * in einer weiteren Liste gespeichert.
	 * 
	 * 
	 * @throws ClientException
	 *             Wird geworfen, wenn die CSV-Datei nicht existiert oder ein
	 *             Abbruch beim �ffnen der Datei passierte.
	 * @throws InvalidCSVRecordException
	 *             Wird beim lesen fehlerhafter Werte geworfen.
	 * 
	 * 
	 */
	public void readCSVFile() throws ClientException {

		FileReader csvFile = null;
		CSVParser csvFileParser = null;

		// erste Zeile als Titelzeile festlegen durch automatisches Parsing
		CSVFormat csvHeaderFormat = CSVFormat.newFormat(';').withHeader();

		try {
			// csv Lesen - starten ++++++++++++++++++++++++++++++++++++++
			csvFile = new FileReader(filePath);

			// csv zergliedern - starten ++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvFile, csvHeaderFormat);

			// Eine Zeile aus der CSV +++++++++++++++++++++++++++++++++++
			for (CSVRecord csvRecord : csvFileParser.getRecords()) {
				try {

					Task oneTask_oneRow = Task.createTaskFromRecord(csvRecord);
					if (!validEntries.contains(oneTask_oneRow))
						validEntries.add(oneTask_oneRow);

				} catch (InvalidCSVRecordException e) {
					invalidEntries.add(csvRecord);
					setInvalidEntries(invalidEntries);
					abstrExc.add(e.getMessage());
					if (!invFilePath.contains(filePath)) {
						invFilePath.add("\n");
						invFilePath.add(filePath);
					}

				}
			}
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Fehler beim Laden der Datei", "Datei " + filePath + " wurde nicht gefunden",
					1);

		} catch (IOException e) {
			throw new ClientException(e, "Ein/Ausgabe Fehler", " Es ist ein Fehler beim Lesen der Datei aufgetreten", 2);

		} finally {
			try {
				if (csvFile != null)
					csvFile.close();
				if (csvFileParser != null)
					csvFileParser.close();
			} catch (IOException e) {
				throw new ClientException(e, "Ein/Ausgabe Fehler", " Es ist ein Fehler beim Lesen der Datei aufgetreten",
						2);
			}
		}
	}

	/**
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zur�ck.
	 * 
	 * @param rec
	 *            Werte aus der CSV-Datei lesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @return Gibt den Wert von der CSV-Datei-Spalte zur�ck.
	 */
	public static String getValue(CSVRecord rec, String colName) {
		String s = "";
		if (rec.isSet(colName)) {
			s = rec.get(colName);
		}
		return s;
	}

	/**
	 * 
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zur�ck.
	 * �berpr�ft ob es sich um ein Pflichfeld handelt und wirft eine
	 * {@link InvalidCSVRecordException}, wenn dies nicht der Fall ist.
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert von der CSV-Datei-Spalte zur�ck.
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, boolean requireField)
			throws InvalidCSVRecordException {
		String s = getValue(rec, colName);

		if (requireField && StringUtils.isBlank(s)) {
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber());
		}
		return s;
	}

	/**
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zur�ck.
	 * �berpr�ft ob es sich um ein Pflichfeld handelt und wirft eine
	 * {@link InvalidCSVRecordException}, wenn dies nicht der Fall ist.
	 * �berpr�ft, ob der Wert ein bestimmtes Pattern aufweist, wirft eine
	 * {@link InvalidCSVRecordException}, wenn dies nicht der Fall ist.
	 * 
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param pattern
	 *            Wert aus der Spalte, auf ein bestimmest Pattern pr�fen.
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert von der CSV-Datei-Spalte zur�ck.
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, boolean requireField, Pattern pattern)
			throws InvalidCSVRecordException {
		String value = getValue(rec, colName, requireField);
		if (!pattern.matcher(value).matches())
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber(),
					"-ung�ltiger Wert-%s Der Wert %s ist f�r das Format %s nicht g�ltig.%s", System.lineSeparator(),
					value, pattern.toString(), System.lineSeparator());
		return value;
	}

	/**
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zur�ck.
	 * �berpr�ft ob es sich um ein Pflichfeld handelt und wirft eine
	 * {@link InvalidCSVRecordException}, wenn dies nicht der Fall ist.
	 * �berpr�ft ob es sich um ein TaskTyp aus der ArrayList
	 * {@link Task#TASK_TYPES TASK_TYPES} handelt.
	 */
	public static String getMappedValue(CSVRecord rec, String colName, boolean requireField,
			ArrayList<String> taskTypeList) throws InvalidCSVRecordException {

		String value = getValue(rec, colName, requireField);
		if (!(taskTypeList.contains(value))) {
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber(),
					"-Tasktype-Fehler-%s \'%s\' ist kein g�ltiger TaskType %s.", System.lineSeparator(), value,
					System.lineSeparator());
		}
		return value;

	}

	/**
	 * 
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als LocalDate zur�ck.
	 * �berpr�ft ob es sich um ein Pflichfeld handelt und wirft eine
	 * {@link InvalidCSVRecordException}, wenn dies nicht der Fall ist.
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert aus der Spalte als LocalDate zur�ck.
	 * @throws InvalidCSVRecordException
	 * @throws InvalidDateException
	 */
	public static LocalDate getValueAsDate(CSVRecord rec, String colName, boolean requireField)
			throws InvalidCSVRecordException, InvalidDateException {
		String s = getValue(rec, colName, requireField);
		LocalDate localDate = DateUtil.parseDate(s, colName);
		return localDate;
	}

	public static String getFilePath() {
		return filePath;
	}

	public List<Task> getValidEntries() {
		return validEntries;
	}

	public List<CSVRecord> getInvalidEntries() {
		return invalidEntries;
	}

	public void setInvalidEntries(List<CSVRecord> invalidEntries) {
		this.invalidEntries = invalidEntries;
	}

	public void setInvEntSize(int invEntSize) {
		this.invEntSize = invEntSize;
	}

	public int getInvEntSize() {
		return invEntSize;
	}

	public static List<String> getAbstrExc() {
		return abstrExc;
	}

	public static List<String> getInvFilePaths() {
		return invFilePath;
	}

}
