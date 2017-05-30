package Exceptions;

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	private String message;

	public InvalidCSVRecordException(String colName, long line) {
		super();
		this.message = String.format("In Zeile %s,  %s in der Spalte \"%s\"", line, System.lineSeparator(), colName);
	}

	
	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		this.message = String.format("In Zeile %s, %s \'%s\'", line,System.lineSeparator() , e.getMessage());
	}

	@Override
	public String getMessage() {
		return message;
	}

}
