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

				t.setName(MyFrame.pName.getText());
				t.setvName(MyFrame.pVname.getText());
				t.setKvnr(MyFrame.pKvnr.getText());
				t.setTaskId(MyFrame.pTaskID.getText());

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
