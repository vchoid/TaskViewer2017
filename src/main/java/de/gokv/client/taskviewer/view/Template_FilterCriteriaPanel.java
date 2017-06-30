package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_ActionContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_KeyContr;
import de.gokv.client.taskviewer.controller.MyFrame_Controller;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Template_FilterCriteriaPanel extends Template_BlockPanel {
	private static final long serialVersionUID = 1L;
	
	private static final Color colorLabelDateTxt = new HexaToRGB("color.label.date.text").parseHexToRGB();
	
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
	public JButton clearAllBtn;
	public JTextField anzFiltTask;
	public String valFiltMsg = "";
	
	public Template_FilterCriteriaPanel(MyFrame_Controller controller) {
		super("Filter Maske", controller);
	}

	@Override
	public GridBagLayout getLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 210, 10 };
		layout.rowHeights = new int[] {40,35,35,35,35,35,35,40 };
		return layout;
	}

	@Override
	public void init() {
		FilterCriteriaPanel_ActionContr filtTaskCont = new FilterCriteriaPanel_ActionContr();
		FilterCriteriaPanel_KeyContr filtTaskKey = new FilterCriteriaPanel_KeyContr();
		
		// << KVNR >>
		pKvnr = new Field_Placeholder();
		pKvnr.setPlaceholder("Krankenversichertennummer (laut eGk)", getLayout().columnWidths[1]*2);
		pKvnr.setBorder(emptyBorder);
		pKvnr.addActionListener(filtTaskCont);
		GridBagConstraints gbc_kvnr = new GridBagConstraints();
		gbc_kvnr.anchor = GridBagConstraints.NORTH;
		gbc_kvnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvnr.gridwidth = 2;
		gbc_kvnr.gridx = 1;
		gbc_kvnr.gridy = 1;
		this.add(pKvnr, gbc_kvnr);

		// << Name >>
		pName = new Field_Placeholder();
		pName.setPlaceholder("Name", getLayout().columnWidths[1]);
		pName.setBorder(emptyBorder);
		pName.addActionListener(filtTaskCont);
		pName.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.NORTH;
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.insets = new Insets(0, 0, 0, 0);
		gbc_name.gridx = 1;
		gbc_name.gridy = 2;
		this.add(pName, gbc_name);

		// << Vorname >>
		pVname = new Field_Placeholder();
		pVname.setPlaceholder("Vorname", getLayout().columnWidths[2]-10);
		pVname.setBorder(emptyBorder);
		pVname.addActionListener(filtTaskCont);
		pVname.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_vName = new GridBagConstraints();
		gbc_vName.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_vName.fill = GridBagConstraints.HORIZONTAL;
		gbc_vName.insets = new Insets(0, 10, 0, 0);
		gbc_vName.gridx = 2;
		gbc_vName.gridy = 2;
		this.add(pVname, gbc_vName);

		// << Geburtsdatum >>
		gDateLabel = new JLabel("Geburtstag");
		gDateLabel.setForeground(colorLabelDateTxt);
		gDateLabel.setBorder(emptyBorder);
		gDateLabel.setFont(fontTxt);
		GridBagConstraints gbc_gDateLabel = new GridBagConstraints();
		gbc_gDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_gDateLabel.gridx = 1;
		gbc_gDateLabel.gridy = 3;
		this.add(gDateLabel, gbc_gDateLabel);
		GridBagConstraints gbc_gebDate = new GridBagConstraints();
		gbc_gebDate.insets = new Insets(0, 8, 10, 0);
		gbc_gebDate.gridx = 2;
		gbc_gebDate.gridy = 3;
		gDatePick.setTextEditable(true);
		gebDatePan.setBorder(emptyBorder);
		this.add(gDatePick, gbc_gebDate);

		// << TaskID >>
		pTaskID = new Field_Placeholder();
		pTaskID.setPlaceholder("TaskID", getLayout().columnWidths[1]*2);
		pTaskID.setBorder(emptyBorder);
		pTaskID.addActionListener(filtTaskCont);
		pTaskID.addKeyListener(filtTaskKey);
		GridBagConstraints gbc_taskID = new GridBagConstraints();
		gbc_taskID.anchor = GridBagConstraints.NORTH;
		gbc_taskID.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID.gridwidth = 2;
		gbc_taskID.gridx = 1;
		gbc_taskID.gridy = 4;
		this.add(pTaskID, gbc_taskID);

		// << OrderedDate >>
		oDateLabel = new JLabel("Order Date");
		oDateLabel.setForeground(colorLabelDateTxt);
		oDateLabel.setBorder(emptyBorder);
		oDateLabel.setFont(fontTxt);
		GridBagConstraints gbc_oDateLabel = new GridBagConstraints();
		gbc_oDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_oDateLabel.gridx = 1;
		gbc_oDateLabel.gridy = 5;
		this.add(oDateLabel, gbc_oDateLabel);
		GridBagConstraints gbc_orderDate = new GridBagConstraints();
		gbc_orderDate.insets = new Insets(0, 8, 10, 0);
		gbc_orderDate.gridx = 2;
		gbc_orderDate.gridy = 5;
		oDatePick.setTextEditable(true);
		orderDatePan.setBorder(emptyBorder);
		this.add(oDatePick, gbc_orderDate);

		// << Button "Filter anwenden" >>
		filterBtn = new JButton("Filter anwenden");
		filterBtn.setBorder(btnBorder);
		filterBtn.setBackground(colorBtnBG);
		filterBtn.setForeground(colorBtnTxt);
		filterBtn.setFont(fontTxt);
		filterBtn.addActionListener(filtTaskCont);
		GridBagConstraints gbc_filterBtn = new GridBagConstraints();
		gbc_filterBtn.anchor = GridBagConstraints.NORTH;
		gbc_filterBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterBtn.insets = new Insets(20, 0, 0, 5);
		gbc_filterBtn.gridx = 1;
		gbc_filterBtn.gridy = 6;
		this.add(filterBtn, gbc_filterBtn);

		// << Button "Felder leeren" >>
		clearAllBtn = new JButton("Felder zurücksetzen");
		clearAllBtn.setBorder(btnBorder);
		clearAllBtn.setBackground(colorBtnBG);
		clearAllBtn.setForeground(colorBtnTxt);
		clearAllBtn.setFont(fontTxt);
		clearAllBtn.addActionListener(filtTaskCont);
		GridBagConstraints gbc_clearFieldBtn = new GridBagConstraints();
		gbc_clearFieldBtn.anchor = GridBagConstraints.NORTH;
		gbc_clearFieldBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearFieldBtn.insets = new Insets(20, 10, 0, 0);
		gbc_clearFieldBtn.gridx = 2;
		gbc_clearFieldBtn.gridy = 6;
		this.add(clearAllBtn, gbc_clearFieldBtn);

		// Anzahl der gefilterten Tasks
		anzFiltTask = new JTextField();
		anzFiltTask.setForeground(colorEntryTxt);
		anzFiltTask.setBorder(emptyBorder);
		anzFiltTask.addActionListener(filtTaskCont);
		anzFiltTask.addKeyListener(filtTaskKey);
		anzFiltTask.setOpaque(false);
		this.add(anzFiltTask);
		
		GridBagConstraints gbc_anzFiltTask = new GridBagConstraints();
		gbc_anzFiltTask.anchor = GridBagConstraints.NORTH;
		gbc_anzFiltTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzFiltTask.insets = new Insets(5, 0, 0, 0);
		gbc_anzFiltTask.gridwidth = 1;
		gbc_anzFiltTask.gridx = 1;
		gbc_anzFiltTask.gridy = 7;
		this.add(anzFiltTask, gbc_anzFiltTask);
	}

}
