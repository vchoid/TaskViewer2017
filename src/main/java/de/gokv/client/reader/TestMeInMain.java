package de.gokv.client.reader;

public class TestMeInMain {

	public static void main(String[] args) {
		
		// 
		CSVReader csv = new CSVReader("C:/java/workspaces/BitgoKV/gokv-client-task-viewer/src/main/resources/famv_direct_input_monitoring_20170515131131.csv");
		csv.readCsvFile();
		
		
		//	TEST - SYSO ---------------------------------------------
		System.out.println(csv.COL_MITGLIED_ZSWORT);
		
		
		
		//  _________________________________________________________
	}

}
