package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import de.gokv.client.taskviewer.view.Frame_CSVExceptionDialog;

public class FrameExceptionArray_Controller implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Frame_CSVExceptionDialog.fExMsg.expandBtn && !(Frame_CSVExceptionDialog.stackTracePane.isVisible())){
			Frame_CSVExceptionDialog.fExMsg.pack();
			Frame_CSVExceptionDialog.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandLessIcon_grey.png")).getImage();
			Frame_CSVExceptionDialog.fExMsg.iconLoadScaled = new ImageIcon(Frame_CSVExceptionDialog.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_CSVExceptionDialog.fExMsg.expandBtn.setIcon(Frame_CSVExceptionDialog.fExMsg.iconLoadScaled);
			Frame_CSVExceptionDialog.fExMsg.expandBtn.setText("Weniger Details");
			Frame_CSVExceptionDialog.stackTracePane.setVisible(true);
			Frame_CSVExceptionDialog.fExMsg.setSize(600, 325);
			
			
		} else if(e.getSource() == Frame_CSVExceptionDialog.fExMsg.expandBtn){
			Frame_CSVExceptionDialog.fExMsg.iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
			Frame_CSVExceptionDialog.fExMsg.iconLoadScaled = new ImageIcon(Frame_CSVExceptionDialog.fExMsg.iconLoad.getScaledInstance(12, 12, 0));
			Frame_CSVExceptionDialog.fExMsg.expandBtn.setIcon(Frame_CSVExceptionDialog.fExMsg.iconLoadScaled);
			Frame_CSVExceptionDialog.fExMsg.expandBtn.setText("Weitere Details");
			Frame_CSVExceptionDialog.stackTracePane.setVisible(false);
			Frame_CSVExceptionDialog.fExMsg.setSize(600, 149);
			Frame_CSVExceptionDialog.fExMsg.pack();
		}
		
	}

	

	
	
}
