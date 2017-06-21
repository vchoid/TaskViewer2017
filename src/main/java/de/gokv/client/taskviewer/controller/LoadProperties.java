package de.gokv.client.taskviewer.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import de.gokv.client.taskviewer.exceptions.ClientException;

public class LoadProperties{

	private File file;
	private String propVal;
	private String propTitle;
	private String propFileName;
	private Properties prop;
	private BufferedInputStream bis;
	
	// Konstruktor +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public LoadProperties(String propFileName) {
		file = new File(
				"C:/java/workspaces/BitgoKV/gokv-client-task-viewer/src/main/resources/" + propFileName);
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei '" + file + "' wurde nicht gefunden");
		}
	}
	
	public LoadProperties(String propFileName, String filePath) {
		this(propFileName);
		try {
			file = new File(filePath);
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei " + filePath + " wurde nicht gefunden");
		}
	}
	
	public LoadProperties(String propFileName, String filePath, String propTitle) {
		this(propFileName, filePath);
		try {
			file = new File(filePath);
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Datei " + filePath + " wurde nicht gefunden");
		}
	}
			
	public String getPropertyVal(String propTitle){
		try {
			prop = new Properties();
			prop.load(bis);
			propVal = prop.getProperty(propTitle);
		} catch (IOException e) {
			throw new ClientException(e, "Es ist ein Fehler beim Lesen der Datei aufgetreten");
		}
		return propVal;
	}


	

}
