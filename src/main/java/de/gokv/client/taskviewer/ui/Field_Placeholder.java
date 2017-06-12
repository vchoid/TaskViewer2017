package de.gokv.client.taskviewer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.JTextField;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Field_Placeholder extends JTextField {

	private Color pan214_C_dis = new Color(211, 143, 190);

	private String placeholder;
	private DatePicker pDatePicker;
	private Stage stage;
	private Graphics2D g;

	public Field_Placeholder() {
	}

	public Field_Placeholder(String pText) {

	}

	@Override
	protected void paintComponent(Graphics pG) {
		super.paintComponent(pG);

		if (placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(pan214_C_dis);
		g.drawString(placeholder, getInsets().left + 3, pG.getFontMetrics().getMaxAscent() + getInsets().top);

	}

	public void setPlaceholder(String s) {
		placeholder = s;
	}

}
