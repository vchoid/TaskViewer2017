package de.gokv.client.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {

	// Attribute +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

	// Einträge aus der CSV als Liste +++++++++++++++++++++++++++++++++++
	private List<CSVRecord> invalidEntries = new ArrayList<CSVRecord>();
	private List<Task> tasks = new ArrayList<Task>();

	// Dateipfad-Abfrage +++++++++++++++++++++++++++++++++++++++++++++++++
	private String filePath;

	public CSVReader(String path) {
		filePath = path;
	}

	// CSV-Datei auslesen ++++++++++++++++++++++++++++++++++++++++++++++++
	public void readCsvFile() throws ClientException {

		FileReader csvLesen = null;
		CSVParser csvFileParser = null;

		// Titelzeile festlegen durch automatisches Parsing ++++++++++++++
		CSVFormat csvFileFormat = CSVFormat.newFormat(';').withHeader();

		try {

			// csv Lesen - starten +++++++++++++++++++++++++++++++++++++++
			csvLesen = new FileReader(filePath);

			// csv zergliedern - starten +++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvLesen, csvFileFormat);

			// TODO: Use to validate csvFile, check if all required Headers are
			// present
			Map<String, Integer> headerMap = csvFileParser.getHeaderMap();

			// Eine Zeile aus der CSV in Liste speichern +++++++++++++++++

			for (CSVRecord csvRecord : csvFileParser.getRecords()) {
				try {
					tasks.add(Task.createTaskFromRecord(csvRecord));
				} catch (InvalidCSVRecordException e) {
					invalidEntries.add(csvRecord);
					System.out.println(e.getMessage());
				}
			}

		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei " + filePath + "wurde nicht gefunden");
		} catch (IOException e) {
			throw new ClientException(e, "Es ist ein Fehler beim Lesen der Datei aufgetreten");
		} finally {
			try {
				if (null != csvLesen)
					csvLesen.close();
				if (null != csvFileParser)
					csvFileParser.close();
			} catch (IOException e) {
				// Swallow Exception
			}
		}
	}
}

// TODO: Rückgabe von einer Liste von Task.class Objekten
