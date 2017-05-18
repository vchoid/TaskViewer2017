package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import de.gokv.client.reader.CSVReader;
import de.gokv.client.reader.ClientException;
import de.gokv.client.reader.Task;

public class ReadCSVTest {
	
	private CSVReader reader;
	private Task content;
	
	@Before
	public void before() {
		reader = new CSVReader(System.getProperty("user.dir") + "/src/main/resources/famv_direct_input_monitoring_20170515131131.csv");
		
	}
	
	@Test(expected=ClientException.class)
	public void testReadCSVExc(){
		String filePath = System.getProperty("user.dir") + "/src/test/resources/test2.csv";
		reader = new CSVReader(filePath);
		try{
			reader.readCsvFile();
		}catch(ClientException e){
			Assert.assertEquals("Datei " + filePath + "wurde nicht gefunden", e.getMessage());
			Assert.assertTrue(e.getCause() instanceof FileNotFoundException);
			throw e;
		}
	}
	
	@Test
	public void testReadCSV() {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/famv_direct_input_monitoring_20170515131131.csv";
		reader.readCsvFile();
		content = reader.getTasks().get(0);
		
		Assert.assertEquals(4, reader.getTasks().size());
		Assert.assertEquals("405750F0395F11E7BED7F726E269B87F", content.getTask_id());
		Assert.assertEquals("famv_bestand", content.getTasktype());
		Assert.assertEquals("15.05.2017", content.getOrdered_date());
		Assert.assertEquals("Q101084865", content.getKvnr());
		Assert.assertEquals("KRUPPIO", content.getName());
		Assert.assertEquals("Ulrich Moritz", content.getVorname());
		Assert.assertEquals("", content.getZsWort());
		Assert.assertEquals("", content.getVsWort());
		Assert.assertEquals("06.03.1982", content.getGeb_dat());
		
	}
	
	
	
}
