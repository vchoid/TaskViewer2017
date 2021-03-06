package de.gokv.client.taskviewer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.view.FExceptionDialogFrame;

/**
 * 
 * 
 * @author Christoph Kiank
 *
 */
public class TaskListPanelActionContrListSelectContr extends AbstractTaskListPanelController implements ActionListener, ListSelectionListener {

	public TaskListPanelActionContrListSelectContr() throws ClientConfigurationExeception {
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
				FExceptionDialogFrame.showException(e1);
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
