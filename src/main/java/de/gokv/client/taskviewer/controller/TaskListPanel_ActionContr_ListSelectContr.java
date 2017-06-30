package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class TaskListPanel_ActionContr_ListSelectContr extends Abstract_TaskListPanel_Controller implements ActionListener, ListSelectionListener {

	public TaskListPanel_ActionContr_ListSelectContr() {
		super();
	}

	/**
	 * Stellt eine Verbindung zum Client her und lädt mittels der ausgwählten
	 * taskID weitere Informationen und setzt diese ins Formular.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.taskMask.taskLoadBtn && taskMask.taskList.isSelectedIndex(taskMask.taskList.getSelectedIndex())) {
			loadTaskDetails();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			selectTaskItem();
		}

	}

}
