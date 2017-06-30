package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.Template_TaskList;

public class TaskListController extends AbstractController implements ActionListener {

	public int valEnt;
	public static String valEntMsg = "";
	public int invalEnt;
	public static String invalEntMsg = "";
	private Template_TaskList taskMask;
	
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
		return invalEntMsg = "(+ " + model.countInvalidTasks + " fehlerhaft)";
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
