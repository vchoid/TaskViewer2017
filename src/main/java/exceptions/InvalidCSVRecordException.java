package exceptions;

public class InvalidCSVRecordException extends Throwable {

	private static final long serialVersionUID = -887930394449075849L;

	private String message;

	public InvalidCSVRecordException(String colName, long line) {
		this(colName, line, "Undefinierter Grund");
	}

	public InvalidCSVRecordException(Throwable e, long line) {
		super();
		this.message = String.format("In Zeile %s, %s \'%s\'", line,System.lineSeparator() , e.getMessage());
	}

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
