package de.gokv.client.taskviewer.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import de.gokv.client.taskviewer.controller.LoadTaskDetailsController;
import de.gokv.client.taskviewer.controller.LoadTaskDetailsKeyHandler;
import de.gokv.client.taskviewer.controller.MyFrameController;
import de.gokv.client.taskviewer.controller.TaskListController;

public class TaskListTemplate extends PanelBlockTemplate{
	private static final long serialVersionUID = 1L;

	public JButton taskLoadBtn;
	public JButton reloadBtn;
	public JList<String> taskList;
	public JTextField anzTasks;
	
	private Image iconReload;
	private String pathIconReload;
	private ImageIcon iconReloadScaled;
	
	protected TaskListTemplate(MyFrameController controller) {
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
		TaskListController taskListCont = new TaskListController();
		LoadTaskDetailsController loadTaskDetailsCont = new LoadTaskDetailsController();
		LoadTaskDetailsKeyHandler loadTaskDetailKey = new LoadTaskDetailsKeyHandler();
		
		anzTasks = new JTextField(TaskListController.getValidEntries() + TaskListController.getInvalidEntries());
		anzTasks.setForeground(colorEntryTxt);
		anzTasks.setBorder(emptyBorder);
		anzTasks.setOpaque(false);
		anzTasks.addActionListener(taskListCont);
		this.add(anzTasks);
		GridBagConstraints gbc_anzTask = new GridBagConstraints();
		gbc_anzTask.anchor = GridBagConstraints.NORTH;
		gbc_anzTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzTask.insets = new Insets(0, 10, 0, 0);
		gbc_anzTask.gridwidth = 2;
		gbc_anzTask.gridx = 2;
		gbc_anzTask.gridy = 4;
		this.add(anzTasks, gbc_anzTask);

		// << Task-Liste >>
		taskList = new JList<>(controller.getFilteredTasks());
		taskList.setVisibleRowCount(8);
		taskList.setFont(fontTxt);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(loadTaskDetailsCont);
		taskList.addKeyListener(loadTaskDetailKey);
		JScrollPane scrollTask = new JScrollPane(taskList);
		GridBagConstraints gbc_scrollTask = new GridBagConstraints();
		gbc_scrollTask.anchor = GridBagConstraints.NORTH;
		gbc_scrollTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollTask.gridwidth = 2;
		gbc_scrollTask.gridx = 1;
		gbc_scrollTask.gridy = 2;
		this.add(scrollTask, gbc_scrollTask);

		// << Tasks reload -Button >>
		reloadBtn = new JButton(" Liste neu laden  ");
		pathIconReload = "/reload.png";
		iconReload = new ImageIcon(getClass().getResource(pathIconReload)).getImage();
		iconReloadScaled = new ImageIcon(iconReload.getScaledInstance(18, 18, 0));
		reloadBtn.setIcon(iconReloadScaled);
		reloadBtn.setBorder(btnBorder);
		reloadBtn.setBackground(colorBtnBG);
		reloadBtn.setForeground(colorBtnTxt);
		reloadBtn.setFont(fontTxt);
		reloadBtn.addActionListener(taskListCont);
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
		taskLoadBtn.addActionListener(loadTaskDetailsCont);
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTH;
		gbc_detailBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailBtn.insets = new Insets(20, 0, 0, 5);
		gbc_detailBtn.gridx = 1;
		gbc_detailBtn.gridy = 3;
		this.add(taskLoadBtn, gbc_detailBtn);
	}

}
