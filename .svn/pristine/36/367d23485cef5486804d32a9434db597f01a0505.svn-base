package de.gokv.client.taskviewer.TestMe.Tutorial;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TutFrame extends JFrame{

	JLabel text;
	JTextField eingabe;
	
	JPanel chkButPan;
	JButton showBut;
	JButton zeit;
	
	JPanel radButPan;
	JRadioButton kirsche;
	JRadioButton kuchen;
	JRadioButton computer;
	ButtonGroup radGroup;
	
	
	JPanel fontPan;
	JCheckBox fett;
	JCheckBox kursiv;
	JCheckBox serif;


	public TutFrame() {
		setLayout(null);
		setTitle("Tutorial");
		
//		setVisible(true);
//		setSize(500, 300);
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setLocationRelativeTo(null); // -> startet in der Mitte
//		setAlwaysOnTop(true);
//		setResizable(true);

		text = new JLabel("Hey du, schreib was ins Feld!");
		text.setBounds(150, 5, 500, 60);
		Font schrift = new Font("Arial", Font.PLAIN, 20);
		text.setFont(schrift);
		text.setToolTipText("Das ist der TextLabel");
		add(text);

		// +++++++++++++++++++++++++++++++++++++++++++++++
		chkButPan = new JPanel();
		chkButPan.setBounds(10, 10, 200, 60);
		chkButPan.setLayout(null);
		add(chkButPan);
		
		TutListener tutList = new TutListener();
		
		showBut = new JButton("Zeig es dort =>");
		showBut.setBounds(0, 0, 120, 20);
		showBut.addActionListener(tutList);
		showBut.setEnabled(false);
		showBut.setToolTipText("Der Button schickt den Text zum TextLabel");
		chkButPan.add(showBut);

		zeit = new JButton("Die Zeit =>");
		zeit.setBounds(0, 30, 120, 20);
		zeit.addActionListener(tutList);
		chkButPan.add(zeit);
		
		eingabe = new JTextField("Schreib was rein");
		eingabe.setBounds(10, 70, 100, 20);
		eingabe.addCaretListener(new TutCaretListen());
		add(eingabe);

		// ++++++++++++++++++++++++++++++++++++++++++++++
		fontPan = new JPanel();
		fontPan.setLayout(null);
		fontPan.setBounds(10, 100, 130, 60);
		add(fontPan);

		TutItemListen tutItemList = new TutItemListen();
		
		fett = new JCheckBox("Fett");
		fett.setBounds(0, 0, 100, 20);
		fett.addItemListener(tutItemList);
		fett.setToolTipText("zum Fett machen vom Text im Label");
		fontPan.add(fett);

		kursiv = new JCheckBox("Kursiv");
		kursiv.setBounds(0, 20, 100, 20);
		kursiv.addItemListener(tutItemList);
		kursiv.setToolTipText("zum kursiv machen vom Text im Label");
		fontPan.add(kursiv);

		serif = new JCheckBox("Serif");
		serif.setBounds(0, 40, 120, 20);
		serif.addItemListener(tutItemList);
		serif.setToolTipText("serifer Text im Label");
		fontPan.add(serif);
		// ++++++++++++++++++++++++++++++++++++++++++++++
		radButPan = new JPanel();
		radButPan.setLayout(null);
		radButPan.setBounds(10, 170, 130, 60);
		add(radButPan);
		
		kirsche = new JRadioButton("Kirsche");
		kirsche.setBounds(0, 0, 100, 20);
		kirsche.addItemListener(tutItemList);
		radButPan.add(kirsche);
		
		kuchen = new JRadioButton("Kuchen");
		kuchen.setBounds(0, 20, 100, 20);
		kuchen.addItemListener(tutItemList);
		radButPan.add(kuchen);
		
		computer = new JRadioButton("Computer");
		computer.setBounds(0, 40, 100, 20);
		computer.addItemListener(tutItemList);
		radButPan.add(computer);
		
		// damit nur ein Radio-Button ausgewählt werden kann
		radGroup = new ButtonGroup();
		radGroup.add(kirsche);
		radGroup.add(kuchen);
		radGroup.add(computer);
		
	}

	private class TutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == showBut){
			text.setText(eingabe.getText());
			} else if(e.getSource() == zeit){
				text.setText("" + e.getWhen());
			}
		}
	}

	private class TutCaretListen implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent arg0) {
			String s = eingabe.getText().trim();
			if (s.isEmpty()) {
				showBut.setEnabled(false);
			} else {
				showBut.setEnabled(true);
			}
		}
	}

	private class TutItemListen implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == fett){
				if (fett.isSelected()) {
					Font fett = new Font("Arial", Font.BOLD, 20);
					text.setFont(fett);

				} else {
					Font schrift = new Font("Arial", Font.PLAIN, 20);
					text.setFont(schrift);
				}
			} else if(e.getSource() == kursiv){
				if (kursiv.isSelected()) {
					Font italic = new Font("Arial", Font.ITALIC, 20);
					text.setFont(italic);
				}else {
					Font schrift = new Font("Arial", Font.PLAIN, 20);
					text.setFont(schrift);
				}
			} else if(e.getSource() == serif){
				if (serif.isSelected()) {
					Font serif = new Font("Serif", Font.TRUETYPE_FONT, 20);
					text.setFont(serif);
				} else {
					Font schrift = new Font("Arial", Font.PLAIN, 20);
					text.setFont(schrift);
				}
				
			} else if(e.getSource() instanceof JRadioButton){
				text.setText(((JRadioButton)e.getSource()).getText());
			}
			

		}

	}
}
