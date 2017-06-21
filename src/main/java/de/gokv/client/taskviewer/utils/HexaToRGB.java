package de.gokv.client.taskviewer.utils;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexaToRGB {

	private Pattern p;
	private Matcher m;
	private File file;
	private Properties prop;
	private BufferedInputStream bis;
	private String propVal;
	private String propName;
	private String errMsg;

	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

	public HexaToRGB(String propName) {
		super();
		this.propName = propName;
	}

	public boolean isHexValid() {
		try {
			prop = new Properties();
			file = new File("C:/java/workspaces/BitgoKV/gokv-client-task-viewer/src/main/resources/color.properties");
			bis = new BufferedInputStream(new FileInputStream(file));
			prop.load(bis);
			propVal = prop.getProperty(propName);
		} catch (IOException e) {

		}
		p = Pattern.compile(HEX_PATTERN);
		
			m = p.matcher(propVal);
		
		return m.matches();
	}

	public Color getRGB() {
		if (isHexValid()) {
			return Color.decode(propVal);
		} else {
			System.out.println("scheiﬂe");
			return Color.decode(defaultColorMode());
		}
	}
	public String defaultColorMode(){
		switch (propName) {
		case "color.panel.block.background":
			errMsg = "Farbe";
			propVal = "#bebebe";
			return propVal;
		case "color.button.background":
			System.out.println("2a");
			propVal = "#A61F7D";
			return propVal;
		case "color.button.text":
			System.out.println("3a");
			propVal = "#ffffff";
			return propVal;
		case "color.titeltext.foreground":
			System.out.println("4a");
			propVal = "#232d8d";
			return propVal;
		}
		return propVal;
	}
}
