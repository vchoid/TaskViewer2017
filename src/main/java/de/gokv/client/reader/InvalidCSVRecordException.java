package de.gokv.client.reader;

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	private String message;

	public InvalidCSVRecordException(String colName, long line) {
		super();
		this.message = String.format("Ungültiger Eintrag:%s in Zeile %s,  %s in Spalte \"%s\"", System.lineSeparator(), line, System.lineSeparator(), colName);
	}

	
	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		this.message = String.format("Folgende Meldung trat auf:%s in Zeile %s, %s \'%s\'", System.lineSeparator(), line,System.lineSeparator() , e.getMessage());
	}

	@Override
	public String getMessage() {
		return message;
	}

}
