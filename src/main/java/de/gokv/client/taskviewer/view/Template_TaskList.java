package de.gokv.client.taskviewer.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import de.gokv.client.taskviewer.controller.TaskListPanel_ActionContr_ListSelectContr;
import de.gokv.client.taskviewer.controller.TaskListPanel_KeyContr;
import de.gokv.client.taskviewer.exceptions.ClientConfigurationExeception;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import de.gokv.client.taskviewer.controller.MyFrame_Controller;
import de.gokv.client.taskviewer.controller.TaskListPanel_ActionContr;

public class Template_TaskList extends Template_BlockPanel{
	private static final long serialVersionUID = 1L;

	public JButton taskLoadBtn;
	public JButton reloadBtn;
	public JList<String> taskList;
	public JTextField anzTasks;
	
	private Image iconReload;
	private String pathIconReload;
	private ImageIcon iconReloadScaled;
	
	protected Template_TaskList(MyFrame_Controller controller) {
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
	public void init()  {
		TaskListPanel_ActionContr taskPanelContr = new TaskListPanel_ActionContr();
		TaskListPanel_ActionContr_ListSelectContr taskListSelcContr;
		TaskListPanel_KeyContr taskListKeyContr;
		try {
			taskListKeyContr = new TaskListPanel_KeyContr();
			taskListSelcContr = new TaskListPanel_ActionContr_ListSelectContr();
		} catch (ClientConfigurationExeception e) {
			//TODO optionPane mit errMsg
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

		// << Tasks Reload Button >>
		reloadBtn = new JButton(" Liste neu laden  ");
		pathIconReload = "/reload.png";
		iconReload = new ImageIcon(getClass().getResource(pathIconReload)).getImage();
		iconReloadScaled = new ImageIcon(iconReload.getScaledInstance(18, 18, 0));
		reloadBtn.setIcon(iconReloadScaled);
		reloadBtn.setBorder(btnBorder);
		reloadBtn.setBackground(colorBtnBG);
		reloadBtn.setForeground(colorBtnTxt);
		reloadBtn.setFont(fontTxt);
		reloadBtn.addActionListener(taskPanelContr);
		GridBagConstraints gbc_reloadTaskBtn = new GridBagConstraints();
		gbc_reloadTaskBtn.anchor = GridBagConstraints.NORTH;
		gbc_reloadTaskBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_reloadTaskBtn.insets = new Insets(20, 10, 0, 0);
		gbc_reloadTaskBtn.gridx = 2;
		gbc_reloadTaskBtn.gridy = 3;
		this.add(reloadBtn, gbc_reloadTaskBtn);

		// << Tasks laden -Button >>
		taskLoadBtn = new JButton("Task laden");
		taskLoadBtn.setBorder(btnBorder);
		taskLoadBtn.setBackground(colorBtnBG);
		taskLoadBtn.setForeground(colorBtnTxt);
		taskLoadBtn.setFont(fontTxt);
		taskLoadBtn.addActionListener(taskListSelcContr);
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTH;
		gbc_detailBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailBtn.insets = new Insets(20, 0, 0, 5);
		gbc_detailBtn.gridx = 1;
		gbc_detailBtn.gridy = 3;
		this.add(taskLoadBtn, gbc_detailBtn);
		
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
	}

}
