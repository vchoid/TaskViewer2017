package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.Template_FilterCriteriaPanel;
import de.gokv.client.taskviewer.view.Template_InfoCSVPanel;
import de.gokv.client.taskviewer.view.Template_InfoTaskPanel;

public class AbstractFilterTaskListController extends AbstractController {

	private Template_FilterCriteriaPanel criteriaTemplate;
	private Template_InfoCSVPanel infoCSVTemplate;
	private Template_InfoTaskPanel infoTaskTemplate;

	public AbstractFilterTaskListController() {
		super();
		criteriaTemplate = frame.filterMask;
		infoCSVTemplate = frame.infoCSV;
		infoTaskTemplate = frame.infoTask;
	}

	public void setFilteredTask() {
		try {
			Task t = new Task();
			t.setName(criteriaTemplate.pName.getText());
			t.setvName(criteriaTemplate.pVname.getText());
			t.setKvnr(criteriaTemplate.pKvnr.getText());
			t.setTaskId(criteriaTemplate.pTaskID.getText());
			if (criteriaTemplate.model_geb.getValue() != null) {
				t.setGebDat(DateUtil.parseDate(criteriaTemplate.model_geb.getValue()));
			}
			if (criteriaTemplate.model_ord.getValue() != null) {
				t.setOrderedDate(DateUtil.parseDate(criteriaTemplate.model_ord.getValue()));
			}

			model.setFilterCriteria(t);
			frame.taskMask.taskList.setListData(model.getFilteredTaskID());
			if(model.getFilteredTaskID().length == 1){
				criteriaTemplate.anzFiltTask.setText("Einen Eintrag gefunden");
			}else {
				criteriaTemplate.anzFiltTask.setText(model.getFilteredTaskID().length + " Einträge gefunden");
			}

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
		
		criteriaTemplate.pName.setText(null);
		criteriaTemplate.pVname.setText(null);
		criteriaTemplate.pKvnr.setText(null);
		criteriaTemplate.pTaskID.setText(null);
		criteriaTemplate.model_geb.setValue(null);
		criteriaTemplate.model_ord.setValue(null);
		
	}

	private void clearCSVInfoPanel() {
		infoCSVTemplate.taskID_field.setText(null);
		infoCSVTemplate.kvnr.setlField(null);
		infoCSVTemplate.name.setlField(null);
		infoCSVTemplate.vName.setlField(null);
		infoCSVTemplate.gebDate.setlField(null);
	}

	private void clearTaskInfoPanel() {
		infoTaskTemplate.state.setlField(null);
		infoTaskTemplate.taskType.setlField(null);
		infoTaskTemplate.orderDate.setlField(null);
		infoTaskTemplate.evInProgs.setlField(null);
		infoTaskTemplate.evCompl.setlField(null);
		infoTaskTemplate.evReceived.setlField(null);
		infoTaskTemplate.evResult.setlField(null);
	}

}
