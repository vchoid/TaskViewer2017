package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.MyFrame;

public class Abstract_MyFrame_Controller {
	protected static MyModel model;
	protected static MyFrame frame;
	
	public Abstract_MyFrame_Controller(MyModel modelParam, MyFrame frameParam) {
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
