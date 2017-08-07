package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.TTaskListTemplate;

public class TaskListPanelActionContr extends AbstractMyFrameController implements ActionListener {

	public int valEnt;
	public String valEntMsg = "";
	public int invalEnt;
	public String invalEntMsg = "";
	private TTaskListTemplate taskMask;
	
	public TaskListPanelActionContr() {
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
	
	public String getInvalidEntries(){
		return invalEntMsg = "(+ " + model.countInvalidTasks + " fehlerhaft)";
	}
	
	public String getValidEntries(){
		if(model.countValidTasks == 1){
			return valEntMsg = model.countValidTasks + " Eintrag ";
		} else {
			return valEntMsg = model.countValidTasks + " Einträge ";
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == taskMask.reloadBtn.getButton()) {
			model.reload();
			taskMask.taskList.setListData(model.getFilteredTaskID());
			getAllEntries();
			
		}
	}
}
