package de.gokv.client.reader;

import java.time.LocalDate;
import java.util.Date;

public class TestMeInMain {

	
	public static void main(String[] args) {

		
		
		// int eintrag;
		CSVReader reader = new CSVReader(System.getProperty("user.dir")
				+ "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();

//		for (Task task : reader.getTasks()) {
//			System.out.println(task);
//		}

				
		// orderedDate
		LocalDate oDate;
		for (int i = 0; i < reader.getTasks().size(); i++) {
			oDate = reader.getTasks().get(i).getOrdered_date();
			System.out.println(oDate);
			
		}
//		// gebDat
//		Date gDate;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			gDate = reader.getTasks().get(i).getGeb_dat();
//			System.out.println(gDate.toLocaleString().substring(0, 10));
//		}
		
//		// Id
//		String iD;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			iD = reader.getTasks().get(i).getTask_id();
//			System.out.println(iD);
//		}
	}
}
