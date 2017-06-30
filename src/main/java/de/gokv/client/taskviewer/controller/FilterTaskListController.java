package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.Template_FilterCriteriaPanel;

public class FilterTaskListController extends AbstractFilterTaskListController implements ActionListener {

	
	
	public FilterTaskListController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Template_FilterCriteriaPanel criteria = frame.filterMask;
		if (e.getSource() == criteria.filterBtn) {
			setFilteredTask();
		} else if (e.getSource() == criteria.clearAllBtn) {
			resetForm();
			setFilteredTask();
			frame.filterMask.anzFiltTask.setText(null);
			
			
			
		}
	}
}
