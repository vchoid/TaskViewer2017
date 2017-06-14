package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.ui.MyFrame;
import de.gokv.client.taskviewer.utils.DateUtil;

public class FilterTaskKeyHandler implements KeyListener{

	MyFrame frame;
	MyModel model;
	
	public FilterTaskKeyHandler(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				Task t = new Task();
				
				t.setName(MyFrame.pName.getText());
				t.setvName(MyFrame.pVname.getText());
				t.setKvnr(MyFrame.pKvnr.getText());
				t.setTaskId(MyFrame.pTaskID.getText());
				
				if(frame.model_geb.getValue() != null){
					t.setGebDat(DateUtil.parseDate(frame.model_geb.getValue()));
				}
				
				if(frame.model_ord.getValue() != null){
					t.setOrderedDate(DateUtil.parseDate(frame.model_ord.getValue()));
				}
				
				model.setFilterCriteria(t);
				frame.taskList.setListData(model.getFilteredTasks());
				
			} catch (InvalidDateException e1) {
				// DO NOTHING
				e1.printStackTrace();
			}
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
