package de.gokv.client.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testMe {
	static int j=0;

	public static void main(String[] args) throws IOException {
		BufferedReader datei = null;
		ReadCSVDir ordnerListe = new ReadCSVDir();
			
		try {
			ordnerListe.datei();
			datei = new BufferedReader(new FileReader(new File("C:/java/workspaces/BitgoKV/gokv-client-main/test-output/" + ordnerListe.datei)));
			String zeile = "";
			while (null !=(zeile=datei.readLine())) {
				System.out.println(">>> Zeile " + j++ +" <<<");
				String[] split = zeile.split(";");
				for (int i = 0; i < split.length; i++) {
					System.out.println(split[i]);
				}
				System.out.println("-----------------------------------");
			}
						
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
			e.printStackTrace();
		}
		
		
			
		}
		
	}


