package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.MyFrame;

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

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				Task t = new Task();

				t.setName(frame.pName.getText());
				t.setvName(frame.pVname.getText());
				t.setKvnr(frame.pKvnr.getText());
				t.setTaskId(frame.pTaskID.getText());

				if (frame.model_geb.isSelected()) {
					t.setGebDat(DateUtil.parseDate(frame.model_geb.getValue()));
				}

				if (frame.model_ord.isSelected()) {
					t.setOrderedDate(DateUtil.parseDate(frame.model_ord.getValue()));
				}

				model.setFilterCriteria(t);
				frame.taskList.setListData(model.getFilteredTasks());

			} catch (InvalidDateException e1) {
				// DO NOTHING
				e1.printStackTrace();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			frame.pName.setText(null);
			frame.pVname.setText(null);
			frame.pKvnr.setText(null);
			frame.pTaskID.setText(null);
			frame.model_geb.setValue(null);
			frame.model_ord.setValue(null);
			
			try {
				Task t = new Task();

				t.setName(frame.pName.getText());
				t.setvName(frame.pVname.getText());
				t.setKvnr(frame.pKvnr.getText());
				t.setTaskId(frame.pTaskID.getText());

				if (frame.model_geb.isSelected()) {
					t.setGebDat(DateUtil.parseDate(frame.model_geb.getValue()));
				}

				if (frame.model_ord.isSelected()) {
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


}
