package de.gokv.client.taskviewer.TestMe.Tutorial;

import javax.swing.JFrame;


public class LayoutTestMain {


	public static JFrame jf;
	public static void main(String[] args) {
		
		jf = new LayoutTest();
		jf.setVisible(true);
		jf.setSize(500, 300);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null); // -> startet in der Mitte
		jf.setAlwaysOnTop(true);
		jf.setResizable(true);
		
		
		

	}

	
}
