package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

import de.gokv.client.taskviewer.utils.HexaToRGB;

/**
 * 
 * @author Christoph Kiank
 *
 */
public class Pattern_PlaceholderTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Color COLOR_PLACEHOLDER_TEXT = new HexaToRGB("color.placeholder.text").parseHexToRGB();
	private static final Font FONT_TEXT = new Font("Arial", Font.PLAIN, 15);

	private String placeholder;
	private Graphics2D g;
	private int lenght;

	public Pattern_PlaceholderTextField() {
	}

	public Pattern_PlaceholderTextField(String pTitle) {
	}
	@Override
	protected void paintComponent(Graphics pG) {
		super.paintComponent(pG);
		if (placeholder.length() == 0 || getText().length() > 0) {
			return;
		}
		setSize(lenght, 23);
		setFont(FONT_TEXT);
		g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(COLOR_PLACEHOLDER_TEXT);
		g.drawString(placeholder, getInsets().left + 3, pG.getFontMetrics().getMaxAscent() + 3 + getInsets().top);
	}
	public void setPlaceholder(String s) {
		placeholder = s;
	}
	public void setPlaceholder(String s, int lenght) {
		this.lenght = lenght;
		placeholder = s;
	}
}
