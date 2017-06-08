package de.gokv.client.taskviewer.TestMe.Tutorial;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TutFrame extends JFrame {

	JLabel text;
	JButton but;
	JTextField eingabe;

	JCheckBox fett;
	JCheckBox kursiv;
	JCheckBox serif;

	JPanel panel_font;

	public TutFrame() {
		this.setVisible(true);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // -> startet in der Mitte
		setTitle("Task-Viewer");
		setAlwaysOnTop(true);
		setResizable(true);
		setLayout(null);

		text = new JLabel("Hey du, schreib was ins Feld!");
		text.setBounds(150, 5, 500, 60);
		Font schrift = new Font("Arial", Font.PLAIN, 20);
		text.setFont(schrift);
		text.setToolTipText("Das ist der TextLabel");
		add(text);

		but = new JButton("Zeig es an!");
		but.setBounds(10, 10, 100, 20);
		but.addActionListener(new TutListener());
		but.setEnabled(false);
		but.setToolTipText("Der Button schickt den Text zum TextLabel");
		add(but);

		eingabe = new JTextField("Schreib was rein");
		eingabe.setBounds(10, 40, 100, 20);
		eingabe.addCaretListener(new TutCaretListen());
		add(eingabe);

		panel_font = new JPanel();
		panel_font.setLayout(null);
		panel_font.setBounds(5, 80, 130, 80);
		add(panel_font);

		fett = new JCheckBox("Fett");
		fett.setBounds(0, 0, 100, 20);
		fett.addItemListener(new TutItemListen());
		fett.setToolTipText("zum Fett machen vom Text im Label");
		panel_font.add(fett);

		kursiv = new JCheckBox("Kursiv");
		kursiv.setBounds(0, 20, 100, 20);
		kursiv.addItemListener(new TutItemListen());
		kursiv.setToolTipText("zum kursiv machen vom Text im Label");
		panel_font.add(kursiv);

		serif = new JCheckBox("Serif");
		serif.setBounds(0, 40, 120, 20);
		serif.addItemListener(new TutItemListen());
		serif.setToolTipText("serifer Text im Label");
		panel_font.add(serif);

	}

	private class TutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			text.setText(eingabe.getText());
		}
	}

	private class TutCaretListen implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent arg0) {
			String s = eingabe.getText().trim();
			if (s.isEmpty()) {
				but.setEnabled(false);
			} else {
				but.setEnabled(true);
			}
		}
	}

	private class TutItemListen implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (fett.isSelected()) {
				Font fett = new Font("Arial", Font.BOLD, 20);
				text.setFont(fett);

			} else if (kursiv.isSelected()) {
				Font italic = new Font("Arial", Font.ITALIC, 20);
				text.setFont(italic);

			} else if (serif.isSelected()) {
				Font serif = new Font("Serif", Font.TRUETYPE_FONT, 20);
				text.setFont(serif);

			} else {
				Font schrift = new Font("Arial", Font.PLAIN, 20);
				text.setFont(schrift);
			}

		}

	}
}
