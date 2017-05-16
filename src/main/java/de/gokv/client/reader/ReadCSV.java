package de.gokv.client.reader;

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
			"mitglied_kvnr", "mitglied_name", "mitglied_vorname", "mitglied_titel", "mitglied_zswort", "mitglied_vswort",
			"mitglied_gebdat" };
	
	//	Attribute
	private static final String TASK_ID = "TaskId";
	private static final String TASK_TYPE = "TaskType";
	private static final String ORDERED_DATE = "Datum";
	private static final String MITGLIED_KVNR = "KvNr";
	private static final String MITGLIED_NAME = "Name";
	private static final String MITGLIED_VORNAME = "Vorname";
	private static final String MITGLIED_TITEL = "Titel";
	private static final String MITGLIED_ZSWORT = "zsWort";
	private static final String MITGLIED_VSWORT = "vsWort";
	private static final String MITGLIED_GEB_DAT = "Geburtsdatum";

	
	
	public static void readCsvFile(String fileName) {

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		
		//Titelzeile festlegen über die FILE_HEADER Variable
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
//        try {
//        	
        	//Aufgaben Liste
        	List tasks = new ArrayList();
            
//            //initialize FileReader object
//            fileReader = new FileReader(fileName);
//            
//            //initialize CSVParser object
//            csvFileParser = new CSVParser(fileReader, csvFileFormat);
//            
//            //Get a list of CSV file records
//            List csvRecords = csvFileParser.getRecords(); 
//            
//            //Read the CSV file records starting from the second record to skip the header
//            for (int i = 1; i < csvRecords.size(); i++) {
//            	CSVRecord record = csvRecords.get(i);
//            	//Create a new student object and fill his data
//            	Student student = new Student(Long.parseLong(record.get(STUDENT_ID)), record.get(STUDENT_FNAME), record.get(STUDENT_LNAME), record.get(STUDENT_GENDER), Integer.parseInt(record.get(STUDENT_AGE)));
//                students.add(student);	
//			}
//            
//            //Print the new student list
//            for (Student student : students) {
//				System.out.println(student.toString());
//			}
//        } 
//        catch (Exception e) {
//        	System.out.println("Error in CsvFileReader !!!");
//            e.printStackTrace();
//        } finally {
//            try {
//                fileReader.close();
//                csvFileParser.close();
//            } catch (IOException e) {
//            	System.out.println("Error while closing fileReader/csvFileParser !!!");
//                e.printStackTrace();
//            }
//        }
//
	}
	
	
}
