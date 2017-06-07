package de.gokv.client.taskviewer.TestMe;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GuiTester extends JFrame {
	JTextPane text = new JTextPane();
	DatePickerSample dp;
	
	public GuiTester() {
		setTitle("Gui Tester");
		setSize(new Dimension(800, 800));
		this.getContentPane().setLayout(null);
		text.setBounds(5, 5, 200, 25);
		this.getContentPane().add(text);
		this.getContentPane().getAlignmentX();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GuiTester();
	}

}
