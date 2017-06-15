package de.gokv.client.taskviewer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.Task;

/**
 * 
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class MyModel {
	private CSVReader reader;
	private List<Task> tasks;
	private List<String> filteredTasks;
	private Task filteredCriteria;

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

		for (File file : folder.listFiles()) {
			reader = new CSVReader(file.getAbsolutePath());
			reader.readCSVFile();
			tasks.addAll(reader.getValidEntries());
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getFilteredTasks() {
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

}