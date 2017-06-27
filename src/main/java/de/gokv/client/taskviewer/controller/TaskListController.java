package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.TaskListTemplate;

public class TaskListController extends AbstractController implements ActionListener {

	public int valEnt;
	public static String valEntMsg = "";
	public int invalEnt;
	public static String invalEntMsg = "";
	private TaskListTemplate taskMask;
	
	public TaskListController() {
		super();
		taskMask = frame.taskMask;
	}

	public void countvalidEntries(){
		if (model.countInvalidTasks > 0) {
			getInvalidEntries();
		} else {
			invalEntMsg = "";
		}
		if (model.countValidTasks > 0) {
			getValidEntries();
		} else {
			valEntMsg = "";
		}
		taskMask.anzTasks.setText(valEntMsg + invalEntMsg);
		
	}
	
	public String getInvalidEntries(){
		return invalEntMsg = "(davon fehlerhaft: " + model.countInvalidTasks + ")";
	}
	
	public String getValidEntries(){
		return valEntMsg = model.countValidTasks + " Einträge ";
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == taskMask.reloadBtn) {
			model.reload();
			taskMask.taskList.setListData(model.getFilteredTaskID());
			
		}
	}
}
