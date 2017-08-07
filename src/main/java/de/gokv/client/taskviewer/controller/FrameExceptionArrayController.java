package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.FCSVExceptionDialogFrame;

public class FrameExceptionArrayController implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == FCSVExceptionDialogFrame.fExMsg.expandBtn && !(FCSVExceptionDialogFrame.stackTracePane.isVisible())){
			FCSVExceptionDialogFrame.fExMsg.pack();
			FCSVExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			FCSVExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FCSVExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FCSVExceptionDialogFrame.fExMsg.expandBtn.setIcon(FCSVExceptionDialogFrame.fExMsg.iconLoadScaled);
			FCSVExceptionDialogFrame.fExMsg.expandBtn.setText("Weniger Details");
			FCSVExceptionDialogFrame.stackTracePane.setVisible(true);
			FCSVExceptionDialogFrame.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == FCSVExceptionDialogFrame.fExMsg.expandBtn){
			FCSVExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			FCSVExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FCSVExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FCSVExceptionDialogFrame.fExMsg.expandBtn.setIcon(FCSVExceptionDialogFrame.fExMsg.iconLoadScaled);
			FCSVExceptionDialogFrame.fExMsg.expandBtn.setText("Weitere Details");
			FCSVExceptionDialogFrame.stackTracePane.setVisible(false);
			FCSVExceptionDialogFrame.fExMsg.setSize(600, 149);
			FCSVExceptionDialogFrame.fExMsg.pack();
		}
		
	}

	

	
	
}
