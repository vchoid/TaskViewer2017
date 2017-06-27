package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.MyFrame;

public class AbstractController {
	protected static MyModel model;
	protected static MyFrame frame;
	
	public AbstractController(MyModel modelParam, MyFrame frameParam) {
		this();
		model = modelParam;
		frame = frameParam;
	}
	
	public AbstractController() {
	}
	
	/**
	 * Gibt das model aus der Klasse MyModel zurück.
	 * @return
	 */
	public MyModel getModel() {
		return model;
	}
}
