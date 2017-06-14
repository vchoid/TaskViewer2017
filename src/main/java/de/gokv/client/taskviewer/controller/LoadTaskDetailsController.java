package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.view.MyFrame;

public class LoadTaskDetailsController implements ActionListener{

	HTTPSClient client;
	MyFrame frame;
	MyModel model;
	
	public LoadTaskDetailsController(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}
	
//TODO: Implementierung der Klasse, die für den Serveraufruf zuständig ist
//TODO: Erstellen dieser Klasse, Tipp: blick in gokv-client-base httpClient
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
