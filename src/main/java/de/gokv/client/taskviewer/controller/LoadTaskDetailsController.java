package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
