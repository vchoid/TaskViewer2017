package de.gokv.client.taskviewer.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;

import de.gokv.client.taskviewer.module.http.HTTPSClient;
import de.gokv.client.taskviewer.module.http.ssl.ClientCertificateException;
import de.gokv.client.taskviewer.module.http.ssl.ServerException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.InfoCSVTemplate;
import de.gokv.client.taskviewer.view.InfoTaskTemplate;
import de.gokv.client.taskviewer.view.MyFrame;
import de.gokv.client.taskviewer.view.TaskListTemplate;

public class AbstractLoadTaskDetailsController extends AbstractController {
	protected static HTTPSClient client;
	protected String taskID;
	protected final TaskListTemplate taskMask;
	protected final InfoCSVTemplate infoCSV;
	protected final InfoTaskTemplate infoTask;

	public AbstractLoadTaskDetailsController() {
		super();
//		if (client == null)
			try {
				client = new HTTPSClient(new URL("http://localhost:9080/gokv-tenant/api"));
			} catch (ServerException | GeneralSecurityException | IOException | ClientCertificateException
					| URISyntaxException e) {
				e.printStackTrace();
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
		infoCSV.taskID_field.setText(taskID);
		infoCSV.kvnr.setlField(model.kvnr);
		infoCSV.name.setlField(model.name);
		infoCSV.vName.setlField(model.vName);
		infoCSV.gebDate.setlField(DateUtil.localDateToString(model.gebDate));
	}
}
