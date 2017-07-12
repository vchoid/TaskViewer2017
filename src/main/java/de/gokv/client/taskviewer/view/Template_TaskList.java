package de.gokv.client.taskviewer.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.oxbow.swingbits.dialog.task.TaskDialogs;

import de.gokv.client.taskviewer.controller.FrameTaskViewer_Controller;
import de.gokv.client.taskviewer.controller.TaskListPanel_ActionContr;
import de.gokv.client.taskviewer.controller.TaskListPanel_ActionContr_ListSelectContr;
import de.gokv.client.taskviewer.controller.TaskListPanel_KeyContr;
import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;

public class Template_TaskList extends Template_BlockPanel {
	private static final long serialVersionUID = 1L;

	public Pattern_GridBagButton taskLoadBtn;
	public Pattern_GridBagButton reloadBtn;

	public JList<String> taskList;
	public JTextField anzTasks;

	private Image iconReload;
	private String pathIconReload;
	private ImageIcon iconReloadScaled;

	private Throwable throwEx;

	protected Template_TaskList(FrameTaskViewer_Controller controller) {
		super("Tasks", controller);
	}

	@Override
	public GridBagLayout getLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 210, 10 };
		layout.rowHeights = new int[] { 40, 0, 177, 35, 40 };
		return layout;
	}

	@Override
	public void init() {
		TaskListPanel_ActionContr taskPanelContr = new TaskListPanel_ActionContr();
		TaskListPanel_ActionContr_ListSelectContr taskListSelcContr = null;
		TaskListPanel_KeyContr taskListKeyContr = null;
		try {
			taskListSelcContr = new TaskListPanel_ActionContr_ListSelectContr();
			taskListKeyContr = new TaskListPanel_KeyContr();
		} catch (ClientConfigurationExeception e) {
			new Frame_ExceptionMsg();
			Frame_ExceptionMsg.setMessageDialog(e);
//			throwEx = e;
		}

		// << Task-Liste >>
		taskList = new JList<>(controller.getFilteredTasks());
		taskList.setVisibleRowCount(8);
		taskList.setFont(fontTxt);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(taskListSelcContr);
		taskList.addKeyListener(taskListKeyContr);
		JScrollPane scrollTask = new JScrollPane(taskList);
		GridBagConstraints gbc_scrollTask = new GridBagConstraints();
		gbc_scrollTask.anchor = GridBagConstraints.NORTH;
		gbc_scrollTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollTask.gridwidth = 2;
		gbc_scrollTask.gridx = 1;
		gbc_scrollTask.gridy = 2;
		this.add(scrollTask, gbc_scrollTask);

		// << Tasks laden - Button >>
		taskLoadBtn = new Pattern_GridBagButton(" Task laden ", 1,3,1, taskListSelcContr);
		taskLoadBtn.getGbc_button().insets = new Insets(20, 0, 0, 5);
		pathIconReload = "/reloadIcon/reload_white.png";
		iconReload = new ImageIcon(getClass().getResource(pathIconReload)).getImage();
		iconReloadScaled = new ImageIcon(iconReload);
		taskLoadBtn.getButton().setIcon(iconReloadScaled);
		this.add(taskLoadBtn.getButton(), taskLoadBtn.getGbc_button());
		
		// << Tasksliste Neu Laden - Button >>
		reloadBtn = new Pattern_GridBagButton(" Liste neu laden  ", 2, 3, 1, taskPanelContr);
		reloadBtn.getGbc_button().insets = new Insets(20, 10, 0, 0);
		pathIconReload = "/downloadIcon/download_white.png";
		iconReload = new ImageIcon(getClass().getResource(pathIconReload)).getImage();
		iconReloadScaled = new ImageIcon(iconReload);
		reloadBtn.getButton().setIcon(iconReloadScaled);
		this.add(reloadBtn.getButton(), reloadBtn.getGbc_button());

		// << Anzahl der TaskID´s in der Liste und der Fehlerhaften >>
		anzTasks = new JTextField(TaskListPanel_ActionContr.getValidEntries() + TaskListPanel_ActionContr.getInvalidEntries());
		anzTasks.setForeground(colorEntryTxt);
		anzTasks.setBorder(emptyBorder);
		anzTasks.setOpaque(false);
		anzTasks.addActionListener(taskPanelContr);
		this.add(anzTasks);
		GridBagConstraints gbc_anzTask = new GridBagConstraints();
		gbc_anzTask.anchor = GridBagConstraints.NORTH;
		gbc_anzTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzTask.insets = new Insets(5, 10, 0, 0);
		gbc_anzTask.gridwidth = 2;
		gbc_anzTask.gridx = 2;
		gbc_anzTask.gridy = 4;
		this.add(anzTasks, gbc_anzTask);
		
		
		
//		if(throwEx != null){
//			TaskDialogs.showException(throwEx);
//			new Frame_ExceptionMsg().setMessageDialog(throwEx);
//		}
	}

}
