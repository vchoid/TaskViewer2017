package de.gokv.client.taskviewer;

import org.junit.Before;
import org.junit.Test;

import de.gokv.client.reader.CSVReader;
import de.gokv.client.reader.ValidDate;

public class ValidDateTest {

	private ValidDate testDate = new ValidDate();
	private CSVReader reader;

	private String orderDate = "";
	private String gebDate = "";

	@Before
	public void before() {
		reader = new CSVReader(System.getProperty("user.dir")
				+ "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv");
		reader.readCsvFile();
	}

	@Test
	public void testGebDate() {

		for (int i = 0; i < reader.getTasks().size(); i++) {
			gebDate = reader.getTasks().get(i).getGeb_dat();
			System.out.println(gebDate + " = " + testDate.validDate(gebDate));
		}
	}

	@Test
	public void testOrderDate() {

		for (int i = 0; i < reader.getTasks().size(); i++) {
			orderDate = reader.getTasks().get(i).getOrdered_date();
			System.out.println(orderDate + " = " + testDate.validDate(orderDate));
		}
	}

}
