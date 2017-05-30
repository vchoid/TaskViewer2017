package Utils;

import java.util.regex.Pattern;

/**
 * Hat eine Methode zum prüfen eines {@link String} auf Integrität.
 * 
 * <p>
 * <b>Funktionen:</b>
 * <ul>
 * <li><b>isKVNrValid({@link String})</b>: prüft eine KVNr auf syntaktische
 * Korrektheit</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * 
 *
 */

public class KVNrUtil {

	/**
	 * Überprüft eine KVNr (als {@link String}) auf syntaktische Korrektheit und
	 * und gibt anschließend ein {@link Boolean} zurück.
	 * 
	 * @param kvNr
	 *            {@link String}: Zu überprüfende KVNr.
	 * @return
	 * 		<ul>
	 *         <li><i>true</i>, wenn der String im korrektem Format vorliegt.
	 *         </li>
	 *         <li><i>false</i>, wenn der String im nicht korrektem Format
	 *         vorliegt.</li>
	 *         </ul>
	 */
	public static boolean isKVNrValid(String kvNr) {

		Pattern p = Pattern.compile("[A-Z]{1}[0-9]{9}");
		java.util.regex.Matcher m = p.matcher(kvNr);
		if (m.matches()) {
			return true;
		}
		return false;
	}

}
