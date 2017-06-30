package de.gokv.client.taskviewer.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import de.gokv.client.taskviewer.controller.MyFrame_Controller;

public class Template_InfoCSVPanel extends Template_BlockPanel {

	private static final long serialVersionUID = 1L;

	public JLabel taskID;
	public JLabel taskID_field;
	public Template_InfoField kvnr;
	public Template_InfoField name;
	public Template_InfoField vName;
	public Template_InfoField gebDate;

	protected Template_InfoCSVPanel(MyFrame_Controller controller) {
		super("Informationen (CSV)", controller);
	}

	@Override
	public GridBagLayout getLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 210, 10 };
		layout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 100 };
		return layout;
	}

	@Override
	public void init() {
		// 1. Task ID
		taskID = new JLabel("TaskID");
		taskID.setFont(fontInfoTitle);
		taskID.setForeground(colorInfoTitleTxt);
		GridBagConstraints gbc_taskdIDLabel = new GridBagConstraints();
		gbc_taskdIDLabel.anchor = GridBagConstraints.NORTH;
		gbc_taskdIDLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskdIDLabel.insets = new Insets(15, 0, 0, 0);
		gbc_taskdIDLabel.gridx = 1;
		gbc_taskdIDLabel.gridy = 1;
		this.add(taskID, gbc_taskdIDLabel);

		taskID_field = new JLabel();
		taskID_field.setFont(fontTxt);
		taskID_field.setForeground(colorInfoTxt);
		GridBagConstraints gbc_taskID_field = new GridBagConstraints();
		gbc_taskID_field.anchor = GridBagConstraints.NORTH;
		gbc_taskID_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskID_field.insets = new Insets(0, 20, 0, 0);
		gbc_taskID_field.gridwidth = 2;
		gbc_taskID_field.gridx = 1;
		gbc_taskID_field.gridy = 2;
		this.add(taskID_field, gbc_taskID_field);

		// 2. KvNr
		kvnr = Template_InfoField.createInfoField("KvNr:", 1, 2);
		this.add(kvnr.getlTitle(), kvnr.getGbc_lTitle());
		this.add(kvnr.getlField(), kvnr.getGbc_lField());

		// 3. Name
		name = Template_InfoField.createInfoField("Name:", 1, 3);
		this.add(name.getlTitle(), name.getGbc_lTitle());
		this.add(name.getlField(), name.getGbc_lField());
		// 4. Vorname
		vName = Template_InfoField.createInfoField("Vorname:", 1, 4);
		this.add(vName.getlTitle(), vName.getGbc_lTitle());
		this.add(vName.getlField(), vName.getGbc_lField());

		// 5. Geburtsdatum
		gebDate = Template_InfoField.createInfoField("Geburtsdatum:", 1, 5);
		this.add(gebDate.getlTitle(), gebDate.getGbc_lTitle());
		this.add(gebDate.getlField(), gebDate.getGbc_lField());
	}

}
