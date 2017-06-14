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
	 * Konstruktor
	 * @param frame
	 */
	public MyFrameController(MyFrame frame) {
		this.frame = frame;
		model = new MyModel();
		model.readFiles();
	}
	/**
	 * Gitb die gefilterten Tasks als String Array zurück.
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
