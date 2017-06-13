package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.ui.MyFrame;

public class MyFrameController {

	private MyModel model;
	private final MyFrame frame;
	
	public MyFrameController(MyFrame frame) {
		this.frame = frame;
		model = new MyModel();
		model.readFiles();
	}
	
	public String[] getFilteredTasks(){
		return model.getFilteredTasks();
	}

	public MyModel getModel() {
		return model;
	}
	
}
