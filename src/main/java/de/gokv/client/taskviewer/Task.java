package de.gokv.client.taskviewer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import de.gokv.client.taskviewer.exceptions.InvalidCSVRecordException;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;

/**
 * Erzeugt einen Task aus einer validen Zeile einer CSV-Datei.
 * 
 * <p>
 * <b>Konstruktor:</b>
 * <ul>
 * <li><b>Task()</b>: ...</li>
 * 
 * </ul>
 * </p>
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li><b>{@link #createTaskFromRecord(CSVRecord record)}</b>: Legt eine
 * Variable der Klasse {@link Task} an. Holt ein Wert einer Zeile aus der CSV
 * mit der {@link CSVReader#getValue(CSVRecord, String) getValue}-Methode oder
 * {@link CSVReader#getValueAsDate(CSVRecord, String, boolean) getValueAsDate}
 * -Methode und speichert ihn in einer Variable im passenden Format.</li>
 * <li><b>{@link #matchesCriteria(Task)}</b>: Filtern der Werte nach Kriterien.</li>
 * 
 * </ul>
 * </p>
 * <p>
 * <b>Getter-Methoden:</b>
 * <ul>
 * <li><b>{@link #getGebDat()}</b>: {@link LocalDate} im <u>Format: "dd.MM.yyyy"
 * </li>
 * <li><b>{@link #getKvnr()}</b>: String im <u>Format: [A-Z]{1}[0-9]{9}</u></li>
 * <li><b>{@link #getName()}</b>: String im <u>Format: [^0-9]*</u></li>
 * <li><b>{@link #getOrderedDate()}</b>: {@link LocalDate} im <u>Format:
 * "dd.MM.yyyy"</u></li>
 * <li><b>{@link #getTaskId()} </b>: String im <u>Format: [A-F0-9]{32}</u></li>
 * <li><b>{@link #getTaskType()}</b>: String im <u>Format: [^0-9]*</u></li>
 * <li><b>{@link #getTitel()}</b>: String im <u>Format: [^0-9]*</u></li>
 * <li><b>{@link #getvName()}</b>: String im <u>Format: [^0-9]*</u></li>
 * <li><b>{@link #getVsWort()}</b>: String im <u>Format: [^0-9]*</u></li>
 * <li><b>{@link #getZsWort()}</b>: String im <u>Format: [^0-9]*</u></li>
 * </ul>
 * </p>
 * <p>
 * <b>Setter-Methoden:</b>
 * <ul>
 * <li><b>{@link #setGebDat(LocalDate)} </b>: ...</u></li>
 * <li><b>{@link #setKvnr(String)}</b>: ...</u></li>
 * <li><b>{@link #setName(String)}</b>: ...</u></li>
 * <li><b>{@link #setOrderedDate(LocalDate)}</b>: ...</u></li>
 * <li><b>{@link #setTaskId(String)}</b>: ...</u></li>
 * <li><b>{@link #setTaskType(String)}</b>: ...</u></li>
 * <li><b>{@link #setTitel(String)}</b>: ...</u></li>
 * <li><b>{@link #setvName(String)}</b>: ...</u></li>
 * <li><b>{@link #setVsWort(String)}</b>: ...</u></li>
 * <li><b>{@link #setZsWort(String)}</b>: ...</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 * 
 */
public class Task {

	// Header der CSV
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private LocalDate orderedDate;
	private LocalDate gebDat;
	private String taskId;
	private String taskType;
	private String kvnr;
	private String name;
	private String vName;
	private String titel;
	private String zsWort;
	private String vsWort;

	// Pattern für valide KvNr, TaskId, Namensgebung
	// +++++++++++++++++++++++++++++++++++++++++++
	protected final static Pattern PATTERN_KVNR = Pattern.compile("[A-Z]{1}[0-9]{9}");
	protected final static Pattern PATTERN_TASKID = Pattern.compile("[A-F0-9]{32}");
	protected final static Pattern PATTERN_NONUMB = Pattern.compile("[^0-9]*");
	// TaskTypes
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * <p>
	 * <b>Tasktypen</b>
	 * <ul>
	 * <li>famv_bestand</li>
	 * <li>eka</li>
	 * <li>unf</li>
	 * <li>kvv</li>
	 * 
	 * </ul>
	 * </p>
	 */
	protected final static ArrayList<String> TASK_TYPES = new ArrayList<String>(
			Arrays.asList("famv_bestand", "eka", "unf", "kvv"));

	private static int countTask = 0;

