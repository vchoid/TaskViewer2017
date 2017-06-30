package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FilterCriteriaPanel_DateContr extends Abstract_FilterCriteria_Controller implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		setFilteredTask();
	}

}
