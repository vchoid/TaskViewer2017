package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_ActionContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_DateContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_KeyContr;
import de.gokv.client.taskviewer.controller.MyFrame_Controller;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Template_FilterCriteriaPanel extends Template_BlockPanel {
	private static final long serialVersionUID = 1L;
	
	private static final Color colorLabelDateTxt = new HexaToRGB("color.label.date.text").parseHexToRGB();
	
	public Pattern_PlaceholderTextField kvnrPh;
	public Pattern_PlaceholderTextField namePh;
	public Pattern_PlaceholderTextField vnamePh;
	public JLabel gebDateLabel;
	public UtilDateModel gebDateModel = new UtilDateModel();
	public JDatePanelImpl gebDatePanelImpl = new JDatePanelImpl(gebDateModel);
	public JDatePickerImpl gebDatePickerImpl = new JDatePickerImpl(gebDatePanelImpl, new DateLabelFormatter());
	public Pattern_PlaceholderTextField taskIdPh;
	public JLabel orderDateLabel;
	public UtilDateModel orderDateModel = new UtilDateModel();
	public JDatePanelImpl orderDatePanelImpl = new JDatePanelImpl(orderDateModel);
	public JDatePickerImpl orderDatePickerImpl = new JDatePickerImpl(orderDatePanelImpl, new DateLabelFormatter());
	public JButton filterBtn;
	public JButton clearAllBtn;
	public JTextField anzFiltEntriesTf;
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
		FilterCriteriaPanel_ActionContr filtTaskContr = new FilterCriteriaPanel_ActionContr();
		FilterCriteriaPanel_KeyContr filtTaskKeyContr = new FilterCriteriaPanel_KeyContr();
		FilterCriteriaPanel_DateContr filtTaskDateContr = new FilterCriteriaPanel_DateContr();
		
		// << KVNR >>
		kvnrPh = new Pattern_PlaceholderTextField();
		kvnrPh.setPlaceholder("Krankenversichertennummer (laut eGk)", getLayout().columnWidths[1]*2);
		kvnrPh.setBorder(emptyBorder);
		kvnrPh.addActionListener(filtTaskContr);
		GridBagConstraints gbc_kvnr = new GridBagConstraints();
		gbc_kvnr.anchor = GridBagConstraints.NORTH;
		gbc_kvnr.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvnr.gridwidth = 2;
		gbc_kvnr.gridx = 1;
		gbc_kvnr.gridy = 1;
		this.add(kvnrPh, gbc_kvnr);

		// << Name >>
		namePh = new Pattern_PlaceholderTextField();
		namePh.setPlaceholder("Name", getLayout().columnWidths[1]-5);
		namePh.setBorder(emptyBorder);
		namePh.addActionListener(filtTaskContr);
		namePh.addKeyListener(filtTaskKeyContr);
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.NORTH;
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.insets = new Insets(0, 0, 0, 0);
		gbc_name.gridx = 1;
		gbc_name.gridy = 2;
		this.add(namePh, gbc_name);

		// << Vorname >>
		vnamePh = new Pattern_PlaceholderTextField();
		vnamePh.setPlaceholder("Vorname", getLayout().columnWidths[2]-10);
		vnamePh.setBorder(emptyBorder);
		vnamePh.addActionListener(filtTaskContr);
		vnamePh.addKeyListener(filtTaskKeyContr);
		GridBagConstraints gbc_vName = new GridBagConstraints();
		gbc_vName.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_vName.fill = GridBagConstraints.HORIZONTAL;
		gbc_vName.insets = new Insets(0, 10, 0, 0);
		gbc_vName.gridx = 2;
		gbc_vName.gridy = 2;
		this.add(vnamePh, gbc_vName);

		// << Geburtsdatum >>
		gebDateLabel = new JLabel("Geburtstag");
		gebDateLabel.setForeground(colorLabelDateTxt);
		gebDateLabel.setBorder(emptyBorder);
		gebDateLabel.setFont(fontTxt);
		GridBagConstraints gbc_gDateLabel = new GridBagConstraints();
		gbc_gDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_gDateLabel.gridx = 1;
		gbc_gDateLabel.gridy = 3;
		this.add(gebDateLabel, gbc_gDateLabel);
		GridBagConstraints gbc_gebDate = new GridBagConstraints();
		gbc_gebDate.insets = new Insets(0, 8, 10, 0);
		gbc_gebDate.gridx = 2;
		gbc_gebDate.gridy = 3;
		gebDatePickerImpl.setTextEditable(true);
		gebDatePanelImpl.setBorder(emptyBorder);
		gebDateModel.addChangeListener(filtTaskDateContr);
		this.add(gebDatePickerImpl, gbc_gebDate);

		// << TaskID >>
		taskIdPh = new Pattern_PlaceholderTextField();
		taskIdPh.setPlaceholder("TaskID", getLayout().columnWidths[1]*2);
		taskIdPh.setBorder(emptyBorder);
		taskIdPh.addActionListener(filtTaskContr);
		taskIdPh.addKeyListener(filtTaskKeyContr);
		GridBagConstraints gbc_taskID = new GridBagConstraints();
		gbc_taskID.anchor = GridBagConstraints.NORTH;
		gbc_taskID.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID.gridwidth = 2;
		gbc_taskID.gridx = 1;
		gbc_taskID.gridy = 4;
		this.add(taskIdPh, gbc_taskID);

		// << OrderedDate >>
		orderDateLabel = new JLabel("Order Date");
		orderDateLabel.setForeground(colorLabelDateTxt);
		orderDateLabel.setBorder(emptyBorder);
		orderDateLabel.setFont(fontTxt);
		GridBagConstraints gbc_oDateLabel = new GridBagConstraints();
		gbc_oDateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_oDateLabel.gridx = 1;
		gbc_oDateLabel.gridy = 5;
		this.add(orderDateLabel, gbc_oDateLabel);
		GridBagConstraints gbc_orderDate = new GridBagConstraints();
		gbc_orderDate.insets = new Insets(0, 8, 10, 0);
		gbc_orderDate.gridx = 2;
		gbc_orderDate.gridy = 5;
		orderDatePickerImpl.setTextEditable(true);
		orderDatePanelImpl.setBorder(emptyBorder);
		orderDateModel.addChangeListener(filtTaskDateContr);
		this.add(orderDatePickerImpl, gbc_orderDate);

		// << Button "Felder leeren" >>
		clearAllBtn = new JButton("Felder zurücksetzen");
		clearAllBtn.setBorder(btnBorder);
		clearAllBtn.setBackground(colorBtnBG);
		clearAllBtn.setForeground(colorBtnTxt);
		clearAllBtn.setFont(fontTxt);
		clearAllBtn.addActionListener(filtTaskContr);
		GridBagConstraints gbc_clearFieldBtn = new GridBagConstraints();
		gbc_clearFieldBtn.anchor = GridBagConstraints.NORTH;
		gbc_clearFieldBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearFieldBtn.insets = new Insets(20, 0, 0, 0);
		gbc_clearFieldBtn.gridwidth = 2;
		gbc_clearFieldBtn.gridx = 1;
		gbc_clearFieldBtn.gridy = 6;
		this.add(clearAllBtn, gbc_clearFieldBtn);
		
		// TODO Löschen und Listener Löschen
		// << Button "Filter anwenden" >>
//		filterBtn = new JButton("Filter anwenden");
//		filterBtn.setBorder(btnBorder);
//		filterBtn.setBackground(colorBtnBG);
//		filterBtn.setForeground(colorBtnTxt);
//		filterBtn.setFont(fontTxt);
//		filterBtn.addActionListener(filtTaskCont);
//		GridBagConstraints gbc_filterBtn = new GridBagConstraints();
//		gbc_filterBtn.anchor = GridBagConstraints.NORTH;
//		gbc_filterBtn.fill = GridBagConstraints.HORIZONTAL;
//		gbc_filterBtn.insets = new Insets(20, 10, 0, 0);
//		gbc_filterBtn.gridx = 2;
//		gbc_filterBtn.gridy = 6;
//		this.add(filterBtn, gbc_filterBtn);

		// Anzahl der gefilterten Tasks
		anzFiltEntriesTf = new JTextField();
		anzFiltEntriesTf.setForeground(colorEntryTxt);
		anzFiltEntriesTf.setBorder(emptyBorder);
		anzFiltEntriesTf.addActionListener(filtTaskContr);
		anzFiltEntriesTf.addKeyListener(filtTaskKeyContr);
		anzFiltEntriesTf.setOpaque(false);
		this.add(anzFiltEntriesTf);
		
		GridBagConstraints gbc_anzFiltTask = new GridBagConstraints();
		gbc_anzFiltTask.anchor = GridBagConstraints.NORTH;
		gbc_anzFiltTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzFiltTask.insets = new Insets(5, 0, 0, 0);
		gbc_anzFiltTask.gridwidth = 1;
		gbc_anzFiltTask.gridx = 1;
		gbc_anzFiltTask.gridy = 7;
		this.add(anzFiltEntriesTf, gbc_anzFiltTask);
	}

}
