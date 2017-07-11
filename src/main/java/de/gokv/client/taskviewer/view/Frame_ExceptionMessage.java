package de.gokv.client.taskviewer.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.oxbow.swingbits.util.Strings;

public class Frame_ExceptionMessage extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int fehlercode;
	
	private String msg;
	
	private JPanel contentPanel;
	private JPanel msgPanel;
	private JPanel btnPanel;
	
	private JLabel msgLabel;
	private JLabel descrLabel;

	private Icon icon = UIManager.getIcon("OptionPane.errorIcon");
	private final Font fontTitle = new Font("SansSerif", Font.PLAIN, 20);
	
	public GridBagLayout getErrorMsgLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 30, 10 };
		layout.rowHeights = new int[] { 10, 50, 50, 200, 10 };
		return layout;
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
		scroller.setPreferredSize(new Dimension(600, 200));
		return scroller;
	}

	public Frame_ExceptionMessage(Throwable ex) {
		//TODO enum Fehlercode Tabelle
		fehlercode = 101;
		// +++++++++++++++++++++++++++++++++++++++++++++++++++
		setTitle("Fehlercode: " + fehlercode);
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		msgPanel = new JPanel();
		msgPanel.setLayout(getErrorMsgLayout());
		msg = ex.getMessage();
		msgLabel = new JLabel(msg);
		msgLabel.setIcon(icon);
		msgLabel.setFont(fontTitle);
		GridBagConstraints gbc_shortTitle = new GridBagConstraints();
		gbc_shortTitle.anchor = GridBagConstraints.NORTH;
		gbc_shortTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_shortTitle.gridx = 1;
		gbc_shortTitle.gridy = 1;

		descrLabel = new JLabel("Hier eine kleine Beschreibung zum Fehler in einfachen Worten");
		GridBagConstraints gbc_description = new GridBagConstraints();
		gbc_description.anchor = GridBagConstraints.NORTH;
		gbc_description.fill = GridBagConstraints.HORIZONTAL;
		gbc_description.gridwidth = 2;
		gbc_description.gridx = 1;
		gbc_description.gridy = 2;
		
		GridBagConstraints gbc_scrollStackTrace = new GridBagConstraints();
		gbc_scrollStackTrace.anchor = GridBagConstraints.NORTH;
		gbc_scrollStackTrace.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollStackTrace.gridwidth = 2;
		gbc_scrollStackTrace.gridx = 1;
		gbc_scrollStackTrace.gridy = 3;

		msgPanel.add(msgLabel, gbc_shortTitle);
		msgPanel.add(descrLabel, gbc_description);
		msgPanel.add(getStackTraceAsScrollPane(ex), gbc_scrollStackTrace);
		
		contentPanel.add(msgPanel);
		add(contentPanel);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

}
