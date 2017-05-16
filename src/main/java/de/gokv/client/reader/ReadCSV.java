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

	// Titeleiste von CSV auslesen. (statisch)
	private static final String[] FILE_HEADER_MAPPING = { "gokv_task_id", "gokv_tasktype", "gokv_ordered_date",
			"mitglied_kvnr", "mitglied_name", "mitglied_vorname", "mitglied_titel", "mitglied_zswort",
			"mitglied_vswort", "mitglied_gebdat" };

	// Attribute
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

	public static void readCsvFile(String fileName) {

		FileReader csvLesen = null;
		CSVParser csvFileParser = null;

		// Titelzeile festlegen ¸ber die FILE_HEADER Variable
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
		System.out.println(csvLesen + " --> nach Titelzeile");

		try {

			List tasks = new ArrayList();

			// csv Lesen - starten
			csvLesen = new FileReader(fileName);
			System.out.println(csvLesen + " --> nach ini FileReader");

			// csv zergliedern - starten
			csvFileParser = new CSVParser(csvLesen, csvFileFormat);

			// speichern in einer Liste
			List<CSVRecord> inhaltsListe = csvFileParser.getRecords();
			String [] t2 = null;

			
			for (int i = 0; i < inhaltsListe.size()-1; i++) {
				tasks.add(inhaltsListe.get(i+1).toString());
				System.out.print(tasks.size() + ". ");
				System.out.print("<< " + tasks.getClass() + " >> ");
				System.out.println(tasks.get(i));
				for (int j = 0; j < tasks.size(); j++) {
					
				}

				// CSVRecord inhaltAusgabe = inhaltsListe.get(i);
				// Task aufgabe = new Task(inhaltAusgabe.get(TASK_ID),
				// inhaltAusgabe.get(TASK_TYPE),
				// inhaltAusgabe.get(ORDERED_DATE),
				// inhaltAusgabe.get(MITGLIED_KVNR),
				// inhaltAusgabe.get(MITGLIED_NAME),
				// inhaltAusgabe.get(MITGLIED_VORNAME),
				// inhaltAusgabe.get(MITGLIED_TITEL),
				// inhaltAusgabe.get(MITGLIED_ZSWORT),
				// inhaltAusgabe.get(MITGLIED_VSWORT),
				// inhaltAusgabe.get(MITGLIED_GEB_DAT));
				// tasks.add(aufgabe);
				// System.out.println(tasks.get(i));

				// System.out.println(tasks.get(1));

			}
			

		} catch (Exception e) {
			System.out.println(">> Error im CsvFileReader <<");
		} finally {
			try {
				
				csvLesen.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println(">>> Error w‰hrend des Schlieﬂen vom fileReader/csvFileParser <<<");
				e.printStackTrace();
			}
		}

	}
}
