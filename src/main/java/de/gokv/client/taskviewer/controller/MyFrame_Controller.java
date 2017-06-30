package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.MyFrame;
/**
 * 
 * @author Christoph Kiank
 *
 */
public class MyFrame_Controller extends Abstract_MyFrame_Controller{

	/**
	 * Konstruktor legt ein neues Model-Objekt an und dieses startet das lesen der CSV-Datei.
	 * @param frame
	 */
	public MyFrame_Controller(MyFrame frame) {
		super(new MyModel(), frame);
		model.readFiles();
	}
	/**
	 * Gibt die gefilterten Tasks als String Array zurück.
	 * @return
	 */
	public String[] getFilteredTasks(){
		return model.getFilteredTaskID();
	}
	
}
