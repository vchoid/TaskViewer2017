package de.gokv.client.taskviewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import de.gokv.client.taskviewer.TestMe.utils.DatePickerSample;

public class MyFrame extends JFrame {

	// Color[] colors = {Color.gray,Color.green, Color.orange};

	JPanel input;
	JTextField kvnr_field;
	JTextField name_field;
	JTextField vName_field;
	JTextField gebDate_field;
	JTextField taskId_field;
	JTextField orderDate_field;
	JButton setFilter;
	
	JPanel taskPanel;
	JButton loadDetails;
	JList<String> taskList;

	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		setSize(new Dimension(1000, 300));
		setLayout(null);
		setAlwaysOnTop(true);

		// Input-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		input = new JPanel();
		input.setLayout(null);
		input.setBounds(10, 10, 400, 350);
		add(input);
		
		kvnr_field = new JTextField("Kvnr");
		kvnr_field.setBounds(0, 0, 400, 25);
		input.add(kvnr_field);

		name_field = new JTextField("Name");
		name_field.setBounds(0, 50, 180, 25);
		input.add(name_field);

		vName_field = new JTextField("Vorname");
		vName_field.setBounds(220, 50, 180, 25);
		input.add(vName_field);

		gebDate_field = new JTextField("Geburtsdatum");
		gebDate_field.setBounds(220, 90, 180, 25);
		input.add(gebDate_field);

		taskId_field = new JTextField("TaskID");
		taskId_field.setBounds(0, 130, 400, 25);
		input.add(taskId_field);

		orderDate_field = new JTextField("OrderDate");
		orderDate_field.setBounds(220, 170, 180, 25);
		input.add(orderDate_field);

		setFilter = new JButton("Filter anwenden");
		setFilter.setBounds(0, 210, 400, 25);
		input.add(setFilter);

		// TaskPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		taskPanel = new JPanel();
		taskPanel.setLayout(null);
		taskPanel.setBounds(420, 10, 300, 350);
		add(taskPanel);
		
		String[] liste = { "Task 1", "Task 2", "Task 3", "Task 4", "Task 5", "Task 6", "Task 7", "Task 8", "Task 9",
				"Task 10", "Task 11", "Task 12", "Task 13","Task 15","Task 16","Task 17","Task 18","Task 19" };
		
		taskList = new JList<>(liste);
		taskList.setBounds(0, 0, 300, 190);
		taskPanel.add(taskList);

		loadDetails = new JButton("Details anzeigen");
		loadDetails.setBounds(0, 210, 300, 25);
		taskPanel.add(loadDetails);

		/**
		 * Beendet die Anwendung. Sichtbarkeit auf true.
		 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
