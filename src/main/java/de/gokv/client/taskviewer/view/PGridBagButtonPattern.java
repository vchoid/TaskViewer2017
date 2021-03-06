package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import de.gokv.client.taskviewer.utils.HexaToRGB;

public class PGridBagButtonPattern extends JButton{

	private static final long serialVersionUID = 1L;
	private final Font fontTxt = new Font("Arial", Font.PLAIN, 15);
	private final Border btnBorder = BorderFactory.createEmptyBorder(8, 5, 8, 5);
	
	private final Color colorBtnBG = new HexaToRGB("color.button.background").parseHexToRGB();
	private final Color colorBtnTxt = new HexaToRGB("color.button.text").parseHexToRGB();
	
	
	private JButton button;
	private GridBagConstraints gbc_button;
	
	
	public PGridBagButtonPattern(String text, int startPosCol, int startPosRow, int gridWidth, ActionListener listener) {
		button = new JButton(text);
		button.setBorder(btnBorder);
		button.setBackground(colorBtnBG);
		button.setForeground(colorBtnTxt);
		button.setFont(fontTxt);
		button.addActionListener(listener);
		gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridwidth = gridWidth;
		gbc_button.gridx = startPosCol;
		gbc_button.gridy = startPosRow;
	}
	
	public JButton getButton() {
		return button;
	}
	
	public GridBagConstraints getGbc_button() {
		return gbc_button;
	}
}
