package de.gokv.client.taskviewer.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	Color[] colors = {Color.gray,Color.green, Color.orange};
	
	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		
		setSize(new Dimension(640, 480));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
