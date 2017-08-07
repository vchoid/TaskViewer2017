package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.CSVReader;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.view.FTaskViewerFrame;

public class AbstractMyFrameController {
	protected static MyModel model;
	protected static FTaskViewerFrame frame;
	
	
	public AbstractMyFrameController(MyModel modelParam, FTaskViewerFrame frameParam) {
		this();
		model = modelParam;
		frame = frameParam;
		
	}
	
	public AbstractMyFrameController() {
	}
	
	/**
	 * Gibt das model aus der Klasse MyModel zurück.
	 * @return
	 */
	public MyModel getModel() {
		return model;
	}
}
