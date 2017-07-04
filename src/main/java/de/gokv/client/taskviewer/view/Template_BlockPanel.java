package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import de.gokv.client.taskviewer.controller.MyFrame_Controller;
import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.utils.HexaToRGB;

public abstract class Template_BlockPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected final Color colorTitle = new HexaToRGB("color.panel.titeltext.foreground").parseHexToRGB();
	protected final Color background = new HexaToRGB("color.panel.block.background").parseHexToRGB();
	protected final Color colorBtnBG = new HexaToRGB("color.button.background").parseHexToRGB();
	protected final Color colorBtnTxt = new HexaToRGB("color.button.text").parseHexToRGB();
	protected final Color colorEntryTxt = new HexaToRGB("color.entries.text").parseHexToRGB();
	protected final Color colorInfoTitleTxt = new HexaToRGB("color.info.titel.text").parseHexToRGB();
	protected final Color colorInfoTxt = new HexaToRGB("color.info.text").parseHexToRGB();
	
	protected final Font fontTitle = new Font("SansSerif", Font.BOLD, 18);
	protected final Font fontInfoTitle = new Font("Arial", Font.BOLD, 14);
	protected final Font fontTxt = new Font("Arial", Font.PLAIN, 15);

	
	protected final Border emptyBorder = BorderFactory.createEmptyBorder();
	protected final Border btnBorder = BorderFactory.createEmptyBorder(8, 5, 8, 5);
	
	protected final MyFrame_Controller controller;
	
	protected Template_BlockPanel(String title, MyFrame_Controller controller){
		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);
		titleBorder.setTitleJustification(TitledBorder.TOP);
		titleBorder.setTitleColor(colorTitle);
		titleBorder.setTitleFont(fontTitle);
		titleBorder.setBorder(emptyBorder);
		this.setBorder(titleBorder);
		this.setBackground(background);
		this.setLayout(getLayout());	
		this.controller = controller;
	}
	
	public abstract GridBagLayout getLayout();
	
	public abstract void init() throws ClientConfigurationExeception;
}
