package Utils;

import java.util.regex.Pattern;

public class KVNrUtil {

	public static boolean isKVNrValid(String kvNr) {

		Pattern p = Pattern.compile("[A-Z]{1}[0-9]{9}");
		java.util.regex.Matcher m = p.matcher(kvNr);
		if (m.matches()) {
			return true;
		} 
		return false;
	}
	
}
