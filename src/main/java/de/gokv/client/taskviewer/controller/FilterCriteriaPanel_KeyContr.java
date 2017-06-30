package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.view.Template_FilterCriteriaPanel;

public class FilterCriteriaPanel_KeyContr extends Abstract_FilterCriteria_Controller implements KeyListener {

	
	
	public FilterCriteriaPanel_KeyContr() {
		super();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			resetForm();
		}
		setFilteredTask();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
