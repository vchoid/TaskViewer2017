package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import de.gokv.client.taskviewer.view.MyFrame;

public class LoadTaskDetailsController implements ActionListener, ListSelectionListener{

	private HTTPSClient client;
	MyFrame frame;
	MyModel model;
	String strDate;
	Date date;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public static String taskID;
	
	public LoadTaskDetailsController(MyFrame frame, MyModel model) {
		super();
		this.frame = frame;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			client = new HTTPSClient(new URL("http://localhost:9080/gokv-tenant/api"));
			client.testConnection(taskID);
			MyFrame.state_field.setText(HTTPSClient.task.get("state").toString());
			MyFrame.taskType_field.setText(HTTPSClient.task.get("type").toString());
			MyFrame.orderDate_field.setText(HTTPSClient.task.get("orderedDate").toString());
			strDate = HTTPSClient.task.get("orderedDate").toString();
			date = df.parse(strDate);
			System.out.println(date.getClass());
			
			MyFrame.evInProgs_field.setText(HTTPSClient.task.get("eventInProgress").toString());
			MyFrame.evCompl_field.setText(HTTPSClient.task.get("eventCompleted").toString());
			MyFrame.evReceived_field.setText(HTTPSClient.task.get("eventReceived").toString());
			MyFrame.evResult_field.setText(HTTPSClient.task.get("eventResult").toString());
		} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
				| URISyntaxException ex) {
			ex.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			taskID = frame.taskList.getSelectedValue();
			MyFrame.taskID_field.setText(taskID);
			
			
			
			
			
			
			
		}
	}

}
