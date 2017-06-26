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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import de.gokv.client.taskviewer.controller.FilterTaskListController;
import de.gokv.client.taskviewer.controller.FilterTaskListKeyHandler;
import de.gokv.client.taskviewer.controller.LoadTaskDetailsController;
import de.gokv.client.taskviewer.controller.LoadTaskDetailsKeyHandler;
import de.gokv.client.taskviewer.controller.MyFrameController;
import de.gokv.client.taskviewer.controller.TaskListController;
import de.gokv.client.taskviewer.model.MyModel;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * @author Christoph Kiank
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Content Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel contPanel;
	// Filter Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
	// CSV Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel csvInfoPanel;
	private JLabel taskID;
	public static JLabel taskID_field;
	public static InfoField kvnr; 
	public static InfoField name; 
	public static InfoField vName; 
	public static InfoField gebDate; 
	// Task Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private JPanel taskInfoPanel;
	public static InfoField state;
	public static InfoField taskType;
	public static InfoField orderDate;
	public static InfoField evCompl;
	public static InfoField evInProgs;
	public static InfoField evReceived;
	public static InfoField evResult;


	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private String pathIconPrg;
	private Font txt = new Font("Arial", Font.PLAIN, 15);
	private Font label = new Font("Arial", Font.BOLD, 14);

	private Color colorPanContBG;
	private Color colorBtnBG;
	private Color colorBtnTxt;
	private Color colorEntryTxt;
	private Color colorLabelDateTxt;
	private Color colorInfoTitleTxt;
	private Color colorInfoTxt;

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
		// setSize(new Dimension(1360, 355));
		// Quer 2x2
		setSize(new Dimension(902, 638));
		// Hoch
		// setSize(new Dimension(470, 970));
		// Farben
		colorPanContBG = new HexaToRGB("color.panel.content.background").parseHexToRGB();
		colorBtnBG = new HexaToRGB("color.button.background").parseHexToRGB();
		colorBtnTxt = new HexaToRGB("color.button.text").parseHexToRGB();
		colorEntryTxt = new HexaToRGB("color.entries.text").parseHexToRGB();
		colorLabelDateTxt = new HexaToRGB("color.label.date.text").parseHexToRGB();
		colorInfoTitleTxt = new HexaToRGB("color.info.titel.text").parseHexToRGB();
		colorInfoTxt = new HexaToRGB("color.info.text").parseHexToRGB();
		// Content-Panel
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		controller = new MyFrameController(this);
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);
		getContentPane().setBackground(colorPanContBG);
		// Filter-Panel
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		FilterTaskListController filtTaskCont = new FilterTaskListController(this, controller.getModel());
		FilterTaskListKeyHandler filtTaskKey = new FilterTaskListKeyHandler(this, controller.getModel());
		
		GridBagLayout gbl_filPanel = new GridBagLayout();
		gbl_filPanel.columnWidths = new int[] { 10, 200, 200, 10 };
		gbl_filPanel.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30 };
		PanelBlockTemplate filterMask = new PanelBlockTemplate();
		filterMask.createPanelWithTitle("Filter Maske");
		filterMask.setGridBagLayout(gbl_filPanel);
		filterPanel = filterMask.getPanel();
		contPanel.add(filterPanel);
		
		// << KVNR >>
		pKvnr = new Field_Placeholder();
		pKvnr.setPlaceholder("Krankenversichertennummer (laut eGk)",410);
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
		pName.setPlaceholder("Name", 195);
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
		pVname.setPlaceholder("Vorname", 200);
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
		pTaskID.setPlaceholder("TaskID", 410);
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
		gbc_filterBtn.insets = new Insets(20, 0, 0, 5);
		gbc_filterBtn.gridx = 1;
		gbc_filterBtn.gridy = 6;
		filterPanel.add(filterBtn, gbc_filterBtn);
		
		// << Button "Felder leeren" >>
		clearFieldBtn = new JButton("Alles zurücksetzen");
		clearFieldBtn.setBorder(btnBorder);
		clearFieldBtn.setBackground(colorBtnBG);
		clearFieldBtn.setForeground(colorBtnTxt);
		clearFieldBtn.setFont(txt);
		clearFieldBtn.addActionListener(filtTaskCont);
		GridBagConstraints gbc_clearFieldBtn = new GridBagConstraints();
		gbc_clearFieldBtn.anchor = GridBagConstraints.NORTH;
		gbc_clearFieldBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearFieldBtn.insets = new Insets(20, 10, 0, 0);
		gbc_clearFieldBtn.gridx = 2;
		gbc_clearFieldBtn.gridy = 6;
		filterPanel.add(clearFieldBtn, gbc_clearFieldBtn);

		// Anzahl der gefilterten Tasks
		anzFiltTask = new JTextField(countValidTasks + " Einträge ");
		anzFiltTask.setForeground(colorEntryTxt);
		anzFiltTask.setBorder(emptyBorder);
		anzFiltTask.addActionListener(filtTaskCont);
		anzFiltTask.addKeyListener(filtTaskKey);
		anzFiltTask.setOpaque(false);
		filterPanel.add(anzFiltTask);
		GridBagConstraints gbc_anzFiltTask = new GridBagConstraints();
		gbc_anzFiltTask.anchor = GridBagConstraints.NORTH;
		gbc_anzFiltTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzFiltTask.insets = new Insets(0, 10, 0, 0);
		gbc_anzFiltTask.gridwidth = 2;
		gbc_anzFiltTask.gridx = 2;
		gbc_anzFiltTask.gridy = 7;
		filterPanel.add(anzFiltTask, gbc_anzFiltTask);

		// Task-Panel
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		TaskListController taskListCont = new TaskListController(this, controller.getModel());
		LoadTaskDetailsController loadTaskDetailsCont = new LoadTaskDetailsController(this, controller.getModel());
		LoadTaskDetailsKeyHandler loadTaskDetailKey = new LoadTaskDetailsKeyHandler(this,  controller.getModel());

		GridBagLayout gbl_taskPan = new GridBagLayout();
		gbl_taskPan.columnWidths = new int[] { 10, 206, 206, 10 };
		gbl_taskPan.rowHeights = new int[] { 30, 0, 157, 30, 30 };
		PanelBlockTemplate tasks = new PanelBlockTemplate();
		tasks.createPanelWithTitle("Tasks");
		tasks.setGridBagLayout(gbl_taskPan);
		taskPanel = tasks.getPanel();
		contPanel.add(taskPanel);

		// Anzahl der Tasks
		countInvalidTasks = MyModel.countInvalidTasks;
		if (countInvalidTasks > 0) {
			invalEntMsg = "(fehlerhaft: " + countInvalidTasks + ")";
		} else {
			invalEntMsg = "";
		}
		countValidTasks = controller.getFilteredTasks().length;
		if (countValidTasks > 0) {
			valEntMsg = countValidTasks + " Einträge ";
		} else {
			valEntMsg = "";
		}
		anzTasks = new JTextField(valEntMsg + invalEntMsg);
		anzTasks.setForeground(colorEntryTxt);
		anzTasks.setBorder(emptyBorder);
		anzTasks.setOpaque(false);
		anzTasks.addActionListener(taskListCont);
		taskPanel.add(anzTasks);
		GridBagConstraints gbc_anzTask = new GridBagConstraints();
		gbc_anzTask.anchor = GridBagConstraints.NORTH;
		gbc_anzTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzTask.insets = new Insets(0, 10, 0, 0);
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
		taskList.addKeyListener(loadTaskDetailKey);
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

		
		// CSV Info-Panel 
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		GridBagLayout gbl_csvInfoPanel = new GridBagLayout();
		gbl_csvInfoPanel.columnWidths = new int[] { 10, 206, 206, 10 };
		gbl_csvInfoPanel.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30 };
		PanelBlockTemplate infoCSV = new PanelBlockTemplate();
		infoCSV.createPanelWithTitle("Informationen (CSV)");
		infoCSV.setGridBagLayout(gbl_csvInfoPanel);
		csvInfoPanel = infoCSV.getPanel();
		contPanel.add(csvInfoPanel);

		// 1. Task ID
		taskID = new JLabel("TaskID");
		taskID.setFont(label);
		taskID.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_taskdIDLabel = new GridBagConstraints();
		gbc_taskdIDLabel.anchor = GridBagConstraints.NORTH;
		gbc_taskdIDLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskdIDLabel.gridx = 1;
		gbc_taskdIDLabel.gridy = 1;
		csvInfoPanel.add(taskID, gbc_taskdIDLabel);

		taskID_field = new JLabel();
		taskID_field.setFont(txt);
		taskID_field.setForeground(colorInfoTxt);
		GridBagConstraints gbc_taskID_field = new GridBagConstraints();
		gbc_taskID_field.anchor = GridBagConstraints.NORTH;
		gbc_taskID_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID_field.gridwidth = 2;
		gbc_taskID_field.gridx = 1;
		gbc_taskID_field.gridy = 2;
		csvInfoPanel.add(taskID_field, gbc_taskID_field);
		
		// 2. KvNr
		kvnr = new InfoField();
		kvnr.createInfoField("KvNr:", 1, 3);
		csvInfoPanel.add(kvnr.getlTitle(), kvnr.getGbc_lTitle());
		csvInfoPanel.add(kvnr.getlField(), kvnr.getGbc_lField());
		
		// 3. Name
		name = new InfoField();
		name.createInfoField("Name:", 1, 4);
		csvInfoPanel.add(name.getlTitle(), name.getGbc_lTitle());
		csvInfoPanel.add(name.getlField(), name.getGbc_lField());
		// 4. Vorname
		vName = new InfoField();
		vName.createInfoField("Vorname:", 1, 5);
		csvInfoPanel.add(vName.getlTitle(), vName.getGbc_lTitle());
		csvInfoPanel.add(vName.getlField(), vName.getGbc_lField());
		
		// 5. Geburtsdatum
		gebDate = new InfoField();
		gebDate.createInfoField("Geburtsdatum:", 1, 6);
		csvInfoPanel.add(gebDate.getlTitle(), gebDate.getGbc_lTitle());
		csvInfoPanel.add(gebDate.getlField(), gebDate.getGbc_lField());
		
		
		// Task Info-Panel
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		GridBagLayout gbl_taskInfoPanel = new GridBagLayout();
		gbl_taskInfoPanel.columnWidths = new int[] { 10, 206, 206, 10 };
		gbl_taskInfoPanel.rowHeights = new int[] {30,30,30,30,30,30,30,30,30 };
		PanelBlockTemplate infoTask = new PanelBlockTemplate();
		infoTask.createPanelWithTitle("Informationen (Task)");
		infoTask.setGridBagLayout(gbl_taskInfoPanel);
		taskInfoPanel = infoTask.getPanel();
		contPanel.add(taskInfoPanel);
		
		// 1. State - Status
		state = new InfoField();
		state.createInfoField("Status:", 1, 1);
		taskInfoPanel.add(state.getlTitle(), state.getGbc_lTitle());
		taskInfoPanel.add(state.getlField(), state.getGbc_lField());
		
		// 2. Type - Task Type
		taskType = new InfoField();
		taskType.createInfoField("Task Type:", 1, 2);
		taskInfoPanel.add(taskType.getlTitle(), taskType.getGbc_lTitle());
		taskInfoPanel.add(taskType.getlField(), taskType.getGbc_lField());

		// 3. OrderedDate - Datum Order
		orderDate = new InfoField();
		orderDate.createInfoField("Order Datum:", 1, 3);
		taskInfoPanel.add(orderDate.getlTitle(), orderDate.getGbc_lTitle());
		taskInfoPanel.add(orderDate.getlField(), orderDate.getGbc_lField());

		// 4. EventInProgress - Zeitpunkt In Arbeit
		evInProgs = new InfoField();
		evInProgs.createInfoField("In Arbeit:", 1, 4);
		taskInfoPanel.add(evInProgs.getlTitle(), evInProgs.getGbc_lTitle());
		taskInfoPanel.add(evInProgs.getlField(), evInProgs.getGbc_lField());

		// 5. EventCompleted - Zeitpunkt Bereit zur Abholung
		evCompl = new InfoField();
		evCompl.createInfoField("Bereit zur Abholung:", 1, 5);
		taskInfoPanel.add(evCompl.getlTitle(), evCompl.getGbc_lTitle());
		taskInfoPanel.add(evCompl.getlField(), evCompl.getGbc_lField());

		// 6. EventReceived - Zeitpunkt Abholung
		evReceived = new InfoField();
		evReceived.createInfoField("Abholung:", 1, 6);
		taskInfoPanel.add(evReceived.getlTitle(), evReceived.getGbc_lTitle());
		taskInfoPanel.add(evReceived.getlField(), evReceived.getGbc_lField());
		
		// 7. EventResult - Zeitpunkt Prüfung
		evResult = new InfoField();
		evResult.createInfoField("Prüfung:", 1, 7);
		taskInfoPanel.add(evResult.getlTitle(), evResult.getGbc_lTitle());
		taskInfoPanel.add(evResult.getlField(), evResult.getGbc_lField());

		// Error Message
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		if (HexaToRGB.errMsg != null) {
			JOptionPane.showMessageDialog(null, HexaToRGB.errMsg, "Fehler in der color.properties Datei",
					JOptionPane.WARNING_MESSAGE);
		}

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

}
