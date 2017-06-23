package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.MyFrame;

public class FilterTaskListKeyHandler implements KeyListener{

	MyFrame frame;
	MyModel model;

	public FilterTaskListKeyHandler(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}

	public void setFilteredTask(){
		try {
			Task t = new Task();

			t.setName(frame.pName.getText());
			t.setvName(frame.pVname.getText());
			t.setKvnr(frame.pKvnr.getText());
			t.setTaskId(frame.pTaskID.getText());
			if (frame.model_geb.getValue() != null) {
				t.setGebDat(DateUtil.parseDate(frame.model_geb.getValue()));
			}
			if (frame.model_ord.getValue() != null) {
				t.setOrderedDate(DateUtil.parseDate(frame.model_ord.getValue()));
			}
			model.setFilterCriteria(t);
			frame.taskList.setListData(model.getFilteredTaskID());
			MyFrame.anzFiltTask.setText(model.getFilteredTaskID().length +" Einträge");

		} catch (InvalidDateException e1) {
			// DO NOTHING
			e1.printStackTrace();
		}
	}
	
	public void clearTasks(){
		// Filter-Panel
		frame.pName.setText(null);
		frame.pVname.setText(null);
		frame.pKvnr.setText(null);
		frame.pTaskID.setText(null);
		frame.model_geb.setValue(null);
		frame.model_ord.setValue(null);
		// CSV Info-Panel
		frame.taskID_field.setText(null);
		frame.kvnr_field.setText(null);
		frame.name_field.setText(null);
		frame.vName_field.setText(null);
		frame.gebDate_field.setText(null);
		// Task Info-Panel
		frame.state_field.setText(null);
		frame.taskType_field.setText(null);
		frame.orderDate_field.setText(null);
		frame.evInProgs_field.setText(null);
		frame.evCompl_field.setText(null);
		frame.evReceived_field.setText(null);
		frame.evResult_field.setText(null);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			setFilteredTask();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			clearTasks();
			setFilteredTask();
			MyFrame.anzFiltTask.setText("0 Einträge");
		}
			}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
