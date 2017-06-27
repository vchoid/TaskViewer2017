package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.MyFrame;
/**
 * 
 * @author Christoph Kiank
 *
 */
public class MyFrameController extends AbstractController{

	/**
	 * Konstruktor legt ein neues Model-Objekt an und dieses startet das lesen der CSV-Datei.
	 * @param frame
	 */
	public MyFrameController(MyFrame frame) {
		super(new MyModel(), frame);
		model.readFiles();
	}
	/**
	 * Gibt die gefilterten Tasks als String Array zur�ck.
	 * @return
	 */
	public String[] getFilteredTasks(){
		return model.getFilteredTaskID();
	}
	
}
