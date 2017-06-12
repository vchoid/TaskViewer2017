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
			filteredTasks.add(task.getTask_id());
		}
		
		return filteredTasks.toArray(new String[0]);
	}

	
	
}
