package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import de.gokv.client.taskviewer.controller.FilterCriteriaPanelActionContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanelKeyContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanelMouseContr;
import de.gokv.client.taskviewer.utils.HexaToRGB;

/**
 * 
 * @author Christoph Kiank
 *
 */
public class PPlaceholderTextFieldPattern extends JTextField {

	 
	private static final long serialVersionUID = 1L;

	private static final Color COLOR_PLACEHOLDER_TEXT = new HexaToRGB("color.placeholder.text").parseHexToRGB();
	private static final Font FONT_TEXT = new Font("Arial", Font.PLAIN, 15);
	private final Border emptyBorder = BorderFactory.createEmptyBorder();
	
	private static FilterCriteriaPanelActionContr filtTaskContr = new FilterCriteriaPanelActionContr();
	private static FilterCriteriaPanelKeyContr filtTaskKeyContr = new FilterCriteriaPanelKeyContr();
	private static FilterCriteriaPanelMouseContr filtTaskMouseContr = new FilterCriteriaPanelMouseContr();
	
	
	private Graphics2D g;
	private String placeholderText;
	private int textWidth;
	private GridBagConstraints gbc_placeholder;

	public PPlaceholderTextFieldPattern() {
	}

	public PPlaceholderTextFieldPattern(String placeholderText, int textWidth, int startPosColumn, int startPosRow, int gridWidth) {
		this.placeholderText = placeholderText;
		this.textWidth = textWidth;
		this.setBorder(emptyBorder);
		this.addActionListener(filtTaskContr);
		this.addKeyListener(filtTaskKeyContr);
		this.addMouseListener(filtTaskMouseContr);
		gbc_placeholder = new GridBagConstraints();
		gbc_placeholder.anchor = GridBagConstraints.NORTH;
		gbc_placeholder.fill = GridBagConstraints.HORIZONTAL;
		gbc_placeholder.gridx = startPosColumn;
		gbc_placeholder.gridy = startPosRow;
		gbc_placeholder.gridwidth = gridWidth;
	}
	
	
	public GridBagConstraints getGbc_placeholder() {
		return gbc_placeholder;
	}

	@Override
	protected void paintComponent(Graphics pG) {
		super.paintComponent(pG);
		if (placeholderText.length() == 0 || getText().length() > 0) {
			return;
		}
		setSize(textWidth, 23);
		setFont(FONT_TEXT);
		g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(COLOR_PLACEHOLDER_TEXT);
		g.drawString(placeholderText, getInsets().left + 3, pG.getFontMetrics().getMaxAscent() + 3 + getInsets().top);
	}
	public void setPlaceholder(String placholderText) {
		placeholderText = placholderText;
	}
	public void setPlaceholder(String s, int lenght) {
		this.textWidth = lenght;
		placeholderText = s;
	}
}