	/**
	 * Legt eine Variable der Klasse {@link Task} an. Holt ein Wert einer Zeile
	 * aus der CSV mit der {@link CSVReader#getValue(CSVRecord, String)
	 * getValue}-Methode oder
	 * {@link CSVReader#getValueAsDate(CSVRecord, String, boolean)
	 * getValueAsDate}-Methode und speichert ihn in einer Variable im passenden
	 * Format.
	 * 
	 * @param record
	 *            Inhalt einer Zeile der gelesenen CSV-Datei.
	 * @return Eine Zeile aus der CSV-Datei als {@link Task}-Objekt.
	 * @throws InvalidCSVRecordException
	 */
	public static Task createTaskFromRecord(CSVRecord record) throws InvalidCSVRecordException {

		Task t = new Task();

		try {

			t.gebDat = CSVReader.getValueAsDate(record, CSVReader.COL_MITGLIED_GEB_DAT, true);
			t.orderedDate = CSVReader.getValueAsDate(record, CSVReader.COL_ORDERED_DATE, true);
			t.taskId = CSVReader.getValue(record, CSVReader.COL_TASK_ID, true, PATTERN_TASKID);
			t.taskType = CSVReader.getMappedValue(record, CSVReader.COL_TASK_TYPE, true, TASK_TYPES);
			t.kvnr = CSVReader.getValue(record, CSVReader.COL_MITGLIED_KVNR, true, PATTERN_KVNR);
			t.name = CSVReader.getValue(record, CSVReader.COL_MITGLIED_NAME, true, PATTERN_NONUMB);
			t.vName = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VORNAME, true, PATTERN_NONUMB);

			t.titel = CSVReader.getValue(record, CSVReader.COL_MITGLIED_TITEL, false, PATTERN_NONUMB);
			t.zsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_ZSWORT, false, PATTERN_NONUMB);
			t.vsWort = CSVReader.getValue(record, CSVReader.COL_MITGLIED_VSWORT, false, PATTERN_NONUMB);

		} catch (InvalidDateException e) {
			throw new InvalidCSVRecordException(e, record.getRecordNumber());
		}

		return t;
	}

	// Konstruktor ++++++++++++++++++++++++++++++++++++++
	public Task() {
		super();
	}

	@Override
	public String toString() {
		return " _____________________________________  " + ++countTask
				+ ". Task  ______________________________________\n"
				+ "|______________________________________________________________________________________|\n"
				+ "\nDatei: ../" + CSVReader.getFilePath().substring(70) + "\n\n" + "Id: " + taskId + " [Typ: "
				+ taskType + "] erstellt am: " + orderedDate.getDayOfMonth() + "." + orderedDate.getMonthValue() + "."
				+ orderedDate.getYear() + "\nKVNr.: " + kvnr + "\n" + "Name: " + titel + name + ", " + vName
				+ "\ngeboren am: " + gebDat.getDayOfMonth() + "." + gebDat.getMonthValue() + "." + gebDat.getYear()
				+ "\nzsWort: " + zsWort + "\nvsWort: \n" + vsWort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kvnr == null) ? 0 : kvnr.hashCode());
		result = prime * result + ((orderedDate == null) ? 0 : orderedDate.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + ((taskType == null) ? 0 : taskType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (kvnr == null) {
			if (other.kvnr != null)
				return false;
		} else if (!kvnr.equals(other.kvnr))
			return false;
		if (orderedDate == null) {
			if (other.orderedDate != null)
				return false;
		} else if (!orderedDate.equals(other.orderedDate))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (taskType == null) {
			if (other.taskType != null)
				return false;
		} else if (!taskType.equals(other.taskType))
			return false;
		return true;
	}

	// AUTOGENERATED METHODS Getter & SETTER
	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	public LocalDate getGebDat() {
		return gebDat;
	}

	public void setGebDat(LocalDate gebDat) {
		this.gebDat = gebDat;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getKvnr() {
		return kvnr;
	}

	public void setKvnr(String kvnr) {
		this.kvnr = kvnr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZsWort() {
		return zsWort;
	}

	public void setZsWort(String zsWort) {
		this.zsWort = zsWort;
	}

	public String getVsWort() {
		return vsWort;
	}

	public void setVsWort(String vsWort) {
		this.vsWort = vsWort;
	}

	//TODO Filter anpassen auf Wildcard
	
	/**
	 * Filtern der Werte nach Kriterien.
	 * Wenn Länge der Eingabe in den Felder größer als 0 ist und Kritierien
	 * nicht übereinstimmen wird false zurückgegeben. Ansonsten wird true zurückgegeben.
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean matchesCriteria(Task criteria) {
		if (criteria.name.length() > 0 && !criteria.name.equals(this.name))
			return false;
		if (criteria.vName.length() > 0 && !criteria.vName.equals(this.vName))
			return false;
		if (criteria.kvnr.length() > 0 && !criteria.kvnr.equals(this.kvnr))
			return false;
		if (criteria.taskId.length() > 0 && !criteria.taskId.equals(this.taskId))
			return false;
		if (criteria.gebDat != null && !criteria.gebDat.equals(this.gebDat))
			return false;
		if (criteria.orderedDate != null && !criteria.orderedDate.equals(this.orderedDate))
			return false;
		return true;
	}

}
