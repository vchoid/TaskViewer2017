package de.gokv.client.reader;

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	private String message;

	public InvalidCSVRecordException(String colName, long line) {
		super();
		this.message = String.format("Ungültiger Eintrag in Zeile > %s <, in der Spalte // %s //", line, colName);
	}

	public InvalidCSVRecordException(IllegalArgumentException e, long line) {
		super();
		this.message = String.format("In Zeile %s, trat folgende Meldung auf %s", line, e.getMessage());
	}

	public InvalidCSVRecordException(Throwable e, long recordNumber) {
		super();
		this.message = String.format("Datumsfehler", recordNumber , e.getMessage());
		System.out.println(recordNumber);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
