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

	public void getAllEntries(){
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
		setAllEntries();
	}
	public void setAllEntries(){
		taskMask.anzTasks.setText(valEntMsg + invalEntMsg);
	}
	
	public static String getInvalidEntries(){
		return invalEntMsg = "(davon fehlerhaft: " + model.countInvalidTasks + ")";
	}
	
	public static String getValidEntries(){
		if(model.countValidTasks == 1){
			return valEntMsg = model.countValidTasks + " Eintrag ";
		} else {
			return valEntMsg = model.countValidTasks + " Einträge ";
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == taskMask.reloadBtn) {
			model.reload();
			getAllEntries();
			taskMask.taskList.setListData(model.getFilteredTaskID());
			
		}
	}
}
