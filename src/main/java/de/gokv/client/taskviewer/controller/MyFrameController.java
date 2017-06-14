package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.ui.MyFrame;
/**
 * 
 * @author Christoph Kiank
 *
 */
public class MyFrameController {

	private MyModel model;
	private final MyFrame frame;
	/**
	 * Konstruktor legt ein neues Model-Objekt an und dieses startet das lesen der CSV-Datei.
	 * @param frame
	 */
	public MyFrameController(MyFrame frame) {
		this.frame = frame;
		model = new MyModel();
		model.readFiles();
	}
	/**
	 * Gibt die gefilterten Tasks als String Array zurück.
	 * @return
	 */
	public String[] getFilteredTasks(){
		return model.getFilteredTasks();
	}
	/**
	 * Gibt das model aus der Klasse MyModel zurück.
	 * @return
	 */
	public MyModel getModel() {
		return model;
	}
	
}
