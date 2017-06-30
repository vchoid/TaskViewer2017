package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.Template_FilterCriteriaPanel;
import de.gokv.client.taskviewer.view.Template_InfoCSVPanel;
import de.gokv.client.taskviewer.view.Template_InfoTaskPanel;

public class Abstract_FilterCriteria_Controller extends Abstract_MyFrame_Controller {

	private Template_FilterCriteriaPanel criteriaTemplate;
	private Template_InfoCSVPanel infoCSVTemplate;
	private Template_InfoTaskPanel infoTaskTemplate;

	public Abstract_FilterCriteria_Controller() {
		super();
		criteriaTemplate = frame.filterMask;
		infoCSVTemplate = frame.infoCSV;
		infoTaskTemplate = frame.infoTask;
	}

	public void setFilteredTask() {
		try {
			Task t = new Task();
			t.setName(criteriaTemplate.namePh.getText());
			t.setvName(criteriaTemplate.vnamePh.getText());
			t.setKvnr(criteriaTemplate.kvnrPh.getText());
			t.setTaskId(criteriaTemplate.taskIdPh.getText());
			if (criteriaTemplate.gebDateModel.getValue() != null) {
				t.setGebDat(DateUtil.parseDate(criteriaTemplate.gebDateModel.getValue()));
			}
			if (criteriaTemplate.orderDateModel.getValue() != null) {
				t.setOrderedDate(DateUtil.parseDate(criteriaTemplate.orderDateModel.getValue()));
			}

			model.setFilterCriteria(t);
			frame.taskMask.taskList.setListData(model.getFilteredTaskID());
			if(model.getFilteredTaskID().length == 1){
				criteriaTemplate.anzFiltEntriesTf.setText("Einen Eintrag gefunden");
			}else {
				criteriaTemplate.anzFiltEntriesTf.setText(model.getFilteredTaskID().length + " Einträge gefunden");
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
		
		criteriaTemplate.namePh.setText(null);
		criteriaTemplate.vnamePh.setText(null);
		criteriaTemplate.kvnrPh.setText(null);
		criteriaTemplate.taskIdPh.setText(null);
		criteriaTemplate.gebDateModel.setValue(null);
		criteriaTemplate.orderDateModel.setValue(null);
		
	}

	private void clearCSVInfoPanel() {
		infoCSVTemplate.taskID.setlField(null);
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
