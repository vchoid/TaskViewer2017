package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import de.gokv.client.taskviewer.exceptions.ClientException;
import de.gokv.client.taskviewer.exceptions.InvalidCSVRecordException;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;

/**
 * Eine CSV auslesen und Daten zurückgeben.
 * 
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #readCSVFile}</b>: Ließt die CSV-Datei, setzt eine Header-Map
 * und versucht den Inhalt einer Reihe in eine Task zu speichern.</li>
 * <li><b>{@link #getValue(CSVRecord, String)}</b>: Holt ein Wert einer Spalte
 * aus der CSV und gibt ihn als String zurück.</li>
 * <li><b>{@link #getValue(CSVRecord, String, boolean)}</b>: Holt ein Wert einer
 * Spalte eines Pflichtfeldes aus der CSV und gibt ihn als String zurück.</li>
 * <li><b>{@link #getValue(CSVRecord, String, Pattern, boolean)}</b>: Holt ein
 * Wert einer Spalte, nach einem bestimmtem Pattern eines Pflichtfeldes aus der
 * CSV und gibt ihn als String zurück.</li>
 * <li><b>{@link #getValueAsDate( CSVRecord, String, boolean)}</b>: Holt ein
 * Wert einer Spalte eines Pflichtfeldes aus der CSV und gibt ihn als LocalDate
 * zurück.</li>
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

	// ungültige Einträge aus der CSV ++++++++++++++++++++++++++++++++++
	private List<CSVRecord> invalidEntries = new ArrayList<CSVRecord>();

	// gültige Einträge aus der CSV
	private List<Task> validEntries = new ArrayList<Task>();

	// Datei-Abfrage - Dateipfad +++++++++++++++++++++++++++++++++++++++
	private static String filePath;

	public CSVReader(String path) {
		filePath = path;
	}

	// CSV-Datei auslesen +++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * Ließt eine CSV-Datei, setzt die erste Zeile als Header-Map und versucht
	 * den Inhalt einer Reihe in eine Task zu speichern.. Beim Versuch
	 * fehlerhafte Einträge zu speichern, wird ein
	 * {@link InvalidCSVRecordException} geworfen und die fehlerhaften Einträge
	 * in einer weiteren Liste gespeichert.
	 * 
	 * 
	 * @throws ClientException
	 *             Wird geworfen, wenn die CSV-Datei nicht existiert oder ein
	 *             Abbruch beim öffnen der Datei passierte.
	 * @throws InvalidCSVRecordException
	 *             Wird beim lesen fehlerhafter Einträge geworfen.
	 * 
	 * 
	 */
	public void readCSVFile() throws ClientException {

		FileReader csvFile = null;
		CSVParser csvFileParser = null;

		// TODO header Mapping ==> eigene Exception werfen.
		// erste Zeile als Titelzeile festlegen durch automatisches Parsing
		CSVFormat csvHeaderFormat = CSVFormat.newFormat(';').withHeader();

		try {
			// csv Lesen - starten ++++++++++++++++++++++++++++++++++++++
			csvFile = new FileReader(filePath);

			// csv zergliedern - starten ++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvFile, csvHeaderFormat);

			// TODO: Überprüfung der Header in der CSV
			// System.out.println(csvFileParser.getHeaderMap());

			// Eine Zeile aus der CSV +++++++++++++++++++++++++++++++++++
			for (CSVRecord csvRecord : csvFileParser.getRecords()) {
				try {

					Task oneTask_oneRow = Task.createTaskFromRecord(csvRecord);
					if (!validEntries.contains(oneTask_oneRow))
						validEntries.add(oneTask_oneRow);

				} catch (InvalidCSVRecordException e) {

					invalidEntries.add(csvRecord);
					System.err.println("\n\n-------------------------------------------------------- Meldung "
							+ invalidEntries.size() + " --------------------------------------------------------\n "
							+ "\nDatei: ../" + filePath.substring(70) + "\n"
							+ e.getMessage());
				}
			}

		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei " + filePath + " wurde nicht gefunden");

		} catch (IOException e) {
			throw new ClientException(e, "Es ist ein Fehler beim Lesen der Datei aufgetreten");

		} finally {
			try {
				if (csvFile != null)
					csvFile.close();
				if (csvFileParser != null)
					csvFileParser.close();
			} catch (IOException e) {
				// Swallow Exception
			}
		}
	}

	// TODO Methode column isSet()

	/**
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zurück.
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @return Gibt den Wert von der CSV-Datei-Spalte zurück.
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
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String zurück.
	 * Überprüft ob es sich um ein Pflichfeld handelt und wirft eine Exception,
	 * wenn dies nicht der Fall ist.
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert von der CSV-Datei-Spalte zurück.
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, boolean requireField) throws InvalidCSVRecordException {
		String s = getValue(rec, colName);

		if (requireField && StringUtils.isBlank(s)) {
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber());
		}
		return s;
	}

	/**
	 * Holt den Wert aus der CSV-Datei und gibt ihn wieder als String
	 * zurück.Überprüft, ob der Wert ein bestimmtes Pattern aufweist, wirft eine
	 * Exception, wenn dies nicht der Fall ist. Überprüft ob es sich um ein
	 * Pflichfeld handelt und wirft eine Exception, wenn dies nicht der Fall
	 * ist.
	 * 
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param pattern
	 *            Wert aus der Spalte, auf ein bestimmest Pattern prüfen.
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert von der CSV-Datei-Spalte zurück.
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, boolean requireField, Pattern pattern)
			throws InvalidCSVRecordException {
		String value = getValue(rec, colName, requireField);
		if (!pattern.matcher(value).matches())
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber(),
					"Der Wert %s ist für das Format %s nicht gültig.", value, pattern.toString());
		return value;
	}

	/**
	 * 
	 * @param rec
	 *            CSV-Datei einlesen.
	 * @param colName
	 *            Spalte der CSV-Datei
	 * @param requireField
	 *            <i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert von der CSV-Datei-Spalte zurück.
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

}
