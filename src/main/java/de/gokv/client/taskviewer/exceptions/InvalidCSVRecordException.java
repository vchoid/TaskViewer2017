package de.gokv.client.taskviewer.exceptions;

/**
 * 
 * Wird geworfen, wenn die Werte aus der CSV-Datei fehlerhaft sind.
 * 
 * <p>
 * <b>Kontruktor:</b>
 * <ul>
 * <li><b>{@link #InvalidCSVRecordException(String, long)}</b>: Gibt den ein Fehler,
 * an der genauen Stelle (Zeile und Spalte) der CSV zur�ck.</li>
 * <li><b>{@link #InvalidCSVRecordException(Throwable, long)}</b>: Gibt den genauen Fehler,
 * in der Zeile der CSV zur�ck.</li>
 * <li><b>{@link #InvalidCSVRecordException(String, long, String, Object)}</b>: Gibt den genauen Fehler,
 * an der genauen Stelle (Zeile und Spalte) der CSV zur�ck.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 */

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	public final static int ERROR_CODE = 102;
	
	private static String message;

	/**
	 * Gibt den ein Fehler, an der genauen Stelle (Zeile und Spalte) der CSV zur�ck.
	 * 
	 * @param colName	Spaltename
	 * @param line		Zeilenangabe
	 */
	public InvalidCSVRecordException(String colName, long line) {
		this(colName, line, "Ung�ltiger Spalteninhalt");
	}
	/**
	 * Gibt den genauen Fehler, in der Zeile der CSV zur�ck.
	 * 
	 * @param e		geworfene Exception
	 * @param line	Zeilenangabe
	 */
	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		InvalidCSVRecordException.message = String.format("In Zeile %s, %s \'%s\'", line, System.lineSeparator() , e.getMessage()) + "(Fehlercode: " + ERROR_CODE + ")";
	}
	/**
	 * Gibt den genauen Fehler, an der genauen Stelle (Zeile und Spalte) der CSV zur�ck.
	 * 
	 * @param colName	Spaltenname
	 * @param line		Zeilenangabe
	 * @param reason	Grund
	 * @param objects	fehlerhaftes Objekt
	 */
	public InvalidCSVRecordException(String colName, long line, String reason,Object...objects) {
		super();
		reason = String.format(reason, objects);
		InvalidCSVRecordException.message = String.format("In Zeile %s,  %s in der Spalte \"%s\" %s Grund: %s", line, System.lineSeparator(), colName, System.lineSeparator(), reason) + "(Fehlercode: " + ERROR_CODE + ")";
	}

	@Override
	public String getMessage() {
		return message;
	}

}
