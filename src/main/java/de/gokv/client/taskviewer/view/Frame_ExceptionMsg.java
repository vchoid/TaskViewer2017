package de.gokv.client.taskviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

	public static JPanel contentPane;
	public static JPanel topPane;
	public static JPanel midPane;
	public static JPanel bottomPane;

	public static String msg;

	public Pattern_Button details;

	public static JLabel iconErrorLabel;
	public static JLabel msgLabel;
	public static JTextArea descrLabel;

	public String pathAppIcon;
	public Image iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
	public ImageIcon iconLoadScaled = new ImageIcon(iconLoad.getScaledInstance(12, 12, 0));
	
	public Image imageError = new ImageIcon(getClass().getResource("/errorIcon/errIcon_red.png")).getImage();
	public ImageIcon imageIconErrorScaled = new ImageIcon(imageError.getScaledInstance(25, 25, 0));
	
	
	
	
//	public static Icon icon = UIManager.getIcon("OptionPane.errorIcon");
	public Icon iconError = imageIconErrorScaled;
	public static final Font FONT_TITLE = new Font("SansSerif", Font.PLAIN, 20);
	
	public static FrameException_Controller btnCont = new FrameException_Controller();

	public static int fehlercode;
	public static Throwable ex;

	public static Frame_ExceptionMsg fExMsg;
	public Frame_ExceptionMsg() {
		pathAppIcon = "/appIcon/taskViewerError.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathAppIcon)));
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
		iconErrorLabel = new JLabel();
		iconErrorLabel.setIcon(fExMsg.iconError);
		msgLabel = new JLabel(msg);
		msgLabel.setFont(FONT_TITLE);
		descrLabel = new JTextArea("Hier eine kleine Beschreibung zum Fehler in einfachen Worten und noch mehr und mehr und mehr und noch mehr und mehr und mehr");
		descrLabel.setEditable(false);
		descrLabel.setLayout(mlayoutTOP);
		descrLabel.setLineWrap(true);;
		// ------------------------------------------------------
		topPane.add(iconErrorLabel, "");
		topPane.add(msgLabel, "wrap,span");
		topPane.add(descrLabel, "grow, skip, span");
		// Middle Pane +++++++++++++++++++++++++++++++++++++++++
		midPane = new JPanel(new BorderLayout());
		midPane.setBackground(Color.WHITE);
		midPane.setLayout(mlayoutMIDDLE);
		midPane.setVisible(false);
		// ------------------------------------------------------
		midPane.add(getStackTraceAsScrollPane(), "span, grow, push");
		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setLayout(mlayoutBTTM);
		bottomPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		fExMsg.details = new Pattern_Button("Weitere Details");
		fExMsg.details.addActionListener(btnCont);
		fExMsg.details.setIcon(fExMsg.iconLoadScaled);
		// ------------------------------------------------------
		bottomPane.add(fExMsg.details, "flowy");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(topPane, BorderLayout.PAGE_START);
		contentPane.add(midPane, BorderLayout.CENTER);
		contentPane.add(bottomPane, BorderLayout.PAGE_END);
		fExMsg.add(contentPane);
		// ------------------------------------------------------
		fExMsg.setTitle("Fehlercode: " + fehlercode);
		fExMsg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fExMsg.setAlwaysOnTop(true);
		fExMsg.setResizable(true);
		fExMsg.setMinimumSize(new Dimension(600, 155));
		fExMsg.pack();
		fExMsg.setLocationRelativeTo(null);
		fExMsg.setVisible(true);
		
	}
	/**
	 * Holt den StackTrace und gibt ihn als ein JScrollPane zurück.
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
