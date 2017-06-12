package de.gokv.client.taskviewer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;


public class MyFrame extends JFrame {

	JPanel contPanel;
	
	TitledBorder filtBorder;
	JPanel filterPanel;
	JTextField kvnr_field;
	JTextField name_field;
	JTextField vName_field;
	JTextField gebDate_field;
	JTextField taskId_field;
	JTextField orderDate_field;
	JButton filterBtn;
	
	TitledBorder taskBorder;
	JPanel taskPanel;
	JButton detailsBtn;
	JList<String> taskList;
	
	JPanel infoPanel;
	JLabel infoLabel;
	

	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		setSize(new Dimension(900, 270));
		setAlwaysOnTop(true);
		
		
		
		// Content-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);

		// Input-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		filtBorder = BorderFactory.createTitledBorder("Filter Maske");
		filtBorder.setTitleJustification(TitledBorder.CENTER);
		filterPanel = new JPanel();
		filterPanel.setBorder(filtBorder);
		GridBagLayout gbl_filPanel = new GridBagLayout();
		gbl_filPanel.columnWidths = new int[]{10,200,200,10};
		gbl_filPanel.rowHeights = new int[]{30,30,30,30,30,30,10};
		filterPanel.setLayout(gbl_filPanel);
		contPanel.add(filterPanel);
		
		// << KVNR >>
		kvnr_field = new JTextField();
		kvnr_field.setText("Kvnr");
		GridBagConstraints gbc_kvnr = new GridBagConstraints();
		gbc_kvnr.anchor = GridBagConstraints.NORTH;
		gbc_kvnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvnr.gridwidth = 2;
		gbc_kvnr.gridx = 1;
		gbc_kvnr.gridy = 0;
		filterPanel.add(kvnr_field, gbc_kvnr);
		
		// << Name >>
		name_field = new JTextField();
		name_field.setText("Name");
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.NORTH;
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.insets = new Insets(0, 0, 0, 10);
		gbc_name.gridx = 1;
		gbc_name.gridy = 1;
		filterPanel.add(name_field, gbc_name);
		
		// << Vorname >>
		vName_field = new JTextField();
		vName_field.setText("Vorname");
		GridBagConstraints gbc_vName = new GridBagConstraints();
		gbc_vName.anchor = GridBagConstraints.NORTH;
		gbc_vName.fill = GridBagConstraints.HORIZONTAL;
		gbc_vName.gridx = 2;
		gbc_vName.gridy = 1;
		filterPanel.add(vName_field, gbc_vName);
		
		// << Geburtsdatum >>
		gebDate_field = new JTextField();
		gebDate_field.setText("Geburtsdatum");
		GridBagConstraints gbc_gebDate = new GridBagConstraints();
		gbc_gebDate.anchor = GridBagConstraints.NORTH;
		gbc_gebDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_gebDate.gridx = 2;
		gbc_gebDate.gridy = 2;
		filterPanel.add(gebDate_field, gbc_gebDate);
		
		// << TaskID >>
		taskId_field = new JTextField();
		taskId_field.setText("TaskID");
		GridBagConstraints gbc_taskID = new GridBagConstraints();
		gbc_taskID.anchor = GridBagConstraints.NORTH;
		gbc_taskID.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID.gridwidth = 2;
		gbc_taskID.gridx = 1;
		gbc_taskID.gridy = 3;
		filterPanel.add(taskId_field, gbc_taskID);
		
		// << OrderedDate >>
		orderDate_field = new JTextField();
		orderDate_field.setText("OrderDate");
		GridBagConstraints gbc_orderDate = new GridBagConstraints();
		gbc_orderDate.anchor = GridBagConstraints.NORTH;
		gbc_orderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderDate.gridx = 2;
		gbc_orderDate.gridy = 4;
		filterPanel.add(orderDate_field, gbc_orderDate);
		
		// << Button "Filter anwenden" >>
		filterBtn = new JButton("Tasks filtern");
		GridBagConstraints gbc_filterBtn = new GridBagConstraints();
		gbc_filterBtn.anchor = GridBagConstraints.NORTH;
		gbc_filterBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterBtn.gridwidth = 2;
		gbc_filterBtn.gridx = 1;
		gbc_filterBtn.gridy = 5;
		filterPanel.add(filterBtn, gbc_filterBtn);

		// TaskPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		taskBorder = BorderFactory.createTitledBorder("Task Liste");
		taskBorder.setTitleJustification(TitledBorder.CENTER);
		taskPanel = new JPanel();
		taskPanel.setBorder(taskBorder);
		GridBagLayout gbl_taskPan = new GridBagLayout();
		gbl_taskPan.columnWidths = new int[]{10,200,10};
		gbl_taskPan.rowHeights = new int[]{150,10,10};
		taskPanel.setLayout(gbl_taskPan);
		contPanel.add(taskPanel);
		
		// >>> Test-Liste
		String[] StringList = { "Task 1", "Task 2", "Task 3", "Task 4", "Task 5", "Task 6", "Task 7", "Task 8", "Task 9",
				"Task 10", "Task 11", "Task 12", "Task 13","Task 15","Task 16","Task 17","Task 18","Task 19" };
		// <<<
		
		// << Task-Liste >>
		taskList = new JList<>(StringList);
		JScrollPane scrollTask = new JScrollPane(taskList);
		GridBagConstraints gbc_scrollTask = new GridBagConstraints();
		gbc_scrollTask.anchor = GridBagConstraints.NORTH;
		gbc_scrollTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollTask.gridwidth = 1;
		gbc_scrollTask.gridx = 1;
		gbc_scrollTask.gridy = 0;
		gbc_scrollTask.insets = new Insets(0, 0, 5, 0);
		taskPanel.add(scrollTask, gbc_scrollTask);
		
		// << Detail-Button >>
		detailsBtn = new JButton("Task laden");
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTH;
		gbc_detailBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailBtn.gridwidth = 1;
		gbc_detailBtn.gridx = 1;
		gbc_detailBtn.gridy = 1;
		taskPanel.add(detailsBtn, gbc_detailBtn);

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
