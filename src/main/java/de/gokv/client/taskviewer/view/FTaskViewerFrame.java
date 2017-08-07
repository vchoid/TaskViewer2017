package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.controller.FrameTaskViewerController;
import de.gokv.client.taskviewer.utils.HexaToRGB;

/**
 * @author Christoph Kiank
 *
 */
public class FTaskViewerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Content Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel contPanel;
	private FrameTaskViewerController controller;
	// Filter Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public TFilterCriteriaPanelTemplate filterMask;
	// Task Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public TTaskListTemplate taskMask;
	// CSV Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public TInfoCSVPanelTemplate infoCSV;
	// Task Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public TInfoTaskPanelTemplate infoTask;

	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String pathAppIcon;
	private Color colorPanContBG;

	public FTaskViewerFrame() {
		controller = new FrameTaskViewerController(this);
		pathAppIcon = "/appIcon/taskViewer.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathAppIcon)));
		setTitle("bitGo_KV-TaskViewer");
//		setAlwaysOnTop(true);
		// Properties
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Fenstergröße------------------
		setSize(new Dimension(918, 728));
		// Farben
		// ----------------------------------------------------------------------
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
		filterMask = new TFilterCriteriaPanelTemplate(controller);
		taskMask = new TTaskListTemplate(controller);
		infoCSV = new TInfoCSVPanelTemplate(controller);
		infoTask = new TInfoTaskPanelTemplate(controller);

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
		// Fehler in der color.properties-Datei
		if (HexaToRGB.errMsg != null) {
			JOptionPane.showMessageDialog(null, HexaToRGB.errMsg, "Fehler in der color.properties Datei",
					JOptionPane.WARNING_MESSAGE);
		}
//		 Fehler in CSV Dateien
		if(controller.getModel().countInvalidTasks > 0){
			FCSVExceptionDialogFrame.showErrorMessageDialog(CSVReader.getAbstrExc(), controller.getModel().countInvalidTasks);
		}
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		repaint();
		setVisible(true);
	}

	public FrameTaskViewerController getController() {
		return controller;
	}

}
