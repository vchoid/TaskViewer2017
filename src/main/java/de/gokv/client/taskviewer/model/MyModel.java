package de.gokv.client.taskviewer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.Task;

public class MyModel {
	private CSVReader reader;
	private List<Task> tasks;
	private List<String> filteredTasks;
	private Task filterCriteria;
	
	public MyModel() {
		tasks = new ArrayList<Task>();
	}

	public void readFiles() {
		File folder = new File(System.getProperty("user.dir") + "/orders");
		folder.mkdirs();

		for (File file : folder.listFiles()) {
			reader = new CSVReader(file.getAbsolutePath());
			reader.readCSVFile();
			tasks.addAll(reader.getValidEntries());
		}
	}

	public String[] getFilteredTasks() {
		filteredTasks = new ArrayList<String>();
		for (Task task : tasks) {
			//TODO criterias
			if(filterCriteria != null){
				if(! task.matchesCriteria(filterCriteria))
					continue;
			}
			filteredTasks.add(task.getTaskId());
		}
		
		return filteredTasks.toArray(new String[0]);
	}

	public void setFilterCriteria(Task t) {
		filterCriteria = t;
	}

	
	
}
