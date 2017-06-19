package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletionService;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.MyFrame;

public class TaskListController implements ActionListener {

	private final MyFrame frame;
	private MyModel model;
	public int valEnt;
	public static String valEntMsg = "";
	public int invalEnt;
	public static String invalEntMsg = "";

	public TaskListController(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MyFrame.reloadBtn) {
			model.reload();
			if(model.countInvalidTasks > 0){
				invalEntMsg = "(davon fehlerhaft: "+ model.countInvalidTasks + ")";
			} else{
				invalEntMsg = "";
			}
			valEntMsg = model.countValidTasks + " Einträge ";
			MyFrame.anzTasks.setText(valEntMsg + invalEntMsg);
			frame.taskList.setListData(model.getFilteredTasks());
		}
	}
}
