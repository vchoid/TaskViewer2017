package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.FcsvExceptionDialogFrame;

public class FrameExceptionArrayController implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == FcsvExceptionDialogFrame.fExMsg.expandBtn && !(FcsvExceptionDialogFrame.stackTracePane.isVisible())){
			FcsvExceptionDialogFrame.fExMsg.pack();
			FcsvExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			FcsvExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FcsvExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FcsvExceptionDialogFrame.fExMsg.expandBtn.setIcon(FcsvExceptionDialogFrame.fExMsg.iconLoadScaled);
			FcsvExceptionDialogFrame.fExMsg.expandBtn.setText("Weniger Details");
			FcsvExceptionDialogFrame.stackTracePane.setVisible(true);
			FcsvExceptionDialogFrame.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == FcsvExceptionDialogFrame.fExMsg.expandBtn){
			FcsvExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			FcsvExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FcsvExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FcsvExceptionDialogFrame.fExMsg.expandBtn.setIcon(FcsvExceptionDialogFrame.fExMsg.iconLoadScaled);
			FcsvExceptionDialogFrame.fExMsg.expandBtn.setText("Weitere Details");
			FcsvExceptionDialogFrame.stackTracePane.setVisible(false);
			FcsvExceptionDialogFrame.fExMsg.setSize(600, 149);
			FcsvExceptionDialogFrame.fExMsg.pack();
		}
		
	}

	

	
	
}
