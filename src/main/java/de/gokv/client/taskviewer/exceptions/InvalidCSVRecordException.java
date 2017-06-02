package de.gokv.client.taskviewer.exceptions;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

/**
 * 
 * Wird bei fehlerhaften Einträgen in der CSV geworfen.
 * 
 * <p>
 * <b>Kontruktor:</b>
 * <ul>
 * <li><b>{@link #InvalidCSVRecordException(String, long)}</b>: Gibt den ein Fehler,
 * an der genauen Stelle (Zeile und Spalte) der CSV zurück.</li>
 * <li><b>{@link #InvalidCSVRecordException(Throwable, long)}</b>: Gibt den genauen Fehler,
 * in der Zeile der CSV zurück.</li>
 * <li><b>{@link #InvalidCSVRecordException(String, long, String, Object)}</b>: Gibt den genauen Fehler,
 * an der genauen Stelle (Zeile und Spalte) der CSV zurück.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 */

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	private String message;

	/**
	 * Gibt den ein Fehler, an der genauen Stelle (Zeile und Spalte) der CSV zurück.
	 * 
	 * @param colName	Spaltename
	 * @param line		Zeilenangabe
	 */
	public InvalidCSVRecordException(String colName, long line) {
		this(colName, line, "Ungültiger Spalteninhalt");
	}
	/**
	 * Gibt den genauen Fehler, in der Zeile der CSV zurück.
	 * 
	 * @param e		geworfene Exception
	 * @param line	Zeilenangabe
	 */
	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		this.message = String.format("In Zeile %s, %s \'%s\'", line, System.lineSeparator() , e.getMessage());
	}
	/**
	 * Gibt den genauen Fehler, an der genauen Stelle (Zeile und Spalte) der CSV zurück.
	 * 
	 * @param colName	Spaltenname
	 * @param line		Zeilenangabe
	 * @param reason	Grund
	 * @param objects	fehlerhaftes Objekt
	 */
	public InvalidCSVRecordException(String colName, long line, String reason,Object...objects) {
		super();
		reason = String.format(reason, objects);
		this.message = String.format("In Zeile %s,  %s in der Spalte \"%s\" %s Grund: %s", line, System.lineSeparator(), colName, System.lineSeparator(), reason);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
