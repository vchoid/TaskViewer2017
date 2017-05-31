package de.gokv.client.reader;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import exceptions.InvalidCSVRecordException;
import exceptions.InvalidDateException;

/**
 * Erzeugt einen Task aus einer validen Zeile einer CSV-Datei.
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 *
 */
public class Task {
	
	// Header der CSV +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

	// Pattern für valide KvNr, TaskId, Namensgebung +++++++++++++++++++++++++++++++++++++++++++
	private final static Pattern PATTERN_KVNR = Pattern.compile("[A-Z]{1}[0-9]{9}");
	private final static Pattern PATTERN_TASKID = Pattern.compile("[A-F0-9]{32}");
	private final static Pattern PATTERN_NONUMB = Pattern.compile("[^0-9]*");
	// private final static Pattern PATTERN_DATE = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");

	private static int countTask = 0;


	/**
	 * Erzeugt ein Task einer Zeile aus einer CSV_Datei und gibt ihn als {@link Task}-Objekt zurück.
	 * 
	 * @param record Inhalt einer Zeile der gelesenen CSV-Datei.
	 * @return Eine Zeile aus der CSV-Datei als {@link Task}-Objekt.
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
			t.name = CSVReader.getValue(record, CSVReader.COL_MITGLIED_NAME, PATTERN_NONUMB, true);
			t.vName = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VORNAME, PATTERN_NONUMB, true);

			t.titel = CSVReader.getValue(record, CSVReader.COL_MITGLIED_TITEL, PATTERN_NONUMB, false);
			t.zsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_ZSWORT, PATTERN_NONUMB, false);
			t.vsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VSWORT, PATTERN_NONUMB, false);

		} catch (InvalidDateException e) {
			throw new InvalidCSVRecordException(e, record.getRecordNumber());
		}

		return t;
	}
	// Konstruktor ++++++++++++++++++++++++++++++++++++++
	public Task() {
		super();
	}
	// Getter +++++++++++++++++++++++++++++++++++++++++++
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
		return "______________________________________  " + ++countTask
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
