package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.http.client.HttpClient;

import de.gokv.client.taskviewer.controller.MyFrame_Controller;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.utils.HexaToRGB;

/**
 * @author Christoph Kiank
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// Content Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel contPanel;
	private MyFrame_Controller controller;
	// Filter Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Template_FilterCriteriaPanel filterMask;
	// Task Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Template_TaskList taskMask;
	// CSV Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Template_InfoCSVPanel infoCSV;
	// Task Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Template_InfoTaskPanel infoTask;

	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String pathAppIcon;
	private Color colorPanContBG;

	public MyFrame() {
		controller = new MyFrame_Controller(this);
		pathAppIcon = "/taskViewer.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathAppIcon)));
		setTitle("bitGo_KV-TaskViewer");
		setAlwaysOnTop(true);
		// Properties
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Fenstergröße------------------
		setSize(new Dimension(918, 728));
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
		filterMask = new Template_FilterCriteriaPanel(controller);
		taskMask = new Template_TaskList(controller);
		infoCSV = new Template_InfoCSVPanel(controller);
		infoTask = new Template_InfoTaskPanel(controller);

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
		// color.properties
		if (HexaToRGB.errMsg != null) {
			JOptionPane.showMessageDialog(null, HexaToRGB.errMsg, "Fehler in der color.properties Datei",
					JOptionPane.WARNING_MESSAGE);
		}
		//TODO errMsg auffangen bei laden der task
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public MyFrame_Controller getController() {
		return controller;
	}

}
