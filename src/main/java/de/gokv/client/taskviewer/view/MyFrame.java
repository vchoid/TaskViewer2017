package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import de.gokv.client.taskviewer.controller.FilterTaskListController;
import de.gokv.client.taskviewer.controller.FilterTaskListKeyHandler;
import de.gokv.client.taskviewer.controller.LoadTaskDetailsController;
import de.gokv.client.taskviewer.controller.MyFrameController;
import de.gokv.client.taskviewer.controller.TaskListController;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * 
 * @author Christoph Kiank
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Content Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel contPanel;
	// Filter Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TitledBorder filtBorder;
	private JPanel filterPanel;
	public Field_Placeholder pKvnr;
	public Field_Placeholder pName;
	public Field_Placeholder pVname;
	public JLabel gDateLabel;
	public UtilDateModel model_geb = new UtilDateModel();
	public JDatePanelImpl gebDatePan = new JDatePanelImpl(model_geb);
	public JDatePickerImpl gDatePick = new JDatePickerImpl(gebDatePan, new DateLabelFormatter());
	public Field_Placeholder pTaskID;
	public JLabel oDateLabel;
	public UtilDateModel model_ord = new UtilDateModel();
	public JDatePanelImpl orderDatePan = new JDatePanelImpl(model_ord);
	public JDatePickerImpl oDatePick = new JDatePickerImpl(orderDatePan, new DateLabelFormatter());
	public JButton filterBtn;
	public static JButton clearFieldBtn;
	public static MyFrameController controller;
	public static JTextField anzFiltTask;
	public String valFiltMsg = "";
	// Task Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TitledBorder taskBorder;
	public static JPanel taskPanel;
	public static JButton taskLoadBtn;
	public static JButton reloadBtn;
	private String pathIconReload;
	private Image icon;
	private ImageIcon iconReload;
	public static JTextField anzTasks;
	public int countValidTasks;
	public int countInvalidTasks;
	public String invalEntMsg = "";
	public String valEntMsg = "";
	// Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TitledBorder infoBorder;
	private JPanel infoPanel;
	private JLabel taskID;
	private JLabel state;
	private JLabel taskType;
	private JLabel orderDate;
	private JLabel evCompl;
	private JLabel evInProgs;
	private JLabel evReceived;
	private JLabel evResult;
	public static JLabel taskID_field;
	public static  JLabel state_field;
	public static  JLabel taskType_field;
	public static  JLabel orderDate_field;
	public static  JLabel evCompl_field;
	public static  JLabel evInProgs_field;
	public static  JLabel evReceived_field;
	public static  JLabel evResult_field;
	public static  JLabel errMsg_field;
	
	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String pathIconPrg;
	private Font title = new Font("Arial", Font.BOLD, 16);
	private Font txt = new Font("Arial", Font.PLAIN, 15);
	private Font errMsgTxt = new Font("Arial", Font.PLAIN, 9);
	private Font label = new Font("Arial", Font.BOLD, 14);
	
	private Color colorPanBlockBG;
	private Color colorPanContBG;
	private Color colorTitelTxt;
	private Color colorBtnBG;
	private Color colorBtnTxt;
	private Color colorLabelDateTxt;
	private Color colorInfoTitleTxt;
	private Color colorInfoTxt;
	private Color colorErrMsg;
	
	private Border btnBorder = BorderFactory.createEmptyBorder(8, 5, 8, 5);
	private Border emptyBorder = BorderFactory.createEmptyBorder();

	// MODEL !! ACHTUNG
	public JList<String> taskList;

	/**
	 * Konstruktor
	 */
	public MyFrame() {
		pathIconPrg = "/taskViewer.png";
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(pathIconPrg)));
		setTitle("bitGo_KV-TaskViewer");
		setAlwaysOnTop(true);


		// Properties
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Fenstergröße
		// Quer 3x1
//		setSize(new Dimension(1360, 355));
		// Quer 2x2
		setSize(new Dimension(915, 662));
		// Hoch
