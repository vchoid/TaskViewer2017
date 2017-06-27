package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
