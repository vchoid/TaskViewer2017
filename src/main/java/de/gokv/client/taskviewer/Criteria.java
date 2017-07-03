package de.gokv.client.taskviewer;

import java.time.LocalDate;

public class Criteria {

	private String kvnr;
	private String nName;
	private String vName;
	private String taskID;
	private String gebDate;

	public Criteria() {

	}

	public boolean matchesTask(Task task) {

		if (this.kvnr.length() > 0) {
			if (!task.getKvnr().matches(kvnr)) {
				return false;
			}
		}

		if (this.nName.length() > 0) {
			if (!task.getName().matches(nName)) {
				System.out.println(nName);
				return false;
			}
		}

		if (this.vName.length() > 0) {
			if (!task.getvName().matches(vName)) {
				return false;
			}
		}

		if (this.taskID.length() > 0) {
			System.out.println(taskID);
			if (!task.getTaskId().matches(taskID)) {
				return false;
			}
		}

		return true;
	}

	private String formatCriteria(String criteria) {
		criteria = criteria.replaceAll("\\*", ".*");
		return criteria;
	}

	public String getKvnr() {
		return kvnr;
	}

	public void setKvnr(String kvnr) {
		this.kvnr = formatCriteria(kvnr);
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = formatCriteria(nName);
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = formatCriteria(vName);
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = formatCriteria(taskID);
	}

}
