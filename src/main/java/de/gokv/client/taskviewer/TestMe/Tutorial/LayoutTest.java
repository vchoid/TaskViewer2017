package de.gokv.client.taskviewer.TestMe.Tutorial;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LayoutTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton a;
	JButton b;
	JButton c;
	JButton d;
	JButton e;
	JButton f;
	Color co = Color.DARK_GRAY;

	JButton fileChooser;
	JTextArea textArea;

	JButton choosePic;
	JLabel pic;
	Icon ic1;
	Icon ic2;

	JComboBox<String> dropDownMenu;

	JList<String> jListe;
	String[] tasks = { "Task 1", "Task 2", "Task 3", "Task 4", "Task 5", "Task 6", "Task 7", "Task 8", "Task 9",
			"Task 10", "Task 11", "Task 12", "Task 13","Task 15","Task 16","Task 17","Task 18","Task 19" };
	
	
	public LayoutTest() {
		super("Layoutest");

		setLayout(new FlowLayout());

		ButtonHandler butHand = new ButtonHandler();

		a = new JButton("Rot");
		b = new JButton("Grün");
		c = new JButton("Blau");
		d = new JButton("Gelb");
		e = new JButton("Cyan");
		f = new JButton("ColorPicker");

		fileChooser = new JButton("FileChooser");
		textArea = new JTextArea("Damit Ihr indess erkennt, woher dieser ganze Irrthum gekommen ist, "
				+ "und weshalb man die Lust anklagt und den Schmerz lobet. ", 3, 20);
		/**
		 * Zeilenumbruch
		 */
		textArea.setLineWrap(true);
		/**
		 * bei Wörter trennen
		 */
		textArea.setWrapStyleWord(true);
		/**
		 * Texthintergrundfarbe nimmt die Farbe vom Background an
		 */
		// textArea.setBackground(getBackground());
		/**
		 * Texthintergrundfarbe wird transparent
		 */
		textArea.setOpaque(false);

		choosePic = new JButton("Bild ändern");

		ic1 = new ImageIcon(getClass().getResource("bild1.jpg"));
		ic2 = new ImageIcon(getClass().getResource("bild2.png"));

		pic = new JLabel(ic1);

		String[] namen = { "aaa", "bbb", "ccc" };
		dropDownMenu = new JComboBox<String>(namen);
		
		jListe = new JList<String>(tasks);
		/**
		 * zeigt die ersten 5 Einträge
		 */
		jListe.setVisibleRowCount(5);	
		// Single_Selection = nur eine Auswahlmöglich
		jListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		a.addActionListener(butHand);
		b.addActionListener(butHand);
		c.addActionListener(butHand);
		d.addActionListener(butHand);
		e.addActionListener(butHand);
		f.addActionListener(butHand);
		choosePic.addActionListener(butHand);
		fileChooser.addActionListener(butHand);
		dropDownMenu.addItemListener(new ItemHandler());
		jListe.addListSelectionListener(new ListenHandler());
		

		add(a);
		add(b);
		add(c);
		add(d);
		add(e);
		add(f);
		add(fileChooser);
		add(new JScrollPane(textArea));
		add(pic);
		add(choosePic);
		add(dropDownMenu);
		add(new JScrollPane(jListe));

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == a) {
				getContentPane().setBackground(Color.RED);
			} else if (ae.getSource() == b) {
				getContentPane().setBackground(Color.GREEN);
			} else if (ae.getSource() == c) {
				getContentPane().setBackground(Color.BLUE);
			} else if (ae.getSource() == d) {
				getContentPane().setBackground(Color.YELLOW);
			} else if (ae.getSource() == e) {
				Color cyan = new Color(10, 150, 170);
				getContentPane().setBackground(cyan);
			} else if (ae.getSource() == f) {
				co = JColorChooser.showDialog(null, "ColorPicker", co);
				getContentPane().setBackground(co);
			} else if (ae.getSource() == fileChooser) {
				JFileChooser fc = new JFileChooser();
				int returnValue = fc.showOpenDialog(null);
				File file = fc.getSelectedFile();
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(file.getPath());
				} else if (returnValue == JFileChooser.CANCEL_OPTION) {
					System.out.println("keine Datei gewählt");
				} else if (returnValue == JFileChooser.ERROR_OPTION) {
					System.out.println("Error!");
				}
			} else if (ae.getSource() == choosePic) {
				pic.setIcon(ic2);
			}
		}

	}

	private class ItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// damit nur einmal der Wechsel angezeigt wird
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(dropDownMenu.getSelectedIndex() == 0){
					System.out.println("changed to:" + dropDownMenu.getSelectedIndex() + " == " + dropDownMenu.getSelectedItem());
				} else if(dropDownMenu.getSelectedIndex() == 1){
					System.out.println("changed to:" + dropDownMenu.getSelectedIndex() + " == " + dropDownMenu.getSelectedItem());
				} else if(dropDownMenu.getSelectedIndex() == 2){
					System.out.println("changed to:" + dropDownMenu.getSelectedIndex() + " == " + dropDownMenu.getSelectedItem());
				}
			}
		}
	}
	
	private class ListenHandler implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()){
			System.out.println(tasks[jListe.getSelectedIndex()]);
			}
			
		}
		
	}
	private class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
