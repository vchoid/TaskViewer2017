package de.gokv.client.taskviewer.ui;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

class DateLabelFormatter extends AbstractFormatter {
		private String datePattern = "dd.MM.yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime((Date) dateFormatter.parseObject(text));
	        return cal;
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	        return "";
	    }
	}