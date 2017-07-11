package de.gokv.client.taskviewer.TestMe.Tutorial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Test {

	
	public static void main(String[] args) {

		String title = (args.length == 0 ? "Expandable Split" : args[0]);

	    JFrame vFrame = new JFrame(title);
	    vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JComponent leftButton = new JButton("Top");
	    JComponent rightButton = new JButton("Bottom");
	    final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setLeftComponent(leftButton);
	    splitPane.setRightComponent(rightButton);
	    ActionListener oneActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        splitPane.resetToPreferredSizes();
	      }
	    };
	    ((JButton) rightButton).addActionListener(oneActionListener);
	    ActionListener anotherActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        splitPane.setDividerLocation(10);
	        splitPane.setContinuousLayout(true);
	      }
	    };
	    ((JButton) leftButton).addActionListener(anotherActionListener);
	    vFrame.getContentPane().add(splitPane, BorderLayout.CENTER);
	    vFrame.setSize(300, 150);
	    vFrame.setVisible(true);

	}

	

}
