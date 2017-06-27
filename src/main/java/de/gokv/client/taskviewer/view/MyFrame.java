package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.gokv.client.taskviewer.controller.MyFrameController;
import de.gokv.client.taskviewer.utils.HexaToRGB;

/**
 * @author Christoph Kiank
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// Content Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel contPanel;
	private MyFrameController controller;
	// Filter Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public FilterCriteriaTemplate filterMask;
	// Task Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public TaskListTemplate taskMask;
	// CSV Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public InfoCSVTemplate infoCSV;
	// Task Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public InfoTaskTemplate infoTask;

	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String pathAppIcon;
	private Color colorPanContBG;

	/**
	 * Konstruktor
	 */
	public MyFrame() {
		controller = new MyFrameController(this);
		
		pathAppIcon = "/taskViewer.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathAppIcon)));
		setTitle("bitGo_KV-TaskViewer");
		setAlwaysOnTop(true);

		// Properties
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Fenstergröße-------------------------
		// Quer 3x1
//		 setSize(new Dimension(1400, 455));
		// Quer 2x2
		setSize(new Dimension(930, 740));
		// Hoch
		// setSize(new Dimension(470, 970));
		
		// Farben ----------------------------------------------------------------------
		colorPanContBG = new HexaToRGB("color.panel.content.background").parseHexToRGB();

		// Content-Panel
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);
		getContentPane().setBackground(colorPanContBG);

		// Dummy Objekte ------------------------------------
		filterMask = new FilterCriteriaTemplate(controller);
		taskMask = new TaskListTemplate(controller);
		infoCSV = new InfoCSVTemplate(controller);
		infoTask = new InfoTaskTemplate(controller);

		// Initialisierung ----------------------------------
		filterMask.init();
		taskMask.init();
		infoCSV.init();
		infoTask.init();
		
		// Ergänzen auf Panel -------------------------------
		contPanel.add(filterMask);
		contPanel.add(taskMask);
		contPanel.add(infoCSV);
		contPanel.add(infoTask);

		// Error Message
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		if (HexaToRGB.errMsg != null) {
			JOptionPane.showMessageDialog(null, HexaToRGB.errMsg, "Fehler in der color.properties Datei",
					JOptionPane.WARNING_MESSAGE);
		}

		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public MyFrameController getController() {
		return controller;
	}

}
