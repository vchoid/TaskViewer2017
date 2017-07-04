package de.gokv.client.taskviewer.view;

import javax.swing.JLabel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Pattern_DatePickerField {

	public JLabel dateLabel;
	public UtilDateModel dateModel = new UtilDateModel();
	public JDatePanelImpl datePanelImpl = new JDatePanelImpl(dateModel);
	public JDatePickerImpl datePickerImpl = new JDatePickerImpl(datePanelImpl, new DateLabelFormatter());
	
	
	
}
