package de.gokv.client.taskviewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.gokv.client.taskviewer.TestMe.DatePickerSample;

public class MyFrame extends JFrame{

//	Color[] colors = {Color.gray,Color.green, Color.orange};
	
	JTextField kvnr_field;
	JTextField name_field;
	JTextField vName_field;
	JTextField gebDate_field;
	JTextField taskId_field;
	JTextField orderDate_field;
	JLabel kvnr_label;
	
	JButton setFilter;
	JButton loadDetails;
	
	JList<String> taskList;
	
	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		setSize(new Dimension(1000, 300));
		this.getContentPane().setLayout(null);
		
		
		kvnr_field = new JTextField("Kvnr");
		kvnr_field.setBounds(10,10,400,25);
		this.getContentPane().add(kvnr_field);
		
		name_field = new JTextField("Name");
		name_field.setBounds(10,50,180,25);
		this.getContentPane().add(name_field);
		
		vName_field = new JTextField("Vorname");
		vName_field.setBounds(230,50,180,25);
		this.getContentPane().add(vName_field);
		
		gebDate_field = new JTextField("Geburtsdatum");
		gebDate_field.setBounds(230,90,180,25);
		this.getContentPane().add(gebDate_field);
		
		taskId_field= new JTextField("TaskID");
		taskId_field.setBounds(10,130,400,25);
		this.getContentPane().add(taskId_field);
		
		orderDate_field = new JTextField("OrderDate");
		orderDate_field.setBounds(230,170,180,25);
		this.getContentPane().add(orderDate_field);
		
		setFilter = new JButton("Filter anwenden");
		setFilter.setBounds(10, 210, 400, 25);
		this.getContentPane().add(setFilter);
		
		taskList = new JList<>();
		taskList.setAutoscrolls(true);
		taskList.setBounds(430, 10, 300, 185);
		this.getContentPane().add(taskList);
		
		loadDetails = new JButton("Details anzeigen");
		loadDetails.setBounds(430, 210, 300, 25);
		this.getContentPane().add(loadDetails);
		
		
		
		/**
		 * Beendet die Anwendung.
		 * Sichtbarkeit auf true.
		 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
	
}
