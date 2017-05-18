package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import de.gokv.client.reader.CSVReader;
import de.gokv.client.reader.ClientException;

public class ReadCSVTest {
	
	private CSVReader reader;
	
	@Before
	public void before() {
		reader = new CSVReader(System.getProperty("user.dir") + "/src/test/resources/test.csv");
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
		reader.readCsvFile();
		
	}
	
	
	
}
