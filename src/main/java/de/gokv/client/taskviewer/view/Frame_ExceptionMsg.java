package de.gokv.client.taskviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class Frame_ExceptionMsg extends JDialog {

	private JPanel contentPane;
	private JPanel topPane;
	private JPanel bottomPane;

	private Pattern_Button details;
	private Pattern_Button close;

	private int fehlercode;

	public Frame_ExceptionMsg() {

		BorderLayout bLayoutCONTENT = new BorderLayout();
		MigLayout mlayoutTOP = new MigLayout("","[][grow][]", "[]");
		MigLayout mlayoutBTTM = new MigLayout("","[][grow][]", "[]");

		// Content Pane ++++++++++++++++++++++++++++++++++++++++
		contentPane = new JPanel();
		contentPane.setLayout(bLayoutCONTENT);

		// Top Pane ++++++++++++++++++++++++++++++++++++++++++++
		topPane = new JPanel();
		topPane.setBackground(Color.WHITE);

		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setBackground(Color.GRAY);
		bottomPane.setLayout(mlayoutBTTM);
		details = new Pattern_Button("Details anzeigen");
		close = new Pattern_Button("Schlieﬂen");
		// ------------------------------------------------------
		bottomPane.add(details, "flowy");
		bottomPane.add(close, "right");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(topPane, BorderLayout.PAGE_START);
		contentPane.add(bottomPane, BorderLayout.PAGE_END);
		add(contentPane);
		// ------------------------------------------------------
		setTitle("Fehlercode: " + fehlercode);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
