package de.gokv.client.reader;

public class InvalidCSVRecordException extends Throwable{

	private String colName;
	private long line;
	
	public InvalidCSVRecordException(String colName, long line) {
		super();
		this.colName = colName;
		this.line = line;
	}

	@Override
	public String getMessage() {
		return String.format("Ungültiger Eintrag in Zeile %s: in der Spalte %s", line, colName);
	}
	
}
