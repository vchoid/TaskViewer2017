package de.gokv.client.taskviewer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class MyFrame extends JFrame {

	JPanel contPanel;
	
	JPanel filterPanel;
	JTextField kvnr_field;
	JTextField name_field;
	JTextField vName_field;
	JTextField gebDate_field;
	JTextField taskId_field;
	JTextField orderDate_field;
	JButton filterBtn;
	
	JPanel taskPanel;
	JButton detailsBtn;
	JList<String> taskList;
	
	JPanel infoPanel;
	JLabel infoLabel;

	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		setSize(new Dimension(800, 300));
		setAlwaysOnTop(true);
		
		// Content-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);

		// Input-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		filterPanel.setBounds(10, 10, 400, 350);
		contPanel.add(filterPanel);
		
		kvnr_field = new JTextField("Kvnr");
		kvnr_field.setBounds(0, 0, 400, 25);
		filterPanel.add(kvnr_field);

		name_field = new JTextField("Name");
		name_field.setBounds(0, 50, 180, 25);
		filterPanel.add(name_field);

		vName_field = new JTextField("Vorname");
		vName_field.setBounds(220, 50, 180, 25);
		filterPanel.add(vName_field);

		gebDate_field = new JTextField("Geburtsdatum");
		gebDate_field.setBounds(220, 90, 180, 25);
		filterPanel.add(gebDate_field);

		taskId_field = new JTextField("TaskID");
		taskId_field.setBounds(0, 130, 400, 25);
		filterPanel.add(taskId_field);

		orderDate_field = new JTextField("OrderDate");
		orderDate_field.setBounds(220, 170, 180, 25);
		filterPanel.add(orderDate_field);

		filterBtn = new JButton("Filter anwenden");
		filterBtn.setBounds(0, 210, 400, 25);
		filterPanel.add(filterBtn);

		// TaskPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));
		contPanel.add(taskPanel);
		
		String[] liste = { "Task 1", "Task 2", "Task 3", "Task 4", "Task 5", "Task 6", "Task 7", "Task 8", "Task 9",
				"Task 10", "Task 11", "Task 12", "Task 13","Task 15","Task 16","Task 17","Task 18","Task 19" };
		
		taskList = new JList<>(liste);
		taskList.setBounds(0, 0, 300, 190);
		taskList.setVisibleRowCount(9);
		taskPanel.add(new JScrollPane(taskList));

		detailsBtn = new JButton("Details anzeigen");
		detailsBtn.setBounds(0, 210, 300, 25);
		taskPanel.add(detailsBtn);

		// Info-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		contPanel.add(infoPanel);
		
		infoLabel = new JLabel("weitere Infos");
		infoLabel.setBounds(0, 0, 100, 20);
		Font schrift = new Font("Arial", Font.PLAIN, 20);
		infoLabel.setFont(schrift);
		infoPanel.add(infoLabel);
		
		
		/**
		 * Beendet die Anwendung. Sichtbarkeit auf true.
		 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
