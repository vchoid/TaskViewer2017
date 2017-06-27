package de.gokv.client.taskviewer.controller;

import de.gokv.client.taskviewer.Task;
import de.gokv.client.taskviewer.exceptions.InvalidDateException;
import de.gokv.client.taskviewer.utils.DateUtil;
import de.gokv.client.taskviewer.view.FilterCriteriaTemplate;
import de.gokv.client.taskviewer.view.MyFrame;

public class AbstractFilterTaskListController extends AbstractController{
	
	FilterCriteriaTemplate criteria;
	
	public AbstractFilterTaskListController() {
		super();
		criteria = frame.filterMask;
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
			criteria.anzFiltTask.setText(model.getFilteredTaskID().length +" Einträge");

		} catch (InvalidDateException e1) {
			// DO NOTHING
			e1.printStackTrace();
		}
	}

	public void resetForm() {
		// Filter-Panel
		clearCriterias();
		// CSV Info-Panel
//		clearCSVInfoPanel();
		// Task Info-Panel
//		clearTaskInfoPanel();
	}

	private void clearCriterias(){
		criteria.pName.setText(null);
		criteria.pVname.setText(null);
		criteria.pKvnr.setText(null);
		criteria.pTaskID.setText(null);
		criteria.model_geb.setValue(null);
		criteria.model_ord.setValue(null);
	}
	
//	private void clearCSVInfoPanel(){
//		MyFrame.taskID_field.setText(null);
//		MyFrame.kvnr.setlField(null);
//		MyFrame.name.setlField(null);
//		MyFrame.vName.setlField(null);
//		MyFrame.gebDate.setlField(null);
//	}
//	private void clearTaskInfoPanel(){
//		frame.state.setlField(null);
//		frame.taskType.setlField(null);
//		frame.orderDate.setlField(null);
//		frame.evInProgs.setlField(null);
//		frame.evCompl.setlField(null);
//		frame.evReceived.setlField(null);
//		frame.evResult.setlField(null);
//	}
	
}