//		setSize(new Dimension(470, 970));
		// Farben 
		colorPanContBG = new HexaToRGB("color.panel.content.background").parseHexToRGB();
		colorPanBlockBG = new HexaToRGB("color.panel.block.background").parseHexToRGB();
		colorTitelTxt = new HexaToRGB("color.panel.titeltext.foreground").parseHexToRGB();
		colorBtnBG = new HexaToRGB("color.button.background").parseHexToRGB();
		colorBtnTxt = new HexaToRGB("color.button.text").parseHexToRGB();
		colorLabelDateTxt = new HexaToRGB("color.label.date.text").parseHexToRGB();
		colorInfoTitleTxt =new HexaToRGB("color.info.titel.text").parseHexToRGB();
		colorInfoTxt =new HexaToRGB("color.info.text").parseHexToRGB();
		colorErrMsg =new HexaToRGB("color.ErrorMsg.text").parseHexToRGB();
		// Content-Panel
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		controller = new MyFrameController(this);
		contPanel.setBorder(taskBorder);
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);
		getContentPane().setBackground(colorPanContBG);
//		 Input-Panel
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		FilterTaskListController filtTaskCont = new FilterTaskListController(this, controller.getModel());
		FilterTaskListKeyHandler filtTaskKey = new FilterTaskListKeyHandler(this, controller.getModel());
		
		filtBorder = BorderFactory.createTitledBorder("Filter Maske");
		filtBorder.setTitleJustification(TitledBorder.TOP);
		filtBorder.setTitleColor(colorTitelTxt);
		filtBorder.setTitleFont(title);
		filtBorder.setBorder(emptyBorder);
		filterPanel = new JPanel();
		filterPanel.setBorder(filtBorder);
		filterPanel.setBackground(colorPanBlockBG);
		GridBagLayout gbl_filPanel = new GridBagLayout();
		gbl_filPanel.columnWidths = new int[] { 10, 200, 200, 10 };
		gbl_filPanel.rowHeights = new int[] { 43, 30, 30, 30, 30, 30, 30, 23 };
		filterPanel.setLayout(gbl_filPanel);
		contPanel.add(filterPanel);

		// << KVNR >>
		pKvnr = new Field_Placeholder();
		pKvnr.setPlaceholder("Krankenversichertennummer (laut eGk)",
				gbl_filPanel.columnWidths[1] + gbl_filPanel.columnWidths[2] + gbl_filPanel.columnWidths[3]);
		pKvnr.setBorder(emptyBorder);
		pKvnr.addActionListener(filtTaskCont);
		GridBagConstraints gbc_kvnr = new GridBagConstraints();
		gbc_kvnr.anchor = GridBagConstraints.NORTH;
		gbc_kvnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvnr.gridwidth = 2;
		gbc_kvnr.gridx = 1;
		gbc_kvnr.gridy = 1;
		filterPanel.add(pKvnr, gbc_kvnr);

		// << Name >>
		pName = new Field_Placeholder();
		pName.setPlaceholder("Name", gbl_filPanel.columnWidths[2] - 5);
		pName.setBorder(emptyBorder);
		pName.addActionListener(filtTaskCont);
		pName.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.NORTH;
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.insets = new Insets(0, 0, 0, 10);
		gbc_name.gridx = 1;
		gbc_name.gridy = 2;
		filterPanel.add(pName, gbc_name);

		// << Vorname >>
		pVname = new Field_Placeholder();
		pVname.setPlaceholder("Vorname", gbl_filPanel.columnWidths[2]);
		pVname.setBorder(emptyBorder);
		pVname.addActionListener(filtTaskCont);
		pVname.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_vName = new GridBagConstraints();
		gbc_vName.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_vName.fill = GridBagConstraints.HORIZONTAL;
		gbc_vName.insets = new Insets(0, 10, 10, 10);
		gbc_vName.gridx = 2;
		gbc_vName.gridy = 2;
		filterPanel.add(pVname, gbc_vName);

		// << Geburtsdatum >>
		gDateLabel = new JLabel("Geburtstag");
		gDateLabel.setForeground(colorLabelDateTxt);
		gDateLabel.setBorder(emptyBorder);
		gDateLabel.setFont(txt);
		GridBagConstraints gbc_gDateLabel = new GridBagConstraints();
		gbc_gDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_gDateLabel.gridx = 1;
		gbc_gDateLabel.gridy = 3;
		filterPanel.add(gDateLabel, gbc_gDateLabel);
		GridBagConstraints gbc_gebDate = new GridBagConstraints();
		gbc_gebDate.insets = new Insets(0, 10, 5, 0);
		gbc_gebDate.gridx = 2;
		gbc_gebDate.gridy = 3;
		gDatePick.setTextEditable(true);
		gebDatePan.setBorder(emptyBorder);
		filterPanel.add(gDatePick, gbc_gebDate);

		// << TaskID >>
		pTaskID = new Field_Placeholder();
		pTaskID.setPlaceholder("TaskID",
				gbl_filPanel.columnWidths[1] + gbl_filPanel.columnWidths[2] + gbl_filPanel.columnWidths[3]);
		pTaskID.setBorder(emptyBorder);
		pTaskID.addActionListener(filtTaskCont);
		pTaskID.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_taskID = new GridBagConstraints();
		gbc_taskID.anchor = GridBagConstraints.NORTH;
		gbc_taskID.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID.gridwidth = 2;
		gbc_taskID.gridx = 1;
		gbc_taskID.gridy = 4;
		filterPanel.add(pTaskID, gbc_taskID);

		// << OrderedDate >>
		oDateLabel = new JLabel("Order Date");
		oDateLabel.setForeground(colorLabelDateTxt);
		oDateLabel.setBorder(emptyBorder);
		oDateLabel.setFont(txt);
		GridBagConstraints gbc_oDateLabel = new GridBagConstraints();
		gbc_oDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_oDateLabel.gridx = 1;
		gbc_oDateLabel.gridy = 5;
		filterPanel.add(oDateLabel, gbc_oDateLabel);
		GridBagConstraints gbc_orderDate = new GridBagConstraints();
		gbc_orderDate.insets = new Insets(0, 10, 10, 0);
		gbc_orderDate.gridx = 2;
		gbc_orderDate.gridy = 5;
		oDatePick.setTextEditable(true);
		orderDatePan.setBorder(emptyBorder);
		filterPanel.add(oDatePick, gbc_orderDate);

		// << Button "Felder leeren" >>
		clearFieldBtn = new JButton("Felder leeren");
		clearFieldBtn.setBorder(btnBorder);
		clearFieldBtn.setBackground(colorBtnBG);
		clearFieldBtn.setForeground(colorBtnTxt);
		clearFieldBtn.setFont(txt);
		clearFieldBtn.addActionListener(filtTaskCont);
		GridBagConstraints gbc_clearFieldBtn = new GridBagConstraints();
		gbc_clearFieldBtn.anchor = GridBagConstraints.NORTH;
		gbc_clearFieldBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearFieldBtn.insets = new Insets(20, 0, 0, 5);
		gbc_clearFieldBtn.gridx = 1;
		gbc_clearFieldBtn.gridy = 6;
		filterPanel.add(clearFieldBtn, gbc_clearFieldBtn);
		
		// << Button "Filter anwenden" >>
		filterBtn = new JButton("Tasks filtern");
		filterBtn.setBorder(btnBorder);
		filterBtn.setBackground(colorBtnBG);
		filterBtn.setForeground(colorBtnTxt);
		filterBtn.setFont(txt);
		filterBtn.addActionListener(filtTaskCont);
		GridBagConstraints gbc_filterBtn = new GridBagConstraints();
		gbc_filterBtn.anchor = GridBagConstraints.NORTH;
		gbc_filterBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterBtn.insets = new Insets(20, 10, 0, 0);
		gbc_filterBtn.gridx = 2;
		gbc_filterBtn.gridy = 6;
		filterPanel.add(filterBtn, gbc_filterBtn);
		
		// Anzahl der gefilterten Tasks
		anzFiltTask = new JTextField(countValidTasks + " Einträge " );
		anzFiltTask.setBorder(emptyBorder);
		anzFiltTask.addActionListener(filtTaskCont);
		anzFiltTask.addKeyListener(filtTaskKey);
		anzFiltTask.setOpaque(false);
		filterPanel.add(anzFiltTask);
		GridBagConstraints gbc_anzFiltTask = new GridBagConstraints();
		gbc_anzFiltTask.anchor = GridBagConstraints.NORTH;
		gbc_anzFiltTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzFiltTask.insets = new Insets(3, 10, 0, 0);
		gbc_anzFiltTask.gridwidth = 2;
		gbc_anzFiltTask.gridx = 2;
		gbc_anzFiltTask.gridy = 7;
		filterPanel.add(anzFiltTask, gbc_anzFiltTask);
		

		// TaskPanel
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		TaskListController taskListCont = new TaskListController(this, controller.getModel());
		LoadTaskDetailsController loadTaskDetailsCont= new LoadTaskDetailsController(this, controller.getModel());
		
		taskBorder = BorderFactory.createTitledBorder("Tasks");
		taskBorder.setTitleJustification(TitledBorder.TOP);
		taskBorder.setTitleColor(colorTitelTxt);
		taskBorder.setTitleFont(title);
		taskBorder.setBorder(emptyBorder);
		taskPanel = new JPanel();
		taskPanel.setBorder(taskBorder);
		taskPanel.setBackground(colorPanBlockBG);
		GridBagLayout gbl_taskPan = new GridBagLayout();
		gbl_taskPan.columnWidths = new int[] { 10, 206, 206, 10 };
		gbl_taskPan.rowHeights = new int[] { 43 , 0, 157, 30, 23};
		taskPanel.setLayout(gbl_taskPan);
		contPanel.add(taskPanel);
		
		// Anzahl der Tasks
		countInvalidTasks = MyModel.countInvalidTasks;
		if(countInvalidTasks > 0){
			invalEntMsg = "(davon fehlerhaft: "+ countInvalidTasks + ")";
		} else{
			invalEntMsg = "";
		}
		countValidTasks = controller.getFilteredTasks().length;
		if(countValidTasks > 0){
			valEntMsg = countValidTasks + " Einträge ";
		} else {
			valEntMsg = "";
		}
		anzTasks = new JTextField(valEntMsg + invalEntMsg );
		anzTasks.setBorder(emptyBorder);
		anzTasks.setOpaque(false);
		anzTasks.addActionListener(taskListCont);
		taskPanel.add(anzTasks);
		GridBagConstraints gbc_anzTask = new GridBagConstraints();
		gbc_anzTask.anchor = GridBagConstraints.CENTER;
		gbc_anzTask.fill = GridBagConstraints.BOTH;
		gbc_anzTask.insets = new Insets(2, 10, 0, 0);
		gbc_anzTask.gridwidth = 2;
		gbc_anzTask.gridx = 2;
		gbc_anzTask.gridy = 4;
		taskPanel.add(anzTasks, gbc_anzTask);
		
		// << Task-Liste >>
		taskList = new JList<>(controller.getFilteredTasks());
		taskList.setVisibleRowCount(7);
		taskList.setFont(txt);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(loadTaskDetailsCont);
		JScrollPane scrollTask = new JScrollPane(taskList);
		GridBagConstraints gbc_scrollTask = new GridBagConstraints();
		gbc_scrollTask.anchor = GridBagConstraints.NORTH;
		gbc_scrollTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollTask.gridwidth = 2;
		gbc_scrollTask.gridx = 1;
		gbc_scrollTask.gridy = 2;
		taskPanel.add(scrollTask, gbc_scrollTask);
				
		// << Tasks reload -Button >>
		reloadBtn = new JButton(" Taskliste neu laden  ");
		pathIconReload = "/reload.png";
		icon = new ImageIcon(getClass().getResource(pathIconReload)).getImage();
		iconReload = new ImageIcon(icon.getScaledInstance(18, 18, 0));
		reloadBtn.setIcon(iconReload);
		reloadBtn.setBorder(btnBorder);
		reloadBtn.setBackground(colorBtnBG);
		reloadBtn.setForeground(colorBtnTxt);
		reloadBtn.setFont(txt);
		reloadBtn.addActionListener(taskListCont);
		GridBagConstraints gbc_reloadTaskBtn = new GridBagConstraints();
		gbc_reloadTaskBtn.anchor = GridBagConstraints.NORTH;
		gbc_reloadTaskBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_reloadTaskBtn.insets = new Insets(20, 10, 0, 0);
		gbc_reloadTaskBtn.gridx = 2;
		gbc_reloadTaskBtn.gridy = 3;
		taskPanel.add(reloadBtn, gbc_reloadTaskBtn);
		
		// << Tasks laden -Button >>
		taskLoadBtn = new JButton("Task laden");
		taskLoadBtn.setBorder(btnBorder);
		taskLoadBtn.setBackground(colorBtnBG);
		taskLoadBtn.setForeground(colorBtnTxt);
		taskLoadBtn.setFont(txt);
		taskLoadBtn.addActionListener(loadTaskDetailsCont);
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTH;
		gbc_detailBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailBtn.insets = new Insets(20, 0, 0, 5);
		gbc_detailBtn.gridx = 1;
		gbc_detailBtn.gridy = 3;
		taskPanel.add(taskLoadBtn, gbc_detailBtn);
		

		// Info-Panel
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		infoBorder = BorderFactory.createTitledBorder("Informationen");
		infoBorder.setTitleJustification(TitledBorder.TOP);
		infoBorder.setTitleColor(colorTitelTxt);
		infoBorder.setTitleFont(title);
		infoBorder.setBorder(emptyBorder);
		infoPanel = new JPanel();
		infoPanel.setBorder(infoBorder);
		infoPanel.setBackground(colorPanBlockBG);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 10, 150, 262, 10 };
		gbl_infoPanel.rowHeights = new int[] { 21, 20, 23, 24, 24, 24, 24, 24, 24, 24, 24, 21 };
		infoPanel.setLayout(gbl_infoPanel);
		contPanel.add(infoPanel);

		// 0. Task ID
		taskID = new JLabel("TaskID");
		taskID.setFont(label);
		taskID.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_taskdIDLabel = new GridBagConstraints();
		gbc_taskdIDLabel.anchor = GridBagConstraints.NORTH;
		gbc_taskdIDLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskdIDLabel.insets = new Insets(0, 0, 0, 0);
		gbc_taskdIDLabel.gridwidth = 1;
		gbc_taskdIDLabel.gridx = 1;
		gbc_taskdIDLabel.gridy = 1;
		infoPanel.add(taskID, gbc_taskdIDLabel);

		taskID_field = new JLabel();
		taskID_field.setFont(txt);
		taskID_field.setForeground(colorInfoTxt);
		taskID_field.setBorder(emptyBorder);
		GridBagConstraints gbc_taskID_field = new GridBagConstraints();
		gbc_taskID_field.anchor = GridBagConstraints.NORTH;
		gbc_taskID_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID_field.insets = new Insets(0, 0, 0, 0);
		gbc_taskID_field.gridwidth = 2;
		gbc_taskID_field.gridx = 1;
		gbc_taskID_field.gridy = 2;
		infoPanel.add(taskID_field, gbc_taskID_field);
		
		// 1. State - Status
		state = new JLabel("Status:");
		state.setFont(label);
		state.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.NORTH;
		gbc_stateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateLabel.insets = new Insets(0, 0, 0, 0);
		gbc_stateLabel.gridwidth = 1;
		gbc_stateLabel.gridx = 1;
		gbc_stateLabel.gridy = 3;
		infoPanel.add(state, gbc_stateLabel);
		
		state_field = new JLabel();
		state_field.setFont(txt);
		state_field.setForeground(colorInfoTxt);
		state_field.setBorder(emptyBorder);
		GridBagConstraints gbc_state_field = new GridBagConstraints();
		gbc_state_field.anchor = GridBagConstraints.NORTH;
		gbc_state_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_state_field.insets = new Insets(0, 0, 0, 0);
		gbc_state_field.gridwidth = 1;
		gbc_state_field.gridx = 2;
		gbc_state_field.gridy = 3;
		infoPanel.add(state_field, gbc_state_field);
		
		// 2. Type - Task Type
		taskType = new JLabel("Task Type:");
		taskType.setFont(label);
		taskType.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_TaskTypeLabel = new GridBagConstraints();
		gbc_TaskTypeLabel.anchor = GridBagConstraints.NORTH;
		gbc_TaskTypeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_TaskTypeLabel.insets = new Insets(0, 0, 0, 0);
		gbc_TaskTypeLabel.gridwidth = 1;
		gbc_TaskTypeLabel.gridx = 1;
		gbc_TaskTypeLabel.gridy = 4;
		infoPanel.add(taskType, gbc_TaskTypeLabel);
		
		taskType_field = new JLabel();
		taskType_field.setFont(txt);
		taskType_field.setForeground(colorInfoTxt);
		taskType_field.setBorder(emptyBorder);
		GridBagConstraints gbc_taskType_field = new GridBagConstraints();
		gbc_taskType_field.anchor = GridBagConstraints.NORTH;
		gbc_taskType_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskType_field.insets = new Insets(0, 0, 0, 0);
		gbc_taskType_field.gridwidth = 1;
		gbc_taskType_field.gridx = 2;
		gbc_taskType_field.gridy = 4;
		infoPanel.add(taskType_field, gbc_taskType_field);
				
		// 3. OrderedDate - Datum Order
		orderDate = new JLabel("Order Datum:");
		orderDate.setFont(label);
		orderDate.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_orderDateLabel = new GridBagConstraints();
		gbc_orderDateLabel.anchor = GridBagConstraints.NORTH;
		gbc_orderDateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderDateLabel.insets = new Insets(0, 0, 0, 0);
		gbc_orderDateLabel.gridwidth = 1;
		gbc_orderDateLabel.gridx = 1;
		gbc_orderDateLabel.gridy = 5;
		infoPanel.add(orderDate, gbc_orderDateLabel);
		
		orderDate_field = new JLabel();
		orderDate_field.setFont(txt);
		orderDate_field.setForeground(colorInfoTxt);
		orderDate_field.setBorder(emptyBorder);
		GridBagConstraints gbc_orderDate_field = new GridBagConstraints();
		gbc_orderDate_field.anchor = GridBagConstraints.NORTH;
		gbc_orderDate_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderDate_field.insets = new Insets(0, 0, 0, 0);
		gbc_orderDate_field.gridwidth = 1;
		gbc_orderDate_field.gridx = 2;
		gbc_orderDate_field.gridy = 5;
		infoPanel.add(orderDate_field, gbc_orderDate_field);
		
		// 4. EventInProgress - Zeitpunkt In Arbeit
		evInProgs = new JLabel("In Arbeit:");
		evInProgs.setFont(label);
		evInProgs.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_evInProgsLabel = new GridBagConstraints();
		gbc_evInProgsLabel.anchor = GridBagConstraints.NORTH;
		gbc_evInProgsLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_evInProgsLabel.insets = new Insets(0, 0, 0, 0);
		gbc_evInProgsLabel.gridwidth = 1;
		gbc_evInProgsLabel.gridx = 1;
		gbc_evInProgsLabel.gridy = 6;
		infoPanel.add(evInProgs, gbc_evInProgsLabel);
		
		evInProgs_field = new JLabel();
		evInProgs_field.setFont(txt);
		evInProgs_field.setForeground(colorInfoTxt);
		evInProgs_field.setBorder(emptyBorder);
		GridBagConstraints gbc_evInProgs_field = new GridBagConstraints();
		gbc_evInProgs_field.anchor = GridBagConstraints.NORTH;
		gbc_evInProgs_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_evInProgs_field.insets = new Insets(0, 0, 0, 0);
		gbc_evInProgs_field.gridwidth = 1;
		gbc_evInProgs_field.gridx = 2;
		gbc_evInProgs_field.gridy = 6;
		infoPanel.add(evInProgs_field, gbc_evInProgs_field);
		
		// 5. EventCompleted - Zeitpunkt Bereit zur Abholung
		evCompl = new JLabel("Bereit zur Abholung:");
		evCompl.setFont(label);
		evCompl.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_evComplLabel = new GridBagConstraints();
		gbc_evComplLabel.anchor = GridBagConstraints.NORTH;
		gbc_evComplLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_evComplLabel.insets = new Insets(0, 0, 0, 0);
		gbc_evComplLabel.gridwidth = 1;
		gbc_evComplLabel.gridx = 1;
		gbc_evComplLabel.gridy = 7;
		infoPanel.add(evCompl, gbc_evComplLabel);
		
		evCompl_field = new JLabel();
		evCompl_field.setFont(txt);
		evCompl_field.setForeground(colorInfoTxt);
		evCompl_field.setBorder(emptyBorder);
		GridBagConstraints gbc_evCompl_field = new GridBagConstraints();
		gbc_evCompl_field.anchor = GridBagConstraints.NORTH;
		gbc_evCompl_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_evCompl_field.insets = new Insets(0, 0, 0, 0);
		gbc_evCompl_field.gridwidth = 1;
		gbc_evCompl_field.gridx = 2;
		gbc_evCompl_field.gridy = 7;
		infoPanel.add(evCompl_field, gbc_evCompl_field);
		
		// 6. EventReceived - Zeitpunkt Abholung
		evReceived = new JLabel("Abholung:");
		evReceived.setFont(label);
		evReceived.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_evReceivedLabel = new GridBagConstraints();
		gbc_evReceivedLabel.anchor = GridBagConstraints.NORTH;
		gbc_evReceivedLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_evReceivedLabel.insets = new Insets(0, 0, 0, 0);
		gbc_evReceivedLabel.gridwidth = 1;
		gbc_evReceivedLabel.gridx = 1;
		gbc_evReceivedLabel.gridy = 8;
		infoPanel.add(evReceived, gbc_evReceivedLabel);
		
		evReceived_field = new JLabel();
		evReceived_field.setFont(txt);
		evReceived_field.setForeground(colorInfoTxt);
		evReceived_field.setBorder(emptyBorder);
		GridBagConstraints gbc_evReceived_field = new GridBagConstraints();
		gbc_evReceived_field.anchor = GridBagConstraints.NORTH;
		gbc_evReceived_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_evReceived_field.insets = new Insets(0, 0, 0, 0);
		gbc_evReceived_field.gridwidth = 1;
		gbc_evReceived_field.gridx = 2;
		gbc_evReceived_field.gridy = 8;
		infoPanel.add(evReceived_field, gbc_evReceived_field);
		
		// 7. EventResult - Zeitpunkt Prüfung	
		evResult = new JLabel("Prüfung:");
		evResult.setFont(label);
		evResult.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_evResultLabel = new GridBagConstraints();
		gbc_evResultLabel.anchor = GridBagConstraints.NORTH;
		gbc_evResultLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_evResultLabel.insets = new Insets(0, 0, 0, 0);
		gbc_evResultLabel.gridwidth = 1;
		gbc_evResultLabel.gridx = 1;
		gbc_evResultLabel.gridy = 9;
		infoPanel.add(evResult, gbc_evResultLabel);
		
		evResult_field = new JLabel();
		evResult_field.setFont(txt);
		evResult_field.setForeground(colorInfoTxt);
		evResult_field.setBorder(emptyBorder);
		GridBagConstraints gbc_evResult_field = new GridBagConstraints();
		gbc_evResult_field.anchor = GridBagConstraints.NORTH;
		gbc_evResult_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_evResult_field.insets = new Insets(0, 0, 0, 0);
		gbc_evResult_field.gridwidth = 1;
		gbc_evResult_field.gridx = 2;
		gbc_evResult_field.gridy = 9;
		infoPanel.add(evResult_field, gbc_evResult_field);
		
		errMsg_field = new JLabel(HexaToRGB.errMsg);
		errMsg_field.setFont(errMsgTxt);
		errMsg_field.setForeground(colorErrMsg);
		errMsg_field.setBorder(emptyBorder);
		GridBagConstraints gbc_errMsg_field = new GridBagConstraints();
		gbc_errMsg_field.anchor = GridBagConstraints.NORTH;
		gbc_errMsg_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_errMsg_field.insets = new Insets(0, 0, 0, 0);
		gbc_errMsg_field.gridwidth = 4;
		gbc_errMsg_field.gridx = 0;
		gbc_errMsg_field.gridy = 10;
		infoPanel.add(errMsg_field, gbc_errMsg_field);
		
		

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
	}

}
