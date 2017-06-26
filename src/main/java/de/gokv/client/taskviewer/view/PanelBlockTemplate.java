package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import de.gokv.client.taskviewer.utils.HexaToRGB;

public class PanelBlockTemplate {
	
	GridBagLayout layout;
	
	private TitledBorder titleBorder;
	private JPanel panel;
	
	private Color colorTitle = new HexaToRGB("color.panel.titeltext.foreground").parseHexToRGB();
	private Color background = new HexaToRGB("color.panel.block.background").parseHexToRGB();
	
	private Font fontTitle = new Font("Arial", Font.BOLD, 16);
	
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	
	public PanelBlockTemplate() {
		super();
	}
	
	public void createPanelWithTitle(String title){
		titleBorder = BorderFactory.createTitledBorder(title);
		titleBorder.setTitleJustification(TitledBorder.TOP);
		titleBorder.setTitleColor(colorTitle);
		titleBorder.setTitleFont(fontTitle);
		titleBorder.setBorder(emptyBorder);
		panel =  new JPanel();
		panel.setBorder(titleBorder);
		panel.setBackground(background);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setGridBagLayout(GridBagLayout layout){
		panel.setLayout(layout);
	}
	
	
	
	
	
	
	

}
