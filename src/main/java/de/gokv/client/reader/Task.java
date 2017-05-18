package de.gokv.client.reader;

import org.apache.commons.csv.CSVRecord;

public class Task {

	private String taskId;
	private String taskType;
	private String orderedDate;
	private String kvnr;
	private String name;
	private String vName;
	private String titel;
	private String zsWort;
	private String vsWort;
	private String gebDat;

	private static int anzTask = 0;

	public static Task createTaskFromRecord(CSVRecord record) throws InvalidCSVRecordException {
		Task t = new Task();

		try {
			t.taskId = record.get(CSVReader.COL_TASK_ID);
			t.taskType = record.get(CSVReader.COL_TASK_TYPE);
			t.orderedDate = record.get(CSVReader.COL_ORDERED_DATE);
			t.kvnr = record.get(CSVReader.COL_MITGLIED_KVNR);
			t.name = record.get(CSVReader.COL_MITGLIED_NAME);
			t.vName = record.get(CSVReader.COL_MITGLIED_VORNAME);
			t.titel = record.get(CSVReader.COL_MITGLIED_TITEL);
			t.zsWort = record.get(CSVReader.COL_MITGLIED_ZSWORT);
			t.vsWort = record.get(CSVReader.COL_MITGLIED_VSWORT);
			t.gebDat = record.get(CSVReader.COL_MITGLIED_GEB_DAT);
		} catch (IllegalArgumentException e) {
			throw new InvalidCSVRecordException(e, record.getRecordNumber());
		}

		// �berpr�fung ob Pflichfelder gef�llt sind, wenn nicht Exception werfen
		// ++++++++++++++++++++++++++++
		if (t.taskId.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_ID, record.getRecordNumber());
		}
		if (t.taskType.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_TASK_TYPE, record.getRecordNumber());
		}
		if (t.orderedDate.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_ORDERED_DATE, record.getRecordNumber());
		}
		if (t.kvnr.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_KVNR, record.getRecordNumber());
		}
		if (t.name.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_NAME, record.getRecordNumber());
		}
		if (t.vName.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_VORNAME, record.getRecordNumber());
		}
		if (t.gebDat.isEmpty()) {

			throw new InvalidCSVRecordException(CSVReader.COL_MITGLIED_GEB_DAT, record.getRecordNumber());
		}

		return t;
	}

	private Task() {
		super();
	}

	public Task(String taskId, String taskType, String orderedDate, String kvnr, String name, String vName,
			String titel, String zsWort, String vsWort, String gebDat) {
		this();
		this.taskId = taskId;
		this.taskType = taskType;
		this.orderedDate = orderedDate;
		this.kvnr = kvnr;
		this.name = name;
		this.vName = vName;
		this.titel = titel;
		this.zsWort = zsWort;
		this.vsWort = vsWort;
		this.gebDat = gebDat;
	}

	public String getTask_id() {
		return taskId;
	}

	public void setTask_id(String task_id) {
		this.taskId = task_id;
	}

	public String getTasktype() {
		return taskType;
	}

	public void setTasktype(String tasktype) {
		this.taskType = tasktype;
	}

	public String getOrdered_date() {
		return orderedDate;
	}

	public void setOrdered_date(String ordered_date) {
		this.orderedDate = ordered_date;
	}

	public String getKvnr() {
		return kvnr;
	}

	public void setKvnr(String kvnr) {
		this.kvnr = kvnr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vName;
	}

	public void setVorname(String vorname) {
		this.vName = vorname;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZsWort() {
		return zsWort;
	}

	public void setZsWort(String zsWort) {
		this.zsWort = zsWort;
	}

	public String getVsWort() {
		return vsWort;
	}

	public void setVsWort(String vsWort) {
		this.vsWort = vsWort;
	}

	public String getGeb_dat() {
		return gebDat;
	}

	public void setGeb_dat(String geb_dat) {
		this.gebDat = geb_dat;
	}

	@Override
	public String toString() {
		return "\n" + ++anzTask + ". Eintrag  -------------------------------------------------------------------\n"
				+ "TaskId: " + taskId + " [Typ: " + taskType + "] Datum: " + orderedDate + "\nKVNr.: " + kvnr + "\n"
				+ "Name: "+ titel + name + ", " + vName + "\ngeboren am: " + gebDat + "\nzsWort: " + zsWort
				+ "\nvsWort: " + vsWort;
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
