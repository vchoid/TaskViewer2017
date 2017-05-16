package de.gokv.client.reader;

public class ShowCSV_test {

	public static void main(String[] args) {
		
		ReadCSV csv = new ReadCSV();
		csv.readCsvFile("C:/java/workspaces/BitgoKV/gokv-client-main/test-output/famv_direct_input_monitoring_20170515131131.csv");
		
	}

}
