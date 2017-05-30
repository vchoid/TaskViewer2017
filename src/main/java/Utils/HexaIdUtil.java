package Utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Hat eine Methode zum prüfen eines {@link String} auf Integrität.
 * 
 * <p>
 * <b>Funktionen:</b>
 * <ul>
 * <li><b>isIdValid({@link String})</b>: prüft eine Id auf syntaktische
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
	 * Überprüft eine Id (als {@link String}) auf syntaktische Korrektheit einer
	 * Hexadezimalzahl und gibt anschließend ein {@link Boolean} zurück.
	 * 
	 * @param id
	 *            {@link String}: Zu überprüfende Id.
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
