package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.view.FExceptionDialogFrame;

public class TaskListPanelKeyContr extends AbstractTaskListPanelController implements KeyListener{

	public TaskListPanelKeyContr() throws ClientConfigurationExeception {
		super();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && taskMask.taskList.isSelectedIndex(taskMask.taskList.getSelectedIndex())) {
			try {
				loadTaskDetails();
			} catch (ClientConfigurationExeception e1) {
				FExceptionDialogFrame.showException(e1);
			}
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
