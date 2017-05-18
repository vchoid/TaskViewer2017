package de.gokv.client.reader;

public class TestMeInMain {

	public static void main(String[] args) {
		
		// 
		CSVReader reader = new CSVReader("../gokv-client-task-viewer/src/main/resources/famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();
		Task t = reader.getTasks().get(0);
		System.out.println(t.getGeb_dat());
		
		
		//	TEST - SYSO ---------------------------------------------
		
		
		
		
		//  _________________________________________________________
	}

}
