package de.gokv.client.reader;

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

import Exceptions.ClientException;
import Exceptions.InvalidCSVRecordException;
import Exceptions.InvalidDateException;
import Utils.DateUtil;

/**
 * 
 * @author Christoph Kiank
 * @version 0.0.1
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

	// ungültige Einträge aus CSV ++++++++++++++++++++++++++++++++++++
	private List<CSVRecord> invalidEntries = new ArrayList<CSVRecord>();

	// Zeileninträge in eine Liste speichern
	private List<Task> tasks = new ArrayList<Task>();

	// Datei-Abfrage - Dateipfad +++++++++++++++++++++++++++++++++++++++
	private String filePath;

	public CSVReader(String path) {
		filePath = path;
	}

	// CSV-Datei auslesen +++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 
	 * @throws ClientException
	 */
	public void readCSVFile() throws ClientException {

		FileReader csvLesen = null;
		CSVParser csvFileParser = null;

		// erste Zeile als Titelzeile festlegen durch automatisches Parsing
		CSVFormat csvFileFormat = CSVFormat.newFormat(';').withHeader();

		try {
			// csv Lesen - starten ++++++++++++++++++++++++++++++++++++++
			csvLesen = new FileReader(filePath);

			// csv zergliedern - starten ++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvLesen, csvFileFormat);

			// TODO: Überprüfung der Header in der CSV
			// System.out.println(csvFileParser.getHeaderMap());

			// Eine Zeile aus der CSV +++++++++++++++++++++++++++++++++++
			for (CSVRecord csvRecord : csvFileParser.getRecords()) {
				try {

					Task oneTaskRow = Task.createTaskFromRecord(csvRecord);
					if (!tasks.contains(oneTaskRow))
						tasks.add(oneTaskRow);

				} catch (InvalidCSVRecordException e) {

					invalidEntries.add(csvRecord);
					System.err.println("\n\n-------------------------------------------------------- Meldung "
							+ invalidEntries.size() + " --------------------------------------------------------\n "
							+ e.getMessage());
				}
			}

		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei " + filePath + " wurde nicht gefunden");

		} catch (IOException e) {
			throw new ClientException(e, "Es ist ein Fehler beim Lesen der Datei aufgetreten");

		} finally {
			try {
				if (csvLesen != null)
					csvLesen.close();
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
	 * @param colName
	 * @return
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
	 * @param rec	CSV-Datei
	 * @param colName	Spalte der CSV-Datei
	 * @param require	<i>true</i> = Pflichtfeld, <i>false</i> = kein Pflichtfeld
	 * @return Gibt den Wert aus der CSV-
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, boolean require) throws InvalidCSVRecordException {
		String s = getValue(rec, colName);

		if (require && StringUtils.isBlank(s)) {
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber());
		}
		return s;
	}

	/**
	 * 
	 * @param rec
	 * @param colName
	 * @param pattern
	 * @param require
	 * @return
	 * @throws InvalidCSVRecordException
	 */
	public static String getValue(CSVRecord rec, String colName, Pattern pattern, boolean require)
			throws InvalidCSVRecordException {
		String value = getValue(rec, colName, require);
		if (!pattern.matcher(value).matches())
			throw new InvalidCSVRecordException(colName, rec.getRecordNumber(),
					"Der Wert %s ist für das Format %s nicht gültig.", value, pattern.toString());
		return value;
	}

	/**
	 * 
	 * @param rec
	 * @param colName
	 * @param require
	 * @return
	 * @throws InvalidCSVRecordException
	 * @throws InvalidDateException
	 */
	public static LocalDate getValueAsDate(CSVRecord rec, String colName, boolean require)
			throws InvalidCSVRecordException, InvalidDateException {
		String s = getValue(rec, colName, require);
		LocalDate localDate = DateUtil.parseDate(s, colName);

		return localDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
