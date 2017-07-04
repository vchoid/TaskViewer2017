package de.gokv.client.taskviewer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Properties;

import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.proxy.MissingProxyPropertyException;
import de.gokv.client.taskviewer.module.http.proxy.ProxyAuthentication;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.Template_InfoCSVPanel;
import de.gokv.client.taskviewer.view.Template_InfoTaskPanel;
import de.gokv.client.taskviewer.view.Template_TaskList;

public class Abstract_TaskListPanel_Controller extends Abstract_MyFrame_Controller {
	protected static HTTPSClient client;
	protected String taskID;
	protected final Template_TaskList taskMask;
	protected final Template_InfoCSVPanel infoCSV;
	protected final Template_InfoTaskPanel infoTask;

	public Abstract_TaskListPanel_Controller() {
		super();
		InputStream stream = Abstract_TaskListPanel_Controller.class.getResourceAsStream("/proxyconfig.properties");
		if(stream != null){
			try {
				Properties properties = new Properties();
				properties.load(stream);
				ProxyAuthentication.initialize(properties);
			} catch (IOException | MissingProxyPropertyException e) {
				//TODO saubere Exception Werfen Client-Configuration Exception
				e.printStackTrace();
			}
		}
			
		if (client == null){
			try {
				client = new HTTPSClient(new URL("https://gokv.bitmarck.de:444/gokv-tenant/api"));
			} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
					| URISyntaxException e) {
				//TODO saubere Exception Werfen Client-Configuration Exception
				e.printStackTrace();
			}
		}
		taskMask = frame.taskMask;
		infoCSV = frame.infoCSV;
		infoTask = frame.infoTask;
	}

	protected void loadTaskDetails() {
		taskID = taskMask.taskList.getSelectedValue();
		client.loadTaskDetails(taskID);
		infoTask.state.setlField(HTTPSClient.task.get("state").toString());
		infoTask.taskType.setlField(HTTPSClient.task.get("type").toString());
		infoTask.orderDate.setlField(DateUtil.datetoString((HTTPSClient.task.get("orderedDate").toString())));
		infoTask.evInProgs.setlField(HTTPSClient.task.get("eventInProgress").toString());
		infoTask.evCompl.setlField(HTTPSClient.task.get("eventCompleted").toString());
		infoTask.evReceived.setlField(HTTPSClient.task.get("eventReceived").toString());
		infoTask.evResult.setlField(HTTPSClient.task.get("eventResult").toString());
	}

	protected void selectTaskItem() {
		taskID = taskMask.taskList.getSelectedValue();
		model.getTaskValueByID(taskID);
		infoCSV.taskID.setlField(taskID);
		infoCSV.kvnr.setlField(model.kvnr);
		infoCSV.name.setlField(model.name);
		infoCSV.vName.setlField(model.vName);
		infoCSV.gebDate.setlField(DateUtil.localDateToString(model.gebDate));
	}
}
