package de.gokv.client.taskviewer;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import de.gokv.client.reader.CSVReader;
import de.gokv.client.reader.Task;
import exceptions.ClientException;

public class CSVReaderTest {

	private CSVReader readerValid;
	private CSVReader readerInvalid;
	private String filePath;
	private Task oneTask;

	@Before
	public void getValidFile() {
		filePath = "/src/test/resources/famv_direct_input_monitoring_20170515131131.csv";
		readerValid = new CSVReader(
				System.getProperty("user.dir") + filePath);
	}
	@Before
	public void getInvalidFile() {
		filePath = "/src/test/resources/invalid_famv_direct_input_monitoring_20170515131131.csv";
		readerInvalid = new CSVReader(System.getProperty("user.dir")
				+ filePath);
	}

	// überprüft ob die Datei vorhanden ist.
	@Test(expected = ClientException.class)
	public void testCSVReaderWrongFileException() {
		String wrongfile_wrongPath = System.getProperty("user.dir") + "/falscher/test/Pfad/falsche_famv_direct_input_monitoring_20170515131131.csv";
		readerValid = new CSVReader(wrongfile_wrongPath);
		try {
			readerValid.readCSVFile();
		} catch (ClientException e) {
			Assert.assertEquals("Datei " + wrongfile_wrongPath + " wurde nicht gefunden", e.getMessage());
			Assert.assertTrue(e.getCause() instanceof FileNotFoundException);
			throw e;
		}
	}

	// überprüft ob die erwarteten Inhalte gelesen werden können.
	
	@Test
	public void testCSVReadContent() {
		readerValid.readCSVFile();
		oneTask = readerValid.getTasks().get(0);
		Assert.assertEquals(4, readerValid.getTasks().size());
		Assert.assertEquals("405750F0395F11E7BED7F726E269B87F", oneTask.getTask_id());
		Assert.assertEquals("famv_bestand", oneTask.getTasktype());
		Assert.assertEquals("2017-05-15", oneTask.getOrdered_date().toString());
		Assert.assertEquals("Q101084865", oneTask.getKvnr());
		Assert.assertEquals("KRUPPIO", oneTask.getName());
		Assert.assertEquals("Ulrich Moritz", oneTask.getVorname());
		Assert.assertEquals("", oneTask.getZsWort());
		Assert.assertEquals("", oneTask.getVsWort());
		Assert.assertEquals("1982-03-06", oneTask.getGeb_dat().toString());

	}

	@Test
	public void testValidIDPattern(){
		
	}
	
	

}
