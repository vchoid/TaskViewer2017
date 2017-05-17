package de.gokv.client.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {

	// Attribute +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static final String TASK_ID = "gokv_task_id";
	public static final String TASK_TYPE = "gokv_tasktype";

	public static final String ORDERED_DATE = "gokv_ordered_date";
	public static final String MITGLIED_KVNR = "mitglied_kvnr";
	public static final String MITGLIED_NAME = "mitglied_name";
	public static final String MITGLIED_VORNAME = "mitglied_vorname";

	public static final String MITGLIED_TITEL = "mitglied_titel";
	public static final String MITGLIED_ZSWORT = "mitglied_zswort";
	public static final String MITGLIED_VSWORT = "mitglied_vswort";
	public static final String MITGLIED_GEB_DAT = "mitglied_gebdat";

	private String filePath;

	private List<CSVRecord> entries = new ArrayList<CSVRecord>();

	public CSVReader(String path) {
		filePath = path;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void readCsvFile() throws ClientException {

		FileReader csvLesen = null;
		CSVParser csvFileParser = null;

		// Titelzeile festlegen durch automatisches Parsing ++++++++++++
		CSVFormat csvFileFormat = CSVFormat.newFormat(';').withHeader();

		try {

			// csv Lesen - starten ++++++++++++++++++++++++++++++++++++++++
			csvLesen = new FileReader(filePath);

			// csv zergliedern - starten +++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvLesen, csvFileFormat);

			// Eine Zeile aus der CSV in Liste speichern +++++++++++++++++
			entries = csvFileParser.getRecords();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ClientException(e, "Datei " + filePath + "wurde nicht gefunden");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ClientException(e, "Es ist ein Fehler beim Lesen der Datei aufgetreten");
		} finally {
			try {

				csvLesen.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println(">>> Fehler w‰hrend des Schlieﬂens vom fileReader/csvFileParser <<<");
				e.printStackTrace();
			}
		}

	}

	public CSVRecord getEntry(int i) {
		return entries.get(i);
	}

	public int getEntryCount() {
		return entries.size();
	}
}
