package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoadTaskDetailsKeyHandler extends AbstractLoadTaskDetailsController implements KeyListener{

	public LoadTaskDetailsKeyHandler() {
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
