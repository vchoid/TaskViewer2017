package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;

public class TaskListPanel_KeyContr extends Abstract_TaskListPanel_Controller implements KeyListener{

	public TaskListPanel_KeyContr() throws ClientConfigurationExeception {
		super();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && taskMask.taskList.isSelectedIndex(taskMask.taskList.getSelectedIndex())) {
			loadTaskDetails();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ){
			selectTaskItem();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	

}
