package de.gokv.client.taskviewer.exceptions;

/**
 * 
 * Wird geworfen, wenn die Werte aus der CSV-Datei fehlerhaft sind.
 * 
 * <p>
 * <b>Kontruktor:</b>
 * <ul>
 * <li><b>{@link #InvalidCSVRecordException(String, long)}</b>: Gibt den ein
 * Fehler, an der genauen Stelle (Zeile und Spalte) der CSV zur�ck.</li>
 * <li><b>{@link #InvalidCSVRecordException(Throwable, long)}</b>: Gibt den
 * genauen Fehler, in der Zeile der CSV zur�ck.</li>
 * <li><b>{@link #InvalidCSVRecordException(String, long, String, Object)}</b>:
 * Gibt den genauen Fehler, an der genauen Stelle (Zeile und Spalte) der CSV
 * zur�ck.</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 */

public class InvalidCSVRecordException extends AbstractException {

	private static final long serialVersionUID = -887930394449075849L;

	private static String message;

	public InvalidCSVRecordException(Throwable cause, String title, String message, int code) {
		super(cause, title, message, code);
		setErrChildCode(300);
	}

	/**
	 * Gibt den ein Fehler, an der genauen Stelle (Zeile und Spalte) der CSV
	 * zur�ck.
	 * 
	 * @param colName
	 *            Spaltename
	 * @param line
	 *            Zeilenangabe
	 */
	public InvalidCSVRecordException(String colName, long line) {
		this(colName, line, "-Ung�ltiger oder leerer Spalteninhalt-%s", System.lineSeparator());
	}

	/**
	 * Gibt den genauen Fehler, in der Zeile der CSV zur�ck.
	 * 
	 * @param e
	 *            geworfene Exception
	 * @param line
	 *            Zeilenangabe
	 */
	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		InvalidCSVRecordException.message = String.format("In der Zeile %s, %s \'%s\'", line, System.lineSeparator(),
				e.getMessage());
	}

	/**
	 * Gibt den genauen Fehler, an der genauen Stelle (Zeile und Spalte) der CSV
	 * zur�ck.
	 * 
	 * @param colName
	 *            Spaltenname
	 * @param line
	 *            Zeilenangabe
	 * @param reason
	 *            Grund
	 * @param objects
	 *            fehlerhaftes Objekt
	 */
	public InvalidCSVRecordException(String colName, long line, String reason, Object... objects) {
		super();
		reason = String.format(reason, objects);
		InvalidCSVRecordException.message = String.format("In der Zeile %s,  %s in der Spalte \"%s\" %s Grund: %s",
				line, System.lineSeparator(), colName, System.lineSeparator(), reason);
	}

	@Override
	public String getMessage() {
		return message + System.lineSeparator();
	}

}
