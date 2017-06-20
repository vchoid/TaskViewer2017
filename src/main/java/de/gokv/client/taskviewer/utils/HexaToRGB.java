package de.gokv.client.taskviewer.utils;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexaToRGB {

	private Pattern p;
	private Matcher m;
	
	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
	
	public HexaToRGB() {
	}
	
	public boolean isHexValid(final String hexColorStr) {
		p = Pattern.compile(HEX_PATTERN);
		m = p.matcher(hexColorStr);
		System.out.println(m.matches());
		return m.matches();
	}
	
	public Color getRGB(String hexColorStr) {
		if(isHexValid(hexColorStr)){
		return Color.decode(hexColorStr);
		}
		throw new IllegalArgumentException("Falsches Farbformat");
	}
	
}
