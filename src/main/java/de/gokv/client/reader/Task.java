package de.gokv.client.reader;

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
	
	
	public Task(String taskId, String taskType, String orderedDate, String kvnr, String name, String vName,
			String titel, String zsWort, String vsWort, String gebDat) {
		super();
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
		return "TaskId: " + taskId + " [type=" + taskType + "] Datum: " + orderedDate +"\nKVNr.: "
				+ kvnr + "(" + titel + ")" + "\nName" + name + ", " + vName + "\ngeboren am: " + gebDat + "\n zsWort: " + zsWort
				+ "\nvsWort: " + vsWort;
	}
	
	
	
}
