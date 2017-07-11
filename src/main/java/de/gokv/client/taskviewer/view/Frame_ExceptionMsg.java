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

import de.gokv.client.taskviewer.controller.FrameException_Controller;
import net.miginfocom.swing.MigLayout;

public class Frame_ExceptionMsg extends JDialog {

	private JPanel contentPane;
	public  JPanel topPane;
	public static JPanel midPane;
	private JPanel bottomPane;
	
	private String msg;

	public static Pattern_Button details;

	private JLabel msgLabel;
	private JLabel descrLabel;

	private Icon icon = UIManager.getIcon("OptionPane.errorIcon");
	private final Font fontTitle = new Font("SansSerif", Font.PLAIN, 20);
	
	private FrameException_Controller btnCont = new FrameException_Controller();
	
	private int fehlercode;
	
	private static Throwable e;

	public Frame_ExceptionMsg() {
	}
	
	public Frame_ExceptionMsg(Throwable e) {
		this.e = e;
		BorderLayout bLayoutCONTENT = new BorderLayout();
		MigLayout mlayoutTOP = new MigLayout("","[][grow][]", "[]");
		MigLayout mlayoutMIDDLE = new MigLayout("","[][grow][]", "[]");
		MigLayout mlayoutBTTM = new MigLayout("","[][grow][]", "[]");

		// Content Pane ++++++++++++++++++++++++++++++++++++++++
		contentPane = new JPanel();
		contentPane.setLayout(bLayoutCONTENT);
		
		// Top Pane ++++++++++++++++++++++++++++++++++++++++++++
		topPane = new JPanel();
		topPane.setBackground(Color.WHITE);
		topPane.setLayout(mlayoutTOP);
		msg = e.getMessage();
		msgLabel = new JLabel(msg);
		msgLabel.setIcon(icon);
		msgLabel.setFont(fontTitle);
		descrLabel = new JLabel("Hier eine kleine Beschreibung zum Fehler in einfachen Worten");
		//------------------------------------------------------
		topPane.add(msgLabel, "wrap");
		topPane.add(descrLabel, "wrap");
		// Middle Pane +++++++++++++++++++++++++++++++++++++++++
		midPane = new JPanel();
		midPane.setLayout(mlayoutMIDDLE);
		//------------------------------------------------------
//		midPane.add(getStackTraceAsScrollPane(), "growx, pushx");
		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setLayout(mlayoutBTTM);
		details = new Pattern_Button("Details anzeigen");
		details.addActionListener(btnCont);
		// ------------------------------------------------------
		bottomPane.add(details, "flowy");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(topPane, BorderLayout.PAGE_START);
		contentPane.add(midPane, BorderLayout.CENTER);
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
	public static JScrollPane getStackTraceAsScrollPane() {
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText(Strings.stackStraceAsString(e));
		JScrollPane scroller = new JScrollPane(text);
		return scroller;
	}

}
