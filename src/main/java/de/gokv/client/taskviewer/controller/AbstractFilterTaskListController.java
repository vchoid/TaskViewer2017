package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.FilterCriteriaTemplate;
import de.gokv.client.taskviewer.view.InfoCSVTemplate;
import de.gokv.client.taskviewer.view.InfoTaskTemplate;

public class AbstractFilterTaskListController extends AbstractController {

	private FilterCriteriaTemplate criteria;
	private InfoCSVTemplate infoCSV;
	private InfoTaskTemplate infoTask;

	public AbstractFilterTaskListController() {
		super();
		criteria = frame.filterMask;
		infoCSV = frame.infoCSV;
		infoTask = frame.infoTask;
	}

	public void setFilteredTask() {
		try {
			Task t = new Task();
			t.setName(criteria.pName.getText());
			t.setvName(criteria.pVname.getText());
			t.setKvnr(criteria.pKvnr.getText());
			t.setTaskId(criteria.pTaskID.getText());
			if (criteria.model_geb.getValue() != null) {
				t.setGebDat(DateUtil.parseDate(criteria.model_geb.getValue()));
			}
			if (criteria.model_ord.getValue() != null) {
				t.setOrderedDate(DateUtil.parseDate(criteria.model_ord.getValue()));
			}

			model.setFilterCriteria(t);
			frame.taskMask.taskList.setListData(model.getFilteredTaskID());
			criteria.anzFiltTask.setText(model.getFilteredTaskID().length + " Einträge");

		} catch (InvalidDateException e1) {
			// DO NOTHING
			e1.printStackTrace();
		}
	}

	public void resetForm() {
		// Filter-Panel
		clearCriterias();
		// CSV Info-Panel
		 clearCSVInfoPanel();
		// Task Info-Panel
		 clearTaskInfoPanel();
	}

	private void clearCriterias() {
		criteria.pName.setText(null);
		criteria.pVname.setText(null);
		criteria.pKvnr.setText(null);
		criteria.pTaskID.setText(null);
		criteria.model_geb.setValue(null);
		criteria.model_ord.setValue(null);
	}

	private void clearCSVInfoPanel() {
		infoCSV.taskID_field.setText(null);
		infoCSV.kvnr.setlField(null);
		infoCSV.name.setlField(null);
		infoCSV.vName.setlField(null);
		infoCSV.gebDate.setlField(null);
	}

	private void clearTaskInfoPanel() {
		infoTask.state.setlField(null);
		infoTask.taskType.setlField(null);
		infoTask.orderDate.setlField(null);
		infoTask.evInProgs.setlField(null);
		infoTask.evCompl.setlField(null);
		infoTask.evReceived.setlField(null);
		infoTask.evResult.setlField(null);
	}

}
