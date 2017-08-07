package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.FExceptionDialogFrame;

public class FrameExceptionController implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == FExceptionDialogFrame.fExMsg.expandBtn && !(FExceptionDialogFrame.midPane.isVisible())){
			FExceptionDialogFrame.fExMsg.pack();
			FExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			FExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FExceptionDialogFrame.fExMsg.expandBtn.setIcon(FExceptionDialogFrame.fExMsg.iconLoadScaled);
			FExceptionDialogFrame.fExMsg.expandBtn.setText("Weniger Details");
			FExceptionDialogFrame.midPane.setVisible(true);
			FExceptionDialogFrame.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == FExceptionDialogFrame.fExMsg.expandBtn){
			FExceptionDialogFrame.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			FExceptionDialogFrame.fExMsg.iconLoadScaled = new ImageIcon(FExceptionDialogFrame.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			FExceptionDialogFrame.fExMsg.expandBtn.setIcon(FExceptionDialogFrame.fExMsg.iconLoadScaled);
			FExceptionDialogFrame.fExMsg.expandBtn.setText("Weitere Details");
			FExceptionDialogFrame.midPane.setVisible(false);
			FExceptionDialogFrame.fExMsg.setSize(600, 149);
			FExceptionDialogFrame.fExMsg.pack();
		}
		
	}

	

	
	
}
