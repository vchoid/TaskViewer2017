package de.gokv.client.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ReadCSV {

	// Titeleiste von CSV auslesen. (statisch)+++++++++++++++++++++++++++++
	private static final String[] FILE_HEADER_MAPPING = { "gokv_task_id", "gokv_tasktype", "gokv_ordered_date",
			"mitglied_kvnr", "mitglied_name", "mitglied_vorname", "mitglied_titel", "mitglied_zswort",
			"mitglied_vswort", "mitglied_gebdat" };

	// Attribute  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private static final String TASK_ID = "gokv_task_id";
	private static final String TASK_TYPE = "gokv_tasktype";
	
	private static final String ORDERED_DATE = "gokv_ordered_date";
	private static final String MITGLIED_KVNR = "mitglied_kvnr";
	private static final String MITGLIED_NAME = "mitglied_name";
	private static final String MITGLIED_VORNAME = "mitglied_vorname";
	
	private static final String MITGLIED_TITEL = "mitglied_titel";
	private static final String MITGLIED_ZSWORT = "mitglied_zswort";
	private static final String MITGLIED_VSWORT = "mitglied_vswort";
	private static final String MITGLIED_GEB_DAT = "mitglied_gebdat";

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void readCsvFile(String fileName) {

		FileReader csvLesen = null;
		CSVParser csvFileParser = null;
		String [] inhaltEinerZeileArray = null;
		String [] titelZeileArray = null;
		
		// Titelzeile festlegen über die FILE_HEADER Variable  ++++++++++++
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {

			
			// csv Lesen - starten ++++++++++++++++++++++++++++++++++++++++
			csvLesen = new FileReader(fileName);

			// csv zergliedern - starten  +++++++++++++++++++++++++++++++++
			csvFileParser = new CSVParser(csvLesen, csvFileFormat);

			// Eine Zeile aus der CSV in Liste speichern  +++++++++++++++++
			List <CSVRecord> zeilenInListe = csvFileParser.getRecords();
			
			// Inhalt der Spalten in Array speichern  +++++++++++++++++++++
			for (int i = 1; i < zeilenInListe.size(); i++) {
				System.out.println("+++++++++++++++++ Task " + i + " ++++++++++++++++++++++");
				// Eine Zeile aufplitten und den Inhalt getrennt in einem Array speichern
				inhaltEinerZeileArray = zeilenInListe.get(i).get(0).split(";");
				titelZeileArray = zeilenInListe.get(0).get(0).split(";");
				// Inhalt auslesen/ausgeben
				for (int j = 0; j < inhaltEinerZeileArray.length; j++) {
					System.out.println(titelZeileArray[j] + ": " + inhaltEinerZeileArray[j]);
				}
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(">> Fehler beim auslesen <<");
		} finally {
			try {

				csvLesen.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println(">>> Fehler während des Schließens vom fileReader/csvFileParser <<<");
				e.printStackTrace();
			}
		}

	}
}
