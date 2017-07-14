package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.Template_FilterCriteriaPanel;

public class FilterCriteriaPanel_ActionContr extends Abstract_FilterCriteria_Controller implements ActionListener {

	
	
	public FilterCriteriaPanel_ActionContr() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Template_FilterCriteriaPanel criteria = frame.filterMask;
		 if (e.getSource() == criteria.clearAllBtn.getButton()) {
			resetForm();
			setFilteredTask();

		}
		setFilteredTask();
		frame.filterMask.anzFiltEntriesTf.setText(null);

	}
}
