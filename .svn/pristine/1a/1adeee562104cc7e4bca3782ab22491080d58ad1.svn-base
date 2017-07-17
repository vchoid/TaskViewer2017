package de.gokv.client.taskviewer.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import de.gokv.client.taskviewer.exceptions.ClientException;

public class LoadProperties {

	private File file;
	private String propVal;
	private Integer propIntVal;
	private String propTitle;
	private Properties prop;
	private BufferedInputStream bis;

	// Konstruktor
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public LoadProperties(String propFileName, String propTitle) {
		this.propTitle = propTitle;
		try {
			file = new File("C:/java/workspaces/BitgoKV/gokv-client-task-viewer/src/main/resources/" + propFileName);
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Fehler beim Laden der Datei", "Der Wert mit der Bezeichung '" + propTitle
					+ "' in der Datei '" + propFileName + "' wurde nicht gefunden", 1);
		}
	}

	public LoadProperties(String propFileName, String propTitle, String filePath) {
		this(propFileName, propTitle);
		try {
			file = new File(filePath + propFileName);
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ClientException(e, "Fehler beim Laden der Datei", "Der Wert mit der Bezeichung '" + propTitle
					+ "' in der Datei '" + propFileName + "' im Pfad '" + filePath + "' wurde nicht gefunden", 1);
		}
	}

	public String getPropertyStringVal() {
		try {
			prop = new Properties();
			prop.load(bis);
			propVal = prop.getProperty(propTitle);
		} catch (IOException e) {
			throw new ClientException(e, "Ein/Ausgabe Fehler", "Es ist ein Fehler beim Lesen der Datei aufgetreten", 2);
		}
		return propVal;
	}

	public int getPropertyIntVal() {
		try {
			prop = new Properties();
			prop.load(bis);
			propVal = prop.getProperty(propTitle);
			propIntVal = Integer.parseInt(propVal);
		} catch (IOException e) {
			throw new ClientException(e, "Ein/Ausgabe Fehler", "Es ist ein Fehler beim Lesen der Datei aufgetreten", 2);
		}
		return propIntVal;
	}

}
