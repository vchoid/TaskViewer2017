package de.gokv.client.reader;

import java.awt.List;
import java.io.File;
import java.util.Arrays;

public class ReadCSVDir {

	String[] dateiListe;
	String datei;
	
	
// Liste der Datein im Ordner.
	public String dateiListe() {
		File dateien = new File("C:/java/workspaces/BitgoKV/gokv-client-main/test-output");
		dateiListe = dateien.list();
		return dateiListe + "\nGesamtanzahl Datein: " + dateiListe.length;
	}
	
	public String datei(){
		dateiListe();
		for (int i = 0; i < dateiListe.length; i++) {
			if(dateiListe[i].startsWith("famv_direct_input_")){
			datei = dateiListe[i];
			
			}
			else{
				datei = "keine CSV Dateien vorhanden!";
			}
		}
		return datei ;
	}

	
	
	public static void main(String[] args) {
		ReadCSVDir dir = new ReadCSVDir();
		System.out.println(dir.datei());
	
	}

}
