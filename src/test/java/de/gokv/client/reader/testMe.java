package de.gokv.client.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testMe {
	static int j = 1;

	public static void main(String[] args) throws IOException {

		String zeile = "";
		String titel[];
		BufferedReader datei = null;

		try {

			datei = new BufferedReader(new FileReader(new File(
					"C:/java/workspaces/BitgoKV/gokv-client-main/test-output/famv_direct_input_monitoring_20170515131131.csv")));
			// die Titelzeile in extra Array speichern, damit die Werte aus den
			// Zeile darunter zugeordnet werden können.
			titel = datei.readLine().split(";");
			while (null != (zeile = datei.readLine())) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Zeile " + j++ + " <<<<<<<<<<<<<<<<<<<<<<<<");
				String[] split = zeile.split(";");
				for (int i = 0; i < split.length; i++) {
					System.out.println(titel[i] + ": " + split[i]);
				}
				System.out.println("-----------------------------------");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
			e.printStackTrace();
		}

	}

}
