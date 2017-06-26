package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDate;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.MyFrame;

/**
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class LoadTaskDetailsController implements ActionListener, ListSelectionListener {

	private HTTPSClient client;
	private MyFrame frame;
	private MyModel model;
	private LocalDate lDate;
	private String strDate;
	public static String taskID;

	public LoadTaskDetailsController(MyFrame frame, MyModel model) {
		super();
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
	
	/**
	 * Stellt eine Verbindung zum Client her und l�dt mittels der ausgw�hlten
	 * taskID weitere Informationen und setzt diese ins Formular.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MyFrame.taskLoadBtn && frame.taskList.isSelectedIndex(frame.taskList.getSelectedIndex())) {
				iniClient();
				MyFrame.state.setlField(HTTPSClient.task.get("state").toString());
				MyFrame.taskType.setlField(HTTPSClient.task.get("type").toString());
				MyFrame.orderDate.setlField(DateUtil.datetoString((HTTPSClient.task.get("orderedDate").toString())));
				MyFrame.evInProgs.setlField(HTTPSClient.task.get("eventInProgress").toString());
				MyFrame.evCompl.setlField(HTTPSClient.task.get("eventCompleted").toString());
				MyFrame.evReceived.setlField(HTTPSClient.task.get("eventReceived").toString());
				MyFrame.evResult.setlField(HTTPSClient.task.get("eventResult").toString());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//TODO Inhalt soll auch bei Benutzung durch die Pfeiltasten angezeigt werden
		if(e.getValueIsAdjusting()){
			taskID = frame.taskList.getSelectedValue();
			model.getTaskValueByID(taskID);
			MyFrame.taskID_field.setText(taskID);
			MyFrame.kvnr.setlField(model.kvnr);
			MyFrame.name.setlField(model.name);
			MyFrame.vName.setlField(model.vName);
			MyFrame.gebDate.setlField(DateUtil.localDateToString(model.gebDate));
		}
			// L�schen ---------------------------------------------------------------------------------------------
//			iniClient();
//			MyFrame.orderDate_field.setText(DateUtil.datetoString((HTTPSClient.task.get("orderedDate").toString())));
//			MyFrame.state_field.setText(HTTPSClient.task.get("state").toString());
//			MyFrame.taskType_field.setText(HTTPSClient.task.get("type").toString());
//			MyFrame.evInProgs_field.setText(HTTPSClient.task.get("eventInProgress").toString());
//			MyFrame.evCompl_field.setText(HTTPSClient.task.get("eventCompleted").toString());
//			MyFrame.evReceived_field.setText(HTTPSClient.task.get("eventReceived").toString());
//			MyFrame.evResult_field.setText(HTTPSClient.task.get("eventResult").toString());
			//-------------------------------------------------------------------------------------------------------
		
	}

	

}
