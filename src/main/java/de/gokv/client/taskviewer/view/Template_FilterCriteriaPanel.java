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
	public Pattern_DatePickerField gebDatePickerField;
	public Pattern_DatePickerField orderDatePickerField;
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
		kvnrPh = new Pattern_PlaceholderTextField("Krankenversichertennummer (laut eGk)", getLayout().columnWidths[1]*2, 1, 1, 2 );
		this.add(kvnrPh, kvnrPh.getGbc_placeholder());

		// << Name >>
		namePh = new Pattern_PlaceholderTextField("Name", getLayout().columnWidths[1]-5,1,2,1);
		this.add(namePh, namePh.getGbc_placeholder());

		// << Vorname >>
		vnamePh = new Pattern_PlaceholderTextField("Vorname", getLayout().columnWidths[2]-10,2,2,1);
		vnamePh.getGbc_placeholder().insets = new Insets(0, 10, 0, 0);
		this.add(vnamePh, vnamePh.getGbc_placeholder());

		// << Geburtsdatum >>
		gebDatePickerField = new Pattern_DatePickerField("Geburtstag",1 ,3);
		this.add(gebDatePickerField.getDateLabel(), gebDatePickerField.getGbc_dateLabel());
		this.add(gebDatePickerField.getDatePickerImpl(), gebDatePickerField.getGbc_date());

		// << TaskID >>
		taskIdPh = new Pattern_PlaceholderTextField("TaskID", getLayout().columnWidths[1]*2,1,4,2);
		this.add(taskIdPh, taskIdPh.getGbc_placeholder());

		// << OrderedDate >>
		orderDatePickerField = new Pattern_DatePickerField("Order Date",1 ,5);
		this.add(orderDatePickerField.getDateLabel(), orderDatePickerField.getGbc_dateLabel());
		this.add(orderDatePickerField.getDatePickerImpl(), orderDatePickerField.getGbc_date());

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
		
		// TODO Löschen
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
