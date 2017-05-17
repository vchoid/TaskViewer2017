package de.gokv.client.taskviewer;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import de.gokv.client.reader.CSVReader;

public class ReadCSVTest {
	
	private CSVReader reader;
	
	@Before
	public void before() {
		reader = new CSVReader(System.getProperty("user.dir") + "/src/test/resources/test.csv");
	}
	
	@Test
	public void testReadCSV() {
		reader.readCsvFile();
		CSVRecord entry = reader.getEntry(0);
		Assert.assertEquals(4, reader.getEntryCount());
		Assert.assertEquals("405750F0395F11E7BED7F726E269B87F", entry.get(CSVReader.TASK_ID));
		Assert.assertEquals("famv_bestand", entry.get(CSVReader.TASK_TYPE));
	}
	
}
