package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Pattern_Button extends JButton{
	
	private final Font fontTxt = new Font("Arial", Font.PLAIN, 12);
	private final Border btnBorder = BorderFactory.createEmptyBorder(8, 5, 8, 5);
	
	private final Color colorBtnBG = Color.DARK_GRAY;
	private final Color colorBtnTxt = Color.WHITE;

	
	public Pattern_Button(String title) {
		setText(title);
		setForeground(colorBtnTxt);
		setBackground(colorBtnBG);
		setFont(fontTxt);
		setBorder(btnBorder);
		setContentAreaFilled(true);
		setOpaque(false);
	}

	
}
