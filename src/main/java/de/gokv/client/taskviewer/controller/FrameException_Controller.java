package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.view.Frame_ExceptionMsg;

public class FrameException_Controller implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Frame_ExceptionMsg.details){
			Frame_ExceptionMsg.midPane.setVisible(true);
			Frame_ExceptionMsg.fExMsg.pack();
			
		} 
		
	}

	

	
	
}
