package de.gokv.client.reader;



public class TestMeInMain {

	public static void main(String[] args) {

		int eintrag = 2;
		CSVReader reader = new CSVReader(System.getProperty("user.dir")
				+ "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();
		Task t = reader.getTasks().get(eintrag);

//		// Ausgabe 1
//		System.out.println("Es gibt " + reader.getTasks().size() + " Einträge.");
//		System.out.println(">> Eintrag " + (reader.getTasks().indexOf(t)) + " << \n"
//				+ CSVReader.COL_MITGLIED_GEB_DAT + ": " + t.getGeb_dat());

	

	}
}
