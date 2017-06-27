package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.view.FilterCriteriaTemplate;

public class FilterTaskListKeyHandler extends AbstractFilterTaskListController implements KeyListener {

	
	
	public FilterTaskListKeyHandler() {
		super();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			setFilteredTask();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			resetForm();
			setFilteredTask();
			frame.filterMask.anzFiltTask.setText(null);
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
