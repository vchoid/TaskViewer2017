package de.gokv.client.taskviewer;

import java.time.LocalDate;

public class TestMeInMain {

	private static CSVReader readerValid;
	private static CSVReader readerInvalid;
	
	public static void main(String[] args) {

//		//gültige Einträge in CSV-Datei +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		readerValid = new CSVReader(
//				System.getProperty("user.dir") + "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv");
//		
//		readerValid.readCSVFile();
//
//		for (Task task : readerValid.getTasks()) {
//			System.out.println(task);
//		}
		
		
		// ungültige Einträge in CSV ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		readerInvalid = new CSVReader(System.getProperty("user.dir")
				+ "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv");
		
//		readerInvalid.readCSVFile();

//		for (Task task : readerInvalid.getTasks()) {
//			System.out.println(task);
//		}

				
//		// orderedDate
//		LocalDate oDate;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			oDate = reader.getTasks().get(i).getOrdered_date();
//			System.out.println(oDate);
//			
//		}
//		// Id
//		String iD;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			iD = reader.getTasks().get(i).getTask_id();
//			System.out.println(iD);
//		}
//		// gebDat
//		LocalDate gDate;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			gDate = reader.getTasks().get(i).getGeb_dat();
//			System.out.println(gDate);
//		}
//		
//		// KVNr
//		String kvNr;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			kvNr = reader.getTasks().get(i).getKvnr();
//			System.out.println(kvNr);
//		}
	}
}
