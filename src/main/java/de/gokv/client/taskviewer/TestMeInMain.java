package de.gokv.client.taskviewer;

import java.time.LocalDate;

import org.apache.commons.csv.CSVRecord;

public class TestMeInMain {
	private static CSVReader reader;
	private static String filePath;

	private static void getFile(String filePath) {
		reader = new CSVReader(System.getProperty("user.dir") + filePath);
		reader.readCSVFile();
	}

	private static void getValidFile() {
		filePath = "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	private static void getInvalidFile() {
		filePath = "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	private static void getInvalidKVNrFile() {
		filePath = "/src/test/resources/invalidKVNr_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}

	private static void getInvalidTaskIdFile() {
		filePath = "/src/test/resources/invalidTaskId_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	
	public static void main(String[] args) {

		//g�ltige Eintr�ge in CSV-Datei +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		getValidFile();
		for (Task task : reader.getValidEntries()) {
			System.out.println(task);
		}
		
		
		// ung�ltige Eintr�ge in CSV ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		getInvalidFile();
//		getInvalidKVNrFile();
//		getInvalidTaskIdFile();
				

	}
}
