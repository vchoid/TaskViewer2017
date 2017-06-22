package de.gokv.client.taskviewer.utils;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.core.IsNull;

import de.gokv.client.taskviewer.controller.LoadProperties;

/**
 * Umwandeln von HTML-Farbcodes in RGB-Farbwerte. Bei fehlerhaften Werten werden
 * Default-Werte geladen.
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class HexaToRGB {

	private Pattern patt;
	private Matcher match;

	private String propFile;
	private String propTitle;
	private String propVal;

	public static String errMsg;
	public static int countErr = 1;

	private LoadProperties loadProp;

	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

	// Konstruktor
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public HexaToRGB(String propTitle) {
		super();
		this.propTitle = propTitle;
	}

	// ------------------------------------------------------------------------------------------------------

	public boolean isHexValid() {
		propFile = "color.properties";
		loadProp = new LoadProperties(propFile);
		propVal = loadProp.getPropertyVal(propTitle);
		if (propVal == null) {
			setDefaultColor();
			++countErr;
		}
		patt = Pattern.compile(HEX_PATTERN);
		match = patt.matcher(propVal);
		return match.matches();
	}

	public Color getRGB() {
		if (isHexValid()) {
			return Color.decode(propVal);
		} else {
			++countErr;
			return Color.decode(setDefaultColor());
		}
	}

	public String setDefaultColor() {
		switch (propTitle) {
		case "color.panel.content.background":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#F0F0F0";
			return propVal;
		case "color.panel.block.background":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#bebebe";
			return propVal;
		case "color.panel.titeltext.foreground":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#232d8d";
			return propVal;
		case "color.button.background":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#A61F7D";
			return propVal;
		case "color.button.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#ffffff";
			return propVal;
		case "color.label.date.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#6e6e6e";
			return propVal;
		case "color.info.titel.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#000000";
			return propVal;
		case "color.info.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#232d8d";
			return propVal;
		case "color.ErrorMsg.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
			+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#a61f7d";
			return propVal;
		case "color.placeholder.text":
			errMsg = countErr + " Fehler: Der Farbwert '" + propVal + "' von '" + propTitle
					+ "' wurde auf den Standardwert zur�ck gesetzt";
			propVal = "#6e6e6e";
			return propVal;
		}
		return propVal;
	}
}
