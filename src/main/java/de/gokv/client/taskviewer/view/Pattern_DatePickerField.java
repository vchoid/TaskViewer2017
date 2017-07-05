package de.gokv.client.taskviewer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_DateContr;
import de.gokv.client.taskviewer.controller.FilterCriteriaPanel_MouseContr;
import de.gokv.client.taskviewer.utils.HexaToRGB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Pattern_DatePickerField {

	public JLabel dateLabel;
	private GridBagConstraints gbc_dateLabel;
	private GridBagConstraints gbc_date;
	public UtilDateModel dateModel = new UtilDateModel();
	public JDatePanelImpl datePanelImpl = new JDatePanelImpl(dateModel);
	public JDatePickerImpl datePickerImpl = new JDatePickerImpl(datePanelImpl, new DateLabelFormatter());
	
	private static FilterCriteriaPanel_DateContr filtTaskDateContr = new FilterCriteriaPanel_DateContr();
	
	private static final Color colorLabelDateTxt = new HexaToRGB("color.label.date.text").parseHexToRGB();
	private final Border emptyBorder = BorderFactory.createEmptyBorder();
	private final Font fontTxt = new Font("Arial", Font.PLAIN, 15);
	
	public JLabel getDateLabel() {
		return dateLabel;
	}

	public GridBagConstraints getGbc_dateLabel() {
		return gbc_dateLabel;
	}

	public GridBagConstraints getGbc_date() {
		return gbc_date;
	}

	public JDatePickerImpl getDatePickerImpl() {
		return datePickerImpl;
	}

	public Pattern_DatePickerField() {
		
	}
	
	public Pattern_DatePickerField(String labelTitle, int startPosCol, int startPosRow) {
		dateModel.addChangeListener(filtTaskDateContr);
		datePanelImpl.setBorder(emptyBorder);
		datePickerImpl.setTextEditable(true);
		dateLabel = new JLabel(labelTitle);
		dateLabel.setForeground(colorLabelDateTxt);
		dateLabel.setBorder(emptyBorder);
		dateLabel.setFont(fontTxt);
		gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_dateLabel.insets = new Insets(0, 0, 10, 5);
		gbc_dateLabel.gridx = startPosCol;
		gbc_dateLabel.gridy = startPosRow;
		gbc_date = new GridBagConstraints();
		gbc_date.insets = new Insets(0, 8, 10, 0);
		gbc_date.gridx = startPosCol+1;
		gbc_date.gridy = startPosRow;
		
	}
	
}
