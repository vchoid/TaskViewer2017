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
	 * Stellt eine Verbindung zum Client her und lädt mittels der ausgwählten
	 * taskID weitere Informationen und setzt diese ins Formular.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MyFrame.taskLoadBtn && frame.taskList.isSelectedIndex(frame.taskList.getSelectedIndex())) {
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
	public void valueChanged(ListSelectionEvent e) {
			taskID = frame.taskList.getSelectedValue();
			MyFrame.taskID_field.setText(taskID);
						
		
	}

	

}
