package de.gokv.client.reader;

import java.time.LocalDate;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

public class Task {

	private String taskId;
	private String taskType;
	private LocalDate orderedDate;
	private String kvnr;
	private String name;
	private String vName;
	private String titel;
	private String zsWort;
	private String vsWort;
	private LocalDate gebDat;

	private static int anzTask = 0;

	public static Task createTaskFromRecord(CSVRecord record) throws InvalidCSVRecordException {
		Task t = new Task();

		try {
			t.taskId = record.get(CSVReader.COL_TASK_ID);
			t.taskType = record.get(CSVReader.COL_TASK_TYPE);
			t.orderedDate = DateUtil.parseDate(record.get(CSVReader.COL_ORDERED_DATE));
			t.kvnr = record.get(CSVReader.COL_MITGLIED_KVNR);
			t.name = record.get(CSVReader.COL_MITGLIED_NAME);
			t.vName = record.get(CSVReader.COL_MITGLIED_VORNAME);
			t.titel = record.get(CSVReader.COL_MITGLIED_TITEL);
			t.zsWort = record.get(CSVReader.COL_MITGLIED_ZSWORT);
			t.vsWort = record.get(CSVReader.COL_MITGLIED_VSWORT);
			t.gebDat = DateUtil.parseDate(record.get(CSVReader.COL_MITGLIED_GEB_DAT));

		} catch (IllegalArgumentException | InvalidDateException e) {
			throw new InvalidCSVRecordException(e, record.getRecordNumber());
		}

		// Überprüfung ob Pflichfelder gefüllt sind oder ..

		// .. nicht 32 Zeichen lang ist, entspricht nicht dem Hexadezimalformat
		if (StringUtils.isBlank(t.taskId) || !HexaIdUtil.isIdValid(t.taskId)) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_ID, record.getRecordNumber());
		}
		if (StringUtils.isBlank(t.taskType)) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_TYPE, record.getRecordNumber());
		}
		// .. das Datumsformat nicht stimmt
		if (StringUtils.isBlank(record.get(CSVReader.COL_MITGLIED_GEB_DAT))
				|| !DateUtil.isDateValid(record.get(CSVReader.COL_MITGLIED_GEB_DAT))) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_GEB_DAT, record.getRecordNumber());
		}
		// .. das Datumsformat nicht stimmt
		if (StringUtils.isBlank(record.get(CSVReader.COL_ORDERED_DATE))
				|| !DateUtil.isDateValid(record.get(CSVReader.COL_ORDERED_DATE))) {
			throw new InvalidCSVRecordException(CSVReader.COL_ORDERED_DATE, record.getRecordNumber());
		}
		if (StringUtils.isBlank(t.kvnr) || !KVNrUtil.isKVNrValid(record.get(CSVReader.COL_MITGLIED_KVNR))) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_KVNR, record.getRecordNumber());
		}
		// .. der Nachname Ziffern enthält
		if (StringUtils.isBlank(t.name) || !StringUtils.containsNone(t.name, "1234567890")) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_NAME, record.getRecordNumber());
		}
		// .. der Vorname Ziffern enthält
		if (StringUtils.isBlank(t.vName) || !StringUtils.containsNone(t.vName, "1234567890")) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_VORNAME, record.getRecordNumber());
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
		return "______________________________________  " + ++anzTask + ". Task  ______________________________________\n" 
				+ "Id: " + taskId + " [Typ: " + taskType
				+ "] erstellt am: " + orderedDate.getDayOfMonth() + "." + orderedDate.getMonthValue() + "."
				+ orderedDate.getYear() + "        |\nKVNr.: " + kvnr + "\n" + "Name: " + titel + name + ", " + vName
				+ "\ngeboren am: " + gebDat.getDayOfMonth() + "." + gebDat.getMonthValue() + "." + gebDat.getYear()
				+ "\nzsWort: " + zsWort + "\nvsWort: \n" + vsWort
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
