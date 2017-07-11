package de.gokv.client.taskviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.oxbow.swingbits.util.Strings;

import net.miginfocom.swing.MigLayout;

public class Frame_ExceptionMsg extends JDialog {

	private JPanel contentPane;
	private JPanel topPane;
	private JPanel bottomPane;
	
	private String msg;

	private Pattern_Button details;
	private Pattern_Button close;

	private JLabel msgLabel;
	private JLabel descrLabel;

	private Icon icon = UIManager.getIcon("OptionPane.errorIcon");
	private final Font fontTitle = new Font("SansSerif", Font.PLAIN, 20);
	
	private int fehlercode;

	public Frame_ExceptionMsg(Throwable ex) {

		BorderLayout bLayoutCONTENT = new BorderLayout();
		MigLayout mlayoutTOP = new MigLayout("","[][grow][]", "[]");
		MigLayout mlayoutBTTM = new MigLayout("","[][grow][]", "[]");

		// Content Pane ++++++++++++++++++++++++++++++++++++++++
		contentPane = new JPanel();
		contentPane.setLayout(bLayoutCONTENT);
		
		// Top Pane ++++++++++++++++++++++++++++++++++++++++++++
		topPane = new JPanel();
		topPane.setBackground(Color.WHITE);
		topPane.setLayout(mlayoutTOP);
		msg = ex.getMessage();
		msgLabel = new JLabel(msg);
		msgLabel.setIcon(icon);
		msgLabel.setFont(fontTitle);
		descrLabel = new JLabel("Hier eine kleine Beschreibung zum Fehler in einfachen Worten");
		//------------------------------------------------------
		topPane.add(msgLabel, "wrap");
		topPane.add(descrLabel, "wrap");
//		topPane.add(getStackTraceAsScrollPane(ex), "growx, pushx");
		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setBackground(Color.GRAY);
		bottomPane.setLayout(mlayoutBTTM);
		details = new Pattern_Button("Details anzeigen");
		close = new Pattern_Button("Schließen");
		// ------------------------------------------------------
		bottomPane.add(details, "flowy");
		bottomPane.add(close, "right");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(topPane, BorderLayout.CENTER);
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
	
	/**
	 * Holt den StackTrace und gibt ihn als ein JScrollPane zurück.
	 * @param e
	 * @return
	 */
	public JScrollPane getStackTraceAsScrollPane(Throwable e) {
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText(Strings.stackStraceAsString(e));
		JScrollPane scroller = new JScrollPane(text);
		return scroller;
	}

}
