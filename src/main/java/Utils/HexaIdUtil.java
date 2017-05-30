package Utils;

import java.util.regex.Pattern;

/**
 * Überprüft ob ein {@link String} im Hexadezimal-Format vorliegt
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * 
 *
 */
public class HexaIdUtil {

	/**
	 * 
	 * @param id
	 * @return 
	 */
	public static boolean isIdValid(String id) {

		Pattern p = Pattern.compile("[A-F0-9]{32}");
		java.util.regex.Matcher m = p.matcher(id);
		if (m.matches()) {
			return true;
		} 
		return false;
	}

}
