package de.gokv.client.taskviewer.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import de.gokv.client.taskviewer.controller.MyFrame_Controller;

public class Template_InfoCSVPanel extends Template_BlockPanel {

	private static final long serialVersionUID = 1L;

//	public JLabel taskID;
//	public JLabel taskID_field;
	public Pattern_InfoField kvnr;
	public Pattern_InfoField name;
	public Pattern_InfoField vName;
	public Pattern_InfoField gebDate;
	public Pattern_InfoField taskID;

	protected Template_InfoCSVPanel(MyFrame_Controller controller) {
		super("Informationen (CSV)", controller);
	}

	@Override
	public GridBagLayout getLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 210, 10 };
		layout.rowHeights = new int[] { 13, 20, 20, 20, 20, 20, 20, 20, 90};
		return layout;
	}

	@Override
	public void init() {

		// 1. KvNr
		kvnr = Pattern_InfoField.createInfoField("KvNr:", 1, 1, 1, 2, 1);
		this.add(kvnr.getlTitle(), kvnr.getGbc_lTitle());
		this.add(kvnr.getlField(), kvnr.getGbc_lField());

		// 2. Name
		name = Pattern_InfoField.createInfoField("Name:", 1, 3, 1, 4, 1);
		this.add(name.getlTitle(), name.getGbc_lTitle());
		this.add(name.getlField(), name.getGbc_lField());
		// 3. Vorname
		vName = Pattern_InfoField.createInfoField("Vorname:", 2, 3, 2, 4, 1);
		this.add(vName.getlTitle(), vName.getGbc_lTitle());
		this.add(vName.getlField(), vName.getGbc_lField());

		// 4. Geburtsdatum
		gebDate = Pattern_InfoField.createInfoField("Geburtsdatum:", 2, 5, 2, 6, 1);
		this.add(gebDate.getlTitle(), gebDate.getGbc_lTitle());
		this.add(gebDate.getlField(), gebDate.getGbc_lField());
		
		// 5. Task ID
		taskID = Pattern_InfoField.createInfoField("TaskID", 1, 7, 1, 8, 2);
		this.add(taskID.getlTitle(), taskID.getGbc_lTitle());
		this.add(taskID.getlField(), taskID.getGbc_lField());
	}

}
