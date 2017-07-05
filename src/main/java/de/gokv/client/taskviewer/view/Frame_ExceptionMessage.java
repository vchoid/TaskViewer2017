package de.gokv.client.taskviewer.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Frame_ExceptionMessage{

	
	public Frame_ExceptionMessage() {
		JFrame errFrame = new JFrame();
		errFrame.setTitle("Exception");
		errFrame.setAlwaysOnTop(true);
		JTextField field = new JTextField("test");
		
		errFrame.add(field);
		errFrame.setSize(new Dimension(400, 300));
		errFrame.setDefaultCloseOperation(errFrame.DISPOSE_ON_CLOSE);
		errFrame.setVisible(true);
		errFrame.setLocationRelativeTo(null);
		errFrame.setResizable(false);
		
	}
	
	
}
