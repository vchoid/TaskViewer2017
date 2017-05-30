package Utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Hat eine Methode zum pr�fen eines {@link String} auf Integrit�t.
 * 
 * <p>
 * <b>Funktionen:</b>
 * <ul>
 * <li><b>isIdValid({@link String})</b>: pr�ft eine Id auf syntaktische
 * Korrektheit einer Hexadezimalzahl</li>
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * 
 *
 */
public class HexaIdUtil {

	/**
	 * �berpr�ft eine Id (als {@link String}) auf syntaktische Korrektheit einer
	 * Hexadezimalzahl und gibt anschlie�end ein {@link Boolean} zur�ck.
	 * 
	 * @param id
	 *            {@link String}: Zu �berpr�fende Id.
	 * @return
	 * 		<ul>
	 *         <li><i>true</i>, wenn der String im korrektem Format vorliegt.
	 *         </li>
	 *         <li><i>false</i>, wenn der String im nicht korrektem Format
	 *         vorliegt.</li>
	 *         </ul>
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
