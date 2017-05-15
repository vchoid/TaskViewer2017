package de.gokv.client.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class testMe {

	
	public static void main(String[] args) {
		BufferedReader file = null;
		String zeile;
		try {
			file = new BufferedReader(new FileReader(new File("C:/java/workspaces/BitgoKV/gokv-client-main/test-output/famv_direct_input_monitoring_20170515131131.csv")));
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
			e.printStackTrace();
		}
		System.out.println(file);
	}

}
