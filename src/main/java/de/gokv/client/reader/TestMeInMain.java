package de.gokv.client.reader;

public class TestMeInMain {

	public static void main(String[] args) {

		// int eintrag;
		CSVReader reader = new CSVReader(System.getProperty("user.dir")
				+ "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();

//		for (Task task : reader.getTasks()) {
//			System.out.println(task);
//		}
//
//		// gebDat
//		String gebDat;
//		for (int i = 0; i < reader.getTasks().size(); i++) {
//			gebDat = reader.getTasks().get(i).getGeb_dat();
//			System.out.println(gebDat);
//		}
	}
}
