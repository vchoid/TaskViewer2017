package de.gokv.client.taskviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.sound.midi.spi.MidiDeviceProvider;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.oxbow.swingbits.util.Strings;

import de.gokv.client.taskviewer.controller.FrameException_Controller;
import net.miginfocom.swing.MigLayout;

public class Frame_ExceptionMsg extends JDialog {

	public static JPanel contentPane;
	public static JPanel topPane;
	public static JPanel midPane;
	public static JPanel bottomPane;

	public static String msg;

	public static Pattern_Button details;

	public static JLabel msgLabel;
	public static JLabel descrLabel;

	public static Icon icon = UIManager.getIcon("OptionPane.errorIcon");
	public static final Font FONT_TITLE = new Font("SansSerif", Font.PLAIN, 20);

	public static FrameException_Controller btnCont = new FrameException_Controller();

	public static int fehlercode;
	public static Throwable ex;

	public static Frame_ExceptionMsg fExMsg;
	public Frame_ExceptionMsg() {
	}


	public static void setMessageDialog(Throwable ex){
		fExMsg = new Frame_ExceptionMsg();
		
		fExMsg.ex = ex;
		BorderLayout bLayoutCONTENT = new BorderLayout();
		MigLayout mlayoutTOP = new MigLayout("", "[][grow][]", "[]");
		MigLayout mlayoutMIDDLE = new MigLayout("", "[][grow][]", "[]");
		MigLayout mlayoutBTTM = new MigLayout("", "[][grow][]", "[]");

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
		msgLabel.setFont(FONT_TITLE);
		descrLabel = new JLabel("Hier eine kleine Beschreibung zum Fehler in einfachen Worten");
		// ------------------------------------------------------
		topPane.add(msgLabel, "wrap ");
		topPane.add(descrLabel, "wrap ");
		// Middle Pane +++++++++++++++++++++++++++++++++++++++++
		midPane = new JPanel(new BorderLayout());
		midPane.setBackground(Color.WHITE);
		midPane.setLayout(mlayoutMIDDLE);
		midPane.setVisible(false);
		// ------------------------------------------------------
		midPane.add(getStackTraceAsScrollPane(), "growx, pushx");
		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setLayout(mlayoutBTTM);
		bottomPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		details = new Pattern_Button("Weitere Details");
		details.addActionListener(btnCont);
		// ------------------------------------------------------
		bottomPane.add(details, "flowy");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(topPane, BorderLayout.PAGE_START);
		contentPane.add(midPane, BorderLayout.CENTER);
		contentPane.add(bottomPane, BorderLayout.PAGE_END);
		fExMsg.add(contentPane);
		// ------------------------------------------------------
		fExMsg.setTitle("Fehlercode: " + fehlercode);
		fExMsg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fExMsg.setAlwaysOnTop(true);
		fExMsg.pack();
//		fExMsg.setSize(600, 300);
		fExMsg.setLocationRelativeTo(null);
		fExMsg.setVisible(true);
		
	}
	/**
	 * Holt den StackTrace und gibt ihn als ein JScrollPane zur�ck.
	 * 
	 * @param e
	 * @return
	 */
	public static JScrollPane getStackTraceAsScrollPane() {
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText(Strings.stackStraceAsString(ex));
		JScrollPane scroller = new JScrollPane(text);
		return scroller;
	}

}
