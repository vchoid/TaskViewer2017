package de.gokv.client.taskviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.csv.CSVRecord;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.controller.FrameExceptionArrayController;
import net.miginfocom.swing.MigLayout;


/**
 * Anzeige von Fehlern in CSV Datein.
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #showErrorMessageDialog(List, List)}</b>: Ermöglicht die Anzeige von Fehler in einem Extra Fenster.</li>
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class FcsvExceptionDialogFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JPanel titlePane;
	public static JPanel descriptionPane;
	public static JPanel stackTracePane;
	public static JPanel bottomPane;

	public PButtonPattern expandBtn;
	public static FrameExceptionArrayController btnCont = new FrameExceptionArrayController();

	public static JLabel errorIconLabel;
	public static JLabel errTitle;
	public static JLabel errSize;
	private String errSizeString;
	public static JTextArea errShortMsg;
	private static int errorCode;

	public String pathAppIcon;
	public Image iconLoad = new ImageIcon(getClass().getResource("/expandIcon/expandMoreIcon_grey.png")).getImage();
	public ImageIcon iconLoadScaled = new ImageIcon(iconLoad.getScaledInstance(12, 12, 0));

	public Image imageError = new ImageIcon(getClass().getResource("/errorIcon/errIcon_red.png")).getImage();
	public ImageIcon imageIconErrorScaled = new ImageIcon(imageError.getScaledInstance(25, 25, 0));
	public Icon iconError = imageIconErrorScaled;

	public static final Font FONT_TITLE = new Font("SansSerif", Font.PLAIN, 20);

	public static List<String> abstrExc;
	public static int invalidEntries;
	private static String filePath;
	public static FcsvExceptionDialogFrame fExMsg;

	/**
	 * Das Absturzprogramm Icon wird hier geladen.
	 */
	public FcsvExceptionDialogFrame() {
		pathAppIcon = "/appIcon/taskViewerError.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathAppIcon)));
	}
	/**
	 * Ermöglicht die Anzeige von Fehler in einem Extra Fenster.
	 * 
	 * @param abstrExc
	 * @param invalidEntries
	 */
	public static void showErrorMessageDialog(List<String> abstrExc, int invalidEntries) {
		errorCode = 300;
		fExMsg = new FcsvExceptionDialogFrame();
		FcsvExceptionDialogFrame.invalidEntries = invalidEntries;
		FcsvExceptionDialogFrame.abstrExc = abstrExc;
		BorderLayout bLayoutCONTENT = new BorderLayout();
		MigLayout mlayoutTOP = new MigLayout("", "[][grow][]", "[]");
		MigLayout mlayoutMIDDLE = new MigLayout("", "[][grow][]", "[]");
		MigLayout mlayoutBTTM = new MigLayout("", "[][grow][]", "[]");

		// Content Pane ++++++++++++++++++++++++++++++++++++++++
		contentPane = new JPanel();
		contentPane.setLayout(bLayoutCONTENT);
		// Top Pane ++++++++++++++++++++++++++++++++++++++++++++
		titlePane = new JPanel();
		titlePane.setBackground(Color.WHITE);
		titlePane.setLayout(mlayoutTOP);
		errorIconLabel = new JLabel();
		errorIconLabel.setIcon(fExMsg.iconError);
		errTitle = new JLabel("Fehlerhafte Werte in CSV-Datein");
		errTitle.setFont(FONT_TITLE);
		if(invalidEntries == 1){
			fExMsg.errSizeString = ""+ invalidEntries + " fehlerhafter Eintrag";
		} else {
			fExMsg.errSizeString = ""+ invalidEntries + " fehlerhafte Einträge";
		}
		errSize = new JLabel(fExMsg.errSizeString);
		// ------------------------------------------------------
		titlePane.add(errorIconLabel, "");
		titlePane.add(errTitle, "wrap,span");
		titlePane.add(errSize, "grow, skip, span");
		// Middle Pane +++++++++++++++++++++++++++++++++++++++++
		stackTracePane = new JPanel(new BorderLayout());
		stackTracePane.setBackground(Color.WHITE);
		stackTracePane.setLayout(mlayoutMIDDLE);
		stackTracePane.setVisible(false);
		// ------------------------------------------------------
		stackTracePane.add(getStackTraceAsScrollPane(), "span, grow, push, wrap");
		// Bottom Pane +++++++++++++++++++++++++++++++++++++++++
		bottomPane = new JPanel();
		bottomPane.setLayout(mlayoutBTTM);
		bottomPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		fExMsg.expandBtn = new PButtonPattern("Weitere Details");
		fExMsg.expandBtn.addActionListener(btnCont);
		fExMsg.expandBtn.setIcon(fExMsg.iconLoadScaled);
		// ------------------------------------------------------
		bottomPane.add(fExMsg.expandBtn, "flowy");
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++
		contentPane.add(titlePane, BorderLayout.PAGE_START);
		contentPane.add(stackTracePane, BorderLayout.CENTER);
		contentPane.add(bottomPane, BorderLayout.PAGE_END);
		fExMsg.add(contentPane);
		// ------------------------------------------------------
		fExMsg.setTitle("Fehlercode: " + errorCode);
		fExMsg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fExMsg.setAlwaysOnTop(true);
		fExMsg.setResizable(true);
		fExMsg.setMinimumSize(new Dimension(600, 149));
		fExMsg.setLocationRelativeTo(null);
		fExMsg.setVisible(true);
		fExMsg.pack();

	}

	public static JScrollPane getStackTraceAsScrollPane() {
		JTextArea text = new JTextArea();
		text.setEditable(false);
		if (CSVReader.getInvFilePaths().size() == 2) {
			filePath = "In der Datei: " + CSVReader.getInvFilePaths() + "\ntraten folgende Fehler auf: \n\n";
		} else {
			filePath = "In den Dateien: " + CSVReader.getInvFilePaths() + "\ntraten folgende Fehler auf: \n\n";
		}
		text.setText(filePath + abstrExc.toString().replace("[", "").replace("]", "").replace(",", ""));
		JScrollPane scroller = new JScrollPane(text);
		return scroller;
	}

}
