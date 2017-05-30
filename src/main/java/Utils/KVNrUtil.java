package Utils;

import java.util.regex.Pattern;

/**
 * Hat eine Methode zum pr�fen eines {@link String} auf Integrit�t.
 * 
 * <p>
 * <b>Funktionen:</b>
 * <ul>
 * <li><b>isKVNrValid({@link String})</b>: pr�ft eine KVNr auf syntaktische
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
	 * �berpr�ft eine KVNr (als {@link String}) auf syntaktische Korrektheit und
	 * und gibt anschlie�end ein {@link Boolean} zur�ck.
	 * 
	 * @param kvNr
	 *            {@link String}: Zu �berpr�fende KVNr.
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
