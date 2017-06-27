package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;

import de.gokv.client.taskviewer.utils.HexaToRGB;

public class InfoField {

	
	
	private Font fntTitle = new Font("Arial", Font.BOLD, 14);
	private Font fntTxt = new Font("Arial", Font.PLAIN, 15);
	
	private Color colorInfoTitleTxt = new HexaToRGB("color.info.titel.text").parseHexToRGB();
	private Color colorInfoTxt = new HexaToRGB("color.info.text").parseHexToRGB();
	
	private JLabel lTitle;
	private JLabel lField;
	private GridBagConstraints gbc_lTitle;
	private GridBagConstraints gbc_lField;
	

	public InfoField() {
		super();
	}

	public void createInfoField(String title, int startPosCol, int startPosRow){
		lTitle = new JLabel(title);
		lTitle.setFont(fntTitle);
		lTitle.setForeground(colorInfoTitleTxt);
		gbc_lTitle = new GridBagConstraints();
		gbc_lTitle.anchor = GridBagConstraints.NORTH;
		gbc_lTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lTitle.insets = new Insets(20, 0, 0, 0);
		gbc_lTitle.gridx = startPosCol;
		gbc_lTitle.gridy = startPosRow;
		
		lField = new JLabel();
		lField.setFont(fntTxt);
		lField.setForeground(colorInfoTxt);
		gbc_lField = new GridBagConstraints();
		gbc_lField.anchor = GridBagConstraints.NORTH;
		gbc_lField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lField.insets = new Insets(0, 20, 0, 0);
		gbc_lField.gridx = startPosCol;
		gbc_lField.gridy = startPosRow+1;
				
		
	}
	
	
	public JLabel getlTitle() {
		return lTitle;
	}


	public GridBagConstraints getGbc_lTitle() {
		return gbc_lTitle;
	}


	public GridBagConstraints getGbc_lField() {
		return gbc_lField;
	}


	public JLabel getlField() {
		return lField;
	}
	
	
	public void setlField(String lField) {
	this.lField.setText(lField);
	}
	

	
	
}
