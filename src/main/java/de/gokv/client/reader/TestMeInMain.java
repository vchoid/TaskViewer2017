package de.gokv.client.reader;

public class TestMeInMain {

	public static void main(String[] args) {
		
		// 
		int eintrag = 3;
		CSVReader reader = new CSVReader(System.getProperty("user.dir") + "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();
		Task t = reader.getTasks();
		
		
		//	TEST - SYSO ---------------------------------------------
		System.out.println("Es gibt " + reader.getTasks().size() + " Einträge.");
		System.out.println(">> Eintrag " + (reader.getTasks().indexOf(t)+1) + " << \n" + reader.COL_MITGLIED_GEB_DAT + ":" + t.getGeb_dat());
		
		
		
		//  _________________________________________________________
	}

}
