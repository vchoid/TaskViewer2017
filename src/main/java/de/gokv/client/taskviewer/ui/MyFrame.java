package de.gokv.client.taskviewer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import de.gokv.client.taskviewer.controller.FilterTaskListController;
import de.gokv.client.taskviewer.controller.MyFrameController;
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
	private Border fieldBorder;
	private JPanel filterPanel;
	public static Field_Placeholder pKvnr;
	public static Field_Placeholder pName;
	public static Field_Placeholder pVname;
	public static JLabel gDateLabel;
	public UtilDateModel model_geb = new UtilDateModel();
	public JDatePanelImpl gebDatePan = new JDatePanelImpl(model_geb);
	public JDatePickerImpl gDatePick = new JDatePickerImpl(gebDatePan, new DateLabelFormatter());
	public static Field_Placeholder pTaskID;
	public static JLabel oDateLabel;
	public UtilDateModel model_ord = new UtilDateModel();
	public JDatePanelImpl orderDatePan = new JDatePanelImpl(model_ord);
	public JDatePickerImpl oDatePick = new JDatePickerImpl(orderDatePan, new DateLabelFormatter());
	public static JButton filterBtn;
	// Task Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TitledBorder taskBorder;
	private JPanel taskPanel;
	private JButton detailsBtn;
	private MyFrameController controller;
	// Info Panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private TitledBorder infoBorder;
	private JPanel infoPanel;
	JLabel infoLabel;
	
	// Style ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private Font title = new Font("Arial",Font.BOLD, 16);
	private Font txt = new Font("Arial", Font.PLAIN, 15);
	private Color pan241_C = new Color(166, 31, 125);
	private Color pan2736_C = new Color(35, 45, 141);
	private Border btnBorder = BorderFactory.createEmptyBorder(8,5,8,5);
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	
	//MODEL !!ACHTUNG
	public JList<String> taskList;
	/**
	 * Konstruktor
	 */
	public MyFrame() {
		setTitle("GoKV-TaskViewer");
		setSize(new Dimension(800, 540));
		setAlwaysOnTop(true);
		
		controller = new MyFrameController(this);
		
		// Content-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		contPanel = new JPanel();
		FlowLayout fl_contPanel = new FlowLayout();
		fl_contPanel.setAlignment(FlowLayout.LEFT);
		contPanel.setLayout(fl_contPanel);
		setContentPane(contPanel);
				
		// Input-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		filtBorder = BorderFactory.createTitledBorder("Filter Maske");
		filtBorder.setTitleJustification(TitledBorder.CENTER);
		filtBorder.setTitleColor(pan2736_C);
		filtBorder.setTitleFont(title);
		filtBorder.setBorder(emptyBorder);
		fieldBorder = BorderFactory.createEmptyBorder();
		filterPanel = new JPanel();
		filterPanel.setBorder(filtBorder);
		filterPanel.setBackground(Color.LIGHT_GRAY);
		GridBagLayout gbl_filPanel = new GridBagLayout();
		gbl_filPanel.columnWidths = new int[]{10,200,200,10};
		gbl_filPanel.rowHeights = new int[]{10,30,30,30,30,30,30,10};
		filterPanel.setLayout(gbl_filPanel);
		contPanel.add(filterPanel);
		
						
		// << KVNR >>
		pKvnr = new Field_Placeholder();
		pKvnr.setPlaceholder("KvNr/Ordnungsbegriff", gbl_filPanel.columnWidths[1]+gbl_filPanel.columnWidths[2]+gbl_filPanel.columnWidths[3]);
		pKvnr.setBorder(fieldBorder);
		GridBagConstraints gbc_kvnr = new GridBagConstraints();
		gbc_kvnr.anchor = GridBagConstraints.NORTH;
		gbc_kvnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvnr.gridwidth = 2;
		gbc_kvnr.gridx = 1;
		gbc_kvnr.gridy = 1;
		filterPanel.add(pKvnr, gbc_kvnr);
		
		// << Name >>
		pName = new Field_Placeholder();
		pName.setPlaceholder("Name", gbl_filPanel.columnWidths[2]-5);
		pName.setBorder(fieldBorder);
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
		pVname.setBorder(fieldBorder);
		GridBagConstraints gbc_vName = new GridBagConstraints();
		gbc_vName.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_vName.fill = GridBagConstraints.HORIZONTAL;
		gbc_vName.insets = new Insets(0, 10, 10, 10);
		gbc_vName.gridx = 2;
		gbc_vName.gridy = 2;
		filterPanel.add(pVname, gbc_vName);
		
		// << Geburtsdatum >>
		gDateLabel = new JLabel("Geburtstag");
		gDateLabel.setForeground(Color.GRAY);
		gDateLabel.setBorder(fieldBorder);
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
		pTaskID.setPlaceholder("TaskID", gbl_filPanel.columnWidths[1]+gbl_filPanel.columnWidths[2]+gbl_filPanel.columnWidths[3]);
		pTaskID.setBorder(fieldBorder);
		GridBagConstraints gbc_taskID = new GridBagConstraints();
		gbc_taskID.anchor = GridBagConstraints.NORTH;
		gbc_taskID.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID.gridwidth = 2;
		gbc_taskID.gridx = 1;
		gbc_taskID.gridy = 4;
		filterPanel.add(pTaskID, gbc_taskID);
		
		// << OrderedDate >>
		oDateLabel = new JLabel("Order Date");
		oDateLabel.setForeground(Color.GRAY);
		oDateLabel.setBorder(fieldBorder);
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
		filterBtn.setBackground(pan241_C);
		filterBtn.setForeground(Color.WHITE);
		filterBtn.setFont(txt);
		// dem ActionListener DIESEN Frame bekannt machen mit this
		filterBtn.addActionListener(new FilterTaskListController(this, controller.getModel()));
		GridBagConstraints gbc_filterBtn = new GridBagConstraints();
		gbc_filterBtn.anchor = GridBagConstraints.NORTH;
		gbc_filterBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterBtn.gridwidth = 2;
		gbc_filterBtn.gridx = 1;
		gbc_filterBtn.gridy = 6;
		filterPanel.add(filterBtn, gbc_filterBtn);

		// TaskPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		taskBorder = BorderFactory.createTitledBorder("Task Liste");
		taskBorder.setTitleJustification(TitledBorder.CENTER);
		taskBorder.setTitleColor(pan2736_C);
		taskBorder.setTitleFont(title);
		taskBorder.setBorder(emptyBorder);
		taskPanel = new JPanel();
		taskPanel.setBorder(taskBorder);
		taskPanel.setBackground(Color.LIGHT_GRAY);
		GridBagLayout gbl_taskPan = new GridBagLayout();
		gbl_taskPan.columnWidths = new int[]{10,260,10};
		gbl_taskPan.rowHeights = new int[]{10,157,0,11};
		taskPanel.setLayout(gbl_taskPan);
		contPanel.add(taskPanel);
		
				
		// << Task-Liste >>
		taskList = new JList<>(controller.getFilteredTasks());
		taskList.setVisibleRowCount(7);
		taskList.setFont(txt);
		JScrollPane scrollTask = new JScrollPane(taskList);
		GridBagConstraints gbc_scrollTask = new GridBagConstraints();
		gbc_scrollTask.anchor = GridBagConstraints.NORTH;
		gbc_scrollTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollTask.gridwidth = 1;
		gbc_scrollTask.gridx = 1;
		gbc_scrollTask.gridy = 1;
		taskPanel.add(scrollTask, gbc_scrollTask);
		
		// << Detail-Button >>
		detailsBtn = new JButton("Task laden");
		detailsBtn.setBorder(btnBorder);
		detailsBtn.setBackground(pan241_C);
		detailsBtn.setForeground(Color.WHITE);
		detailsBtn.setFont(txt);
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTH;
		gbc_detailBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailBtn.insets = new Insets(0, 0, 0, 0);
		gbc_detailBtn.gridwidth = 1;
		gbc_detailBtn.gridx = 1;
		gbc_detailBtn.gridy = 2;
		taskPanel.add(detailsBtn, gbc_detailBtn);

		// Info-Panel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		infoBorder = BorderFactory.createTitledBorder("Informationen");
		infoBorder.setTitleJustification(TitledBorder.CENTER);
		infoBorder.setTitleColor(pan2736_C);
		infoBorder.setTitleFont(title);
		infoBorder.setBorder(emptyBorder);
		infoPanel = new JPanel();
		infoPanel.setBorder(infoBorder);
		infoPanel.setBackground(Color.LIGHT_GRAY);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{10,340,10};
		gbl_infoPanel.rowHeights = new int[]{10,200};
		infoPanel.setLayout(gbl_infoPanel);
		contPanel.add(infoPanel);
		
		infoLabel = new JLabel("weitere Infos");
		infoLabel.setFont(txt);
		GridBagConstraints gbc_infoLabel = new GridBagConstraints();
		gbc_infoLabel.anchor = GridBagConstraints.NORTH;
		gbc_infoLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_infoLabel.insets = new Insets(0, 0, 0, 0);
		gbc_infoLabel.gridwidth = 1;
		gbc_infoLabel.gridx = 0;
		gbc_infoLabel.gridy = 0;
		infoPanel.add(infoLabel, gbc_infoLabel);
		
		
		/**
		 * Beendet die Anwendung. Sichtbarkeit auf true.
		 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
