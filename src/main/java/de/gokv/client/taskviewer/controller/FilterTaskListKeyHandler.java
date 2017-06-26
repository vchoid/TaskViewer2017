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
			MyFrame.anzFiltTask.setText(model.getFilteredTaskID().length +" Eintr�ge");

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
		MyFrame.taskID_field.setText(null);
		MyFrame.kvnr.setlField(null);
		MyFrame.name.setlField(null);
		MyFrame.vName.setlField(null);
		MyFrame.gebDate.setlField(null);
		// Task Info-Panel
		frame.state.setlField(null);
		frame.taskType.setlField(null);
		frame.orderDate.setlField(null);
		frame.evInProgs.setlField(null);
		frame.evCompl.setlField(null);
		frame.evReceived.setlField(null);
		frame.evResult.setlField(null);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			setFilteredTask();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			clearTasks();
			setFilteredTask();
			MyFrame.anzFiltTask.setText("0 Eintr�ge");
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
