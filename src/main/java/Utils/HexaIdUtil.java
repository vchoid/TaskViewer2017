package Utils;

import java.util.regex.Pattern;


public class HexaIdUtil {

	public static boolean isIdValid(String id) {

		Pattern p = Pattern.compile("[A-F0-9]{32}");
		java.util.regex.Matcher m = p.matcher(id);
		if (m.matches()) {
			return true;
		} 
		return false;
	}

}
