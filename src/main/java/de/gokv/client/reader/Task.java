package de.gokv.client.reader;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import Exceptions.InvalidCSVRecordException;
import Exceptions.InvalidDateException;

public class Task {

	private LocalDate orderedDate;
	private LocalDate gebDat;
	private String taskId;
	private String taskType;
	private String kvnr;
	private String name;
	private String vName;
	private String titel;
	private String zsWort;
	private String vsWort;

	// Pattern für valide KvNr, TaskId, Namensgebung
	private final static Pattern PATTERN_KVNR = Pattern.compile("[A-Z]{1}[0-9]{9}");
	private final static Pattern PATTERN_TASKID = Pattern.compile("[A-F0-9]{32}");
	private final static Pattern PATTERN_ALPHA = Pattern.compile("^[a-zA-Z\\d-\\d \\d.]*");
	// private final static Pattern PATTERN_DATE = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");

	private static int anzTask = 0;

	// TODO JavaDoc schreiben

	/**
	 * 
	 * @param record Aufzeichnung der gelesenen CSV-Datei
	 * @return Eine Zeile aus der CSV-Datei.
	 * @throws InvalidCSVRecordException
	 */
	public static Task createTaskFromRecord(CSVRecord record) throws InvalidCSVRecordException {

		Task t = new Task();

		try {

			t.gebDat = CSVReader.getValueAsDate(record, CSVReader.COL_MITGLIED_GEB_DAT, true);
			t.orderedDate = CSVReader.getValueAsDate(record, CSVReader.COL_ORDERED_DATE, true);
			t.taskId = CSVReader.getValue(record, CSVReader.COL_TASK_ID, PATTERN_TASKID, true);
			t.taskType = CSVReader.getValue(record, CSVReader.COL_TASK_TYPE, true);
			t.kvnr = CSVReader.getValue(record, CSVReader.COL_MITGLIED_KVNR, PATTERN_KVNR, true);
			t.name = CSVReader.getValue(record, CSVReader.COL_MITGLIED_NAME, PATTERN_ALPHA, true);
			t.vName = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VORNAME, PATTERN_ALPHA, true);

			t.titel = CSVReader.getValue(record, CSVReader.COL_MITGLIED_TITEL, PATTERN_ALPHA, false);
			t.zsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_ZSWORT, PATTERN_ALPHA, false);
			t.vsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VSWORT, PATTERN_ALPHA, false);

		} catch (InvalidDateException e) {
			throw new InvalidCSVRecordException(e, record.getRecordNumber());
		}

		return t;
	}

	public Task() {
		super();
	}

	public String getTask_id() {
		return taskId;
	}

	public String getTasktype() {
		return taskType;
	}

	public LocalDate getOrdered_date() {
		return orderedDate;
	}

	public String getKvnr() {
		return kvnr;
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vName;
	}

	public String getTitel() {
		return titel;
	}

	public String getZsWort() {
		return zsWort;
	}

	public String getVsWort() {
		return vsWort;
	}

	public LocalDate getGeb_dat() {
		return gebDat;
	}

	@Override
	public String toString() {
		return "______________________________________  " + ++anzTask
				+ ". Task  ______________________________________\n"
				+ "                                                                                       |\n" + "Id: "
				+ taskId + " [Typ: " + taskType + "] erstellt am: " + orderedDate.getDayOfMonth() + "."
				+ orderedDate.getMonthValue() + "." + orderedDate.getYear() + "\nKVNr.: " + kvnr + "\n" + "Name: "
				+ titel + name + ", " + vName + "\ngeboren am: " + gebDat.getDayOfMonth() + "." + gebDat.getMonthValue()
				+ "." + gebDat.getYear() + "\nzsWort: " + zsWort + "\nvsWort: \n" + vsWort
				+ "_______________________________________________________________________________________|\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kvnr == null) ? 0 : kvnr.hashCode());
		result = prime * result + ((orderedDate == null) ? 0 : orderedDate.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + ((taskType == null) ? 0 : taskType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (kvnr == null) {
			if (other.kvnr != null)
				return false;
		} else if (!kvnr.equals(other.kvnr))
			return false;
		if (orderedDate == null) {
			if (other.orderedDate != null)
				return false;
		} else if (!orderedDate.equals(other.orderedDate))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (taskType == null) {
			if (other.taskType != null)
				return false;
		} else if (!taskType.equals(other.taskType))
			return false;
		return true;
	}

}
