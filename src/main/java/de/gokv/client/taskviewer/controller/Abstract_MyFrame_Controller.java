package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.Frame_TaskViewer;

public class Abstract_MyFrame_Controller {
	protected static MyModel model;
	protected static Frame_TaskViewer frame;
	
	
	public Abstract_MyFrame_Controller(MyModel modelParam, Frame_TaskViewer frameParam) {
		this();
		model = modelParam;
		frame = frameParam;
		
	}
	
	public Abstract_MyFrame_Controller() {
	}
	
	/**
	 * Gibt das model aus der Klasse MyModel zurück.
	 * @return
	 */
	public MyModel getModel() {
		return model;
	}
}
