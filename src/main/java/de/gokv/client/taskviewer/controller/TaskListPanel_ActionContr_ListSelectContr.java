package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.view.Frame_ExceptionDialog;

/**
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class TaskListPanel_ActionContr_ListSelectContr extends Abstract_TaskListPanel_Controller implements ActionListener, ListSelectionListener {

	public TaskListPanel_ActionContr_ListSelectContr() throws ClientConfigurationExeception {
		super();
	}

	/**
	 * Stellt eine Verbindung zum Client her und l�dt mittels der ausgw�hlten
	 * taskID weitere Informationen und setzt diese ins Formular.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.taskMask.taskLoadBtn.getButton() && taskMask.taskList.isSelectedIndex(taskMask.taskList.getSelectedIndex())) {
			try {
				loadTaskDetails();
			} catch (ClientConfigurationExeception e1) {
				Frame_ExceptionDialog.showException(e1);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		clearTaskInfoPanel();
		if (e.getValueIsAdjusting()) {
			selectTaskItem();
		}

	}

}
