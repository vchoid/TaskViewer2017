package de.gokv.client.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ReadCSV {

	// Titeleiste von CSV auslesen. (statisch)
	private static final String[] FILE_HEADER_MAPPING = { "gokv_task_id", "gokv_tasktype", "gokv_ordered_date",
			"mitglied_kvnr", "mitglied_name", "mitglied_vorname", "mitglied_titel", "mitglied_zswort",
			"mitglied_vswort", "mitglied_gebdat" };

	// Attribute
	private static final String TASK_ID = "gokv_task_id";
	private static final String TASK_TYPE = "gokv_tasktype";
	private static final String ORDERED_DATE = "gokv_ordered_date";
	private static final String MITGLIED_KVNR = "mitglied_kvnr";
	private static final String MITGLIED_NAME = "mitglied_name";
	private static final String MITGLIED_VORNAME = "mitglied_vorname";
	private static final String MITGLIED_TITEL = "mitglied_titel";
	private static final String MITGLIED_ZSWORT = "mitglied_zswort";
	private static final String MITGLIED_VSWORT = "mitglied_vswort";
	private static final String MITGLIED_GEB_DAT = "mitglied_gebdat";

	public static void readCsvFile(String fileName) {

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		
		
		//Titelzeile festlegen über die FILE_HEADER Variable
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
        try {
        	
        	//Aufgaben Liste
        	String[] tasks = null;
            
            //FileReader object starten
            fileReader = new FileReader(fileName);
           
//            System.out.println(fileReader);
            
            //CSVParser object starten
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
//            System.out.println(csvFileFormat);
            
            //Inhalt der .csv-Datei in einer Liste speichern
            List csvRecords = csvFileParser.getRecords(); 
//            System.out.println("Zeilenlänge: << " + csvRecords.size() + " >>\n" + csvRecords );
            
//          Read the CSV file records starting from the second record to skip the header
            
            for (int i = 1; i < csvRecords.size(); i++) {
            	CSVRecord record = (CSVRecord) csvRecords.get(i);
            	tasks[i]= (String) csvRecords.get(i);
            	System.out.println(tasks);
                    
            	//Create a new student object and fill his data
            
//            	Task task = new Task(record.get(TASK_ID), record.get(TASK_TYPE), record.get(ORDERED_DATE), record.get(MITGLIED_KVNR), record.get(MITGLIED_NAME), record.get(MITGLIED_VORNAME),record.get(MITGLIED_TITEL),record.get(MITGLIED_ZSWORT),record.get(MITGLIED_VSWORT),record.get(MITGLIED_GEB_DAT));
//                tasks.add(task);	
			}
           
//            //Print the new student list
//            for (int j = 0; j < tasks.size(); j++) {
//				System.out.println(tasks.toString());
//			}
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }

	
	}
}
