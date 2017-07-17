package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.commons.lang.ArrayUtils;

public class FilterCriteriaPanel_KeyContr extends Abstract_FilterCriteria_Controller implements KeyListener {

	private final int[] ignoredKeys = {KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_SHIFT};
	
	
	public FilterCriteriaPanel_KeyContr() {
		super();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(ArrayUtils.contains(ignoredKeys, keyCode))
			return;
		if (keyCode == KeyEvent.VK_ESCAPE) {
			resetForm();
		}
		setFilteredTask();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
