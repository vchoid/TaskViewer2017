package de.gokv.client.reader;

public class TestMeInMain {

	public static void main(String[] args) {
		
		// 
		CSVReader csv = new CSVReader("C:/java/workspaces/BitgoKV/gokv-client-main/test-output/famv_direct_input_monitoring_20170515131131.csv");
		csv.readCsvFile();
		
		
		//	TEST - SYSO ---------------------------------------------
		System.out.println(csv.getEntry(0));
		System.out.println(csv.getEntry(1).get(0));
		
		
		
		//  _________________________________________________________
	}

}
