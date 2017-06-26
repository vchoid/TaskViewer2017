package de.gokv.client.taskviewer.model;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.Task;

/**
 * 
 * @author Christoph Kiank
 *
 */
public class MyModel {
	private CSVReader reader;
	public static List<Task> tasks;
	private List<String> filteredTasks;
	private Task filteredCriteria;
	public int countValidTasks;
	public static int countInvalidTasks;
	public String kvnr;
	public String name;
	public String vName;
	public LocalDate gebDate;
	
	public MyModel() {
		tasks = new ArrayList<Task>();
	}

	/**
	 * �ffnet zuerst den Ordner 'orders'. Falls nicht vorhanden wird automatisch
	 * einer erstellt. Lie�t anschlie�end alle CSV Dateien und holt. �ffnet
	 * mittels des CSVReaders die Dateien und holt alle g�ltigen Eintr�ge und
	 * speichert diese dann in einer Liste.
	 */
	public void readFiles() {
		File folder = new File(System.getProperty("user.dir") + "/orders");
		folder.mkdirs();
		countValidTasks = 0;
		for (File file : folder.listFiles()) {
			reader = new CSVReader(file.getAbsolutePath());
			reader.readCSVFile();
			tasks.addAll(reader.getValidEntries());
			countValidTasks += reader.getValidEntries().size();
			countInvalidTasks += reader.getInvalidEntries().size();
			
		}
	}
	public void reload(){
		tasks.clear();
		countInvalidTasks = 0;
		readFiles();
	}
	
	public void getTaskValueByID(String taskID){
		for(Task t : tasks){
			if(taskID.equals(t.getTaskId())){
				kvnr = t.getKvnr();
				name = t.getName();
				vName = t.getvName();
				gebDate = t.getGebDat();
			}
		}
	}
	
	/**
	 * fabian fragen...
	 * @return
	 */
	public String[] getFilteredTaskID() {
		filteredTasks = new ArrayList<String>();
		for (Task task : tasks) {
			// criterias
			if (filteredCriteria != null) {
				if (!task.matchesCriteria(filteredCriteria))
					continue;
			}
			filteredTasks.add(task.getTaskId());
		}

		return filteredTasks.toArray(new String[0]);
	}

	public void setFilterCriteria(Task t) {
		filteredCriteria = t;
	}

	public String getKvnr() {
		return kvnr;
	}
	
	public String getName() {
		return name;
	}
	
	public String getvName() {
		return vName;
	}
	
	public LocalDate getGebDate() {
		return gebDate;
	}
}
