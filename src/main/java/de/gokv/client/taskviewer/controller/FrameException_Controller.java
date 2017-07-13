package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.Frame_ExceptionMsg;

public class FrameException_Controller implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Frame_ExceptionMsg.fExMsg.expandBtn && !(Frame_ExceptionMsg.midPane.isVisible())){
			Frame_ExceptionMsg.fExMsg.pack();
			Frame_ExceptionMsg.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			Frame_ExceptionMsg.fExMsg.iconLoadScaled = new ImageIcon(Frame_ExceptionMsg.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_ExceptionMsg.fExMsg.expandBtn.setIcon(Frame_ExceptionMsg.fExMsg.iconLoadScaled);
			Frame_ExceptionMsg.fExMsg.expandBtn.setText("Weniger Details");
			Frame_ExceptionMsg.midPane.setVisible(true);
			Frame_ExceptionMsg.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == Frame_ExceptionMsg.fExMsg.expandBtn){
			Frame_ExceptionMsg.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			Frame_ExceptionMsg.fExMsg.iconLoadScaled = new ImageIcon(Frame_ExceptionMsg.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_ExceptionMsg.fExMsg.expandBtn.setIcon(Frame_ExceptionMsg.fExMsg.iconLoadScaled);
			Frame_ExceptionMsg.fExMsg.expandBtn.setText("Weitere Details");
			Frame_ExceptionMsg.midPane.setVisible(false);
			Frame_ExceptionMsg.fExMsg.setSize(600, 149);
			Frame_ExceptionMsg.fExMsg.pack();
		}
		
	}

	

	
	
}
