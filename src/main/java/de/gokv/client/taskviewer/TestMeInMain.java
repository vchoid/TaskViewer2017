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
	private static void getInvalidReqFieldFile() {
		filePath = "/src/test/resources/invalidReqFields_famv_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	private static void getTaskTypesFile() {
		filePath = "/src/test/resources/taskTypes_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	private static void getInvalidTaskTypesFile() {
		filePath = "/src/test/resources/invalid_taskTypes_direct_input_monitoring_20170515131131.csv";
		getFile(filePath);
	}
	
	public static void main(String[] args) {

		//gültige Einträge in CSV-Datei +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
//		getValidFile();
//		getTaskTypesFile();
		getInvalidTaskTypesFile();
		for (Task task : reader.getValidEntries()) {
			System.out.println(task);
		}
		
		
		// ungültige Einträge in CSV ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		getInvalidFile();
//		getInvalidReqFieldFile();
//		getInvalidKVNrFile();
//		getInvalidTaskIdFile();
				

	}
}
