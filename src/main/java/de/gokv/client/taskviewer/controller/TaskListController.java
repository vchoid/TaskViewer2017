package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.TaskListTemplate;

public class TaskListController extends AbstractController implements ActionListener {

	public int valEnt;
	public static String valEntMsg = "";
	public int invalEnt;
	public static String invalEntMsg = "";

	public TaskListController() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TaskListTemplate taskMask = frame.taskMask;
		if (e.getSource() == taskMask.reloadBtn) {
			model.reload();
			if (model.countInvalidTasks > 0) {
				invalEntMsg = "(davon fehlerhaft: " + model.countInvalidTasks + ")";
			} else {
				invalEntMsg = "";
			}
			if (model.countValidTasks > 0) {
				valEntMsg = model.countValidTasks + " Einträge ";
			} else {
				valEntMsg = "";
			}
			taskMask.anzTasks.setText(valEntMsg + invalEntMsg);
			taskMask.taskList.setListData(model.getFilteredTaskID());
		}
	}
}
