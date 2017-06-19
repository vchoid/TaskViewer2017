package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.MyFrame;

public class FilterTaskListController implements ActionListener {

	MyFrame frame;
	MyModel model;

	public FilterTaskListController(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MyFrame.filterBtn) {
			try {
				Task t = new Task();

				t.setName(MyFrame.pName.getText());
				t.setvName(MyFrame.pVname.getText());
				t.setKvnr(MyFrame.pKvnr.getText());
				t.setTaskId(MyFrame.pTaskID.getText());
				if (frame.model_geb.getValue() != null) {
					t.setGebDat(DateUtil.parseDate(frame.model_geb.getValue()));
				}
				if (frame.model_ord.getValue() != null) {
					t.setOrderedDate(DateUtil.parseDate(frame.model_ord.getValue()));
				}

				model.setFilterCriteria(t);
				frame.taskList.setListData(model.getFilteredTasks());

			} catch (InvalidDateException e1) {
				// DO NOTHING
				e1.printStackTrace();
			}
		} else if(e.getSource() == MyFrame.clearFieldBtn){
			MyFrame.pName.setText(null);
			MyFrame.pVname.setText(null);
			MyFrame.pKvnr.setText(null);
			MyFrame.pTaskID.setText(null);
			frame.model_geb.setValue(null);
			frame.model_ord.setValue(null);
		}
	}

}
