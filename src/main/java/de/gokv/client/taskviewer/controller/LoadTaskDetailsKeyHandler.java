package de.gokv.client.taskviewer.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDate;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.MyFrame;

public class LoadTaskDetailsKeyHandler implements KeyListener{

	private HTTPSClient client;
	private MyFrame frame;
	private MyModel model;
	private LocalDate lDate;
	private String strDate;
	public static String taskID;
	
	
	public LoadTaskDetailsKeyHandler(MyFrame frame, MyModel model) {
		this.frame = frame;
		this.model = model;
	}

	public void iniClient(){
		try {
			client = new HTTPSClient(new URL("http://localhost:9080/gokv-tenant/api"));
			client.testConnection(taskID);
		} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
				| URISyntaxException ex) {
			System.out.println(taskID + " nicht gefunden");
			ex.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && frame.taskList.isSelectedIndex(frame.taskList.getSelectedIndex())) {
			taskID = frame.taskList.getSelectedValue();
			iniClient();
			MyFrame.orderDate_field.setText(DateUtil.datetoString((HTTPSClient.task.get("orderedDate").toString())));
			MyFrame.state_field.setText(HTTPSClient.task.get("state").toString());
			MyFrame.taskType_field.setText(HTTPSClient.task.get("type").toString());
			MyFrame.evInProgs_field.setText(HTTPSClient.task.get("eventInProgress").toString());
			MyFrame.evCompl_field.setText(HTTPSClient.task.get("eventCompleted").toString());
			MyFrame.evReceived_field.setText(HTTPSClient.task.get("eventReceived").toString());
			MyFrame.evResult_field.setText(HTTPSClient.task.get("eventResult").toString());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	

}
