package de.gokv.client.taskviewer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Properties;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
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
	
	private LoadProperties loadProps;
	protected static final String FILE_PROPS = "http.properties";
	protected static final String TITLE_OF_VAL_PROPS = "http.local.url";
//	protected static final String TITLE_OF_VAL_PROPS = "http.url";
	protected final String valProps;
	public String errMsg;

	public Abstract_TaskListPanel_Controller() throws ClientConfigurationExeception {
		super();
		loadProps = new LoadProperties(FILE_PROPS, TITLE_OF_VAL_PROPS);
		valProps = loadProps.getPropertyStringVal();
		InputStream stream = Abstract_TaskListPanel_Controller.class.getResourceAsStream("/proxyconfig.properties");
		if(stream != null){
			try {
				Properties properties = new Properties();
				properties.load(stream);
				ProxyAuthentication.initialize(properties);
				//TODO löschen
//				throw new MissingProxyPropertyException("MissingProxyPropertyException", null);
			} catch (IOException | MissingProxyPropertyException e) {
				throw new ClientConfigurationExeception(e, "Fehlerhafter Proxy", "Die proxyconfig.properties enthält fehlerhafte Einträge.", 3);
			}
		}
			
		if (client == null){
			try {
				client = new HTTPSClient(new URL(valProps));
				//TODO löschen
//				throw new ServerException("ServerException");
			} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
					| URISyntaxException e) {
				throw new ClientConfigurationExeception(e, "Verbindungsfehler zum Server", "Die http.properties enthält fehlerhafte Einträge.", 4);
			}
		}
		taskMask = frame.taskMask;
		infoCSV = frame.infoCSV;
		infoTask = frame.infoTask;
	}

	protected void loadTaskDetails() throws ClientConfigurationExeception {
		taskID = taskMask.taskList.getSelectedValue();
		client.loadTaskDetails(taskID);
		infoTask.state.setlField(HTTPSClient.task.get("state").toString());
		infoTask.taskType.setlField(HTTPSClient.task.get("type").toString());
		
		infoTask.orderDate.setlField(DateUtil.dateWithTimeFromHttpsClientToString("orderedDate"));
		infoTask.evInProgs.setlField(DateUtil.dateWithTimeFromHttpsClientToString("eventInProgress"));
		infoTask.evCompl.setlField(DateUtil.dateWithTimeFromHttpsClientToString("eventCompleted"));
		infoTask.evReceived.setlField(DateUtil.dateWithTimeFromHttpsClientToString("eventReceived"));
		infoTask.evResult.setlField(DateUtil.dateWithTimeFromHttpsClientToString("eventResult"));
		
	}

	protected void selectTaskItem() {
		taskID = taskMask.taskList.getSelectedValue();
		model.getTaskValueByID(taskID);
		infoCSV.kvnr.setlField(model.kvnr);
		infoCSV.orderDate.setlField(DateUtil.localDateToString(model.orderDate));
		infoCSV.name.setlField(model.name);
		infoCSV.vName.setlField(model.vName);
		infoCSV.taskType.setlField(model.taskType);
		infoCSV.gebDate.setlField(DateUtil.localDateToString(model.gebDate));
		infoCSV.taskID.setlField(taskID);
	}
	
	public void clearTaskInfoPanel() {
		infoTask.state.setlField(null);
		infoTask.taskType.setlField(null);
		infoTask.orderDate.setlField(null);
		infoTask.evInProgs.setlField(null);
		infoTask.evCompl.setlField(null);
		infoTask.evReceived.setlField(null);
		infoTask.evResult.setlField(null);
	}
}
