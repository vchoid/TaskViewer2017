package de.gokv.client.taskviewer.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FilterCriteriaPanelDateContr extends AbstractFilterCriteriaController implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		setFilteredTask();
		frame.repaint();
	}

}
