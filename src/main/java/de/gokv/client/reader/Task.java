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

		// Überprüfung ob Pflichfelder gefüllt sind oder ..

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
		// .. nicht 32 Zeichen lang ist, entspricht nicht dem Hexadezimalformat
		if (StringUtils.isBlank(record.get(CSVReader.COL_TASK_ID)) || !HexaIdUtil.isIdValid(record.get(CSVReader.COL_TASK_ID))) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_ID, record.getRecordNumber());
		}
		if (StringUtils.isBlank(record.get(CSVReader.COL_TASK_TYPE))) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_TYPE, record.getRecordNumber());
		}
		if (StringUtils.isBlank(record.get(CSVReader.COL_MITGLIED_KVNR)) || !KVNrUtil.isKVNrValid(record.get(CSVReader.COL_MITGLIED_KVNR))) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_KVNR, record.getRecordNumber());
		}
		// .. der Nachname Ziffern enthält
		if (StringUtils.isBlank(record.get(CSVReader.COL_MITGLIED_NAME)) || !StringUtils.containsNone(record.get(CSVReader.COL_MITGLIED_NAME), "1234567890")) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_NAME, record.getRecordNumber());
		}
		// .. der Vorname Ziffern enthält
		if (StringUtils.isBlank(record.get(CSVReader.COL_MITGLIED_VORNAME)) || !StringUtils.containsNone(record.get(CSVReader.COL_MITGLIED_VORNAME), "1234567890")) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_VORNAME, record.getRecordNumber());
		}
		
		Task t = new Task();

		try {
			t.gebDat = DateUtil.parseDate(record.get(CSVReader.COL_MITGLIED_GEB_DAT), CSVReader.COL_MITGLIED_GEB_DAT);
			t.orderedDate = DateUtil.parseDate(record.get(CSVReader.COL_ORDERED_DATE), CSVReader.COL_ORDERED_DATE);
			t.taskId = record.get(CSVReader.COL_TASK_ID);
			t.taskType = record.get(CSVReader.COL_TASK_TYPE);
			t.kvnr = record.get(CSVReader.COL_MITGLIED_KVNR);
			t.name = record.get(CSVReader.COL_MITGLIED_NAME);
			t.vName = record.get(CSVReader.COL_MITGLIED_VORNAME);
			t.titel = record.get(CSVReader.COL_MITGLIED_TITEL);
			t.zsWort = record.get(CSVReader.COL_MITGLIED_ZSWORT);
			t.vsWort = record.get(CSVReader.COL_MITGLIED_VSWORT);

		} catch (IllegalArgumentException | InvalidDateException e) {
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
