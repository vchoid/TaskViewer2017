package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.Frame_ExceptionArrayMsg;

public class FrameExceptionArray_Controller implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Frame_ExceptionArrayMsg.fExMsg.expandBtn && !(Frame_ExceptionArrayMsg.stackTracePane.isVisible())){
			Frame_ExceptionArrayMsg.fExMsg.pack();
			Frame_ExceptionArrayMsg.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			Frame_ExceptionArrayMsg.fExMsg.iconLoadScaled = new ImageIcon(Frame_ExceptionArrayMsg.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_ExceptionArrayMsg.fExMsg.expandBtn.setIcon(Frame_ExceptionArrayMsg.fExMsg.iconLoadScaled);
			Frame_ExceptionArrayMsg.fExMsg.expandBtn.setText("Weniger Details");
			Frame_ExceptionArrayMsg.stackTracePane.setVisible(true);
			Frame_ExceptionArrayMsg.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == Frame_ExceptionArrayMsg.fExMsg.expandBtn){
			Frame_ExceptionArrayMsg.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			Frame_ExceptionArrayMsg.fExMsg.iconLoadScaled = new ImageIcon(Frame_ExceptionArrayMsg.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_ExceptionArrayMsg.fExMsg.expandBtn.setIcon(Frame_ExceptionArrayMsg.fExMsg.iconLoadScaled);
			Frame_ExceptionArrayMsg.fExMsg.expandBtn.setText("Weitere Details");
			Frame_ExceptionArrayMsg.stackTracePane.setVisible(false);
			Frame_ExceptionArrayMsg.fExMsg.setSize(600, 149);
			Frame_ExceptionArrayMsg.fExMsg.pack();
		}
		
	}

	

	
	
}
