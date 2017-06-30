package de.gokv.client.taskviewer.view;

import java.awt.GridBagLayout;

import de.gokv.client.taskviewer.controller.MyFrame_Controller;

public class Template_InfoTaskPanel extends Template_BlockPanel {

	private static final long serialVersionUID = 1L;

	public Pattern_InfoField state;
	public Pattern_InfoField taskType;
	public Pattern_InfoField orderDate;
	public Pattern_InfoField evCompl;
	public Pattern_InfoField evInProgs;
	public Pattern_InfoField evReceived;
	public Pattern_InfoField evResult;

	protected Template_InfoTaskPanel(MyFrame_Controller controller) {
		super("Informationen (Task)", controller);
	}

	@Override
	public GridBagLayout getLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 210, 210, 10 };
		layout.rowHeights = new int[] { 20, 30, 30, 30, 30, 30, 30, 30, 30 };
		return layout;
	}

	@Override
	public void init() {
		// 1. State - Status
		state = Pattern_InfoField.createInfoField("Status:", 1, 1);
		this.add(state.getlTitle(), state.getGbc_lTitle());
		this.add(state.getlField(), state.getGbc_lField());

		// 2. Type - Task Type
		taskType = Pattern_InfoField.createInfoField("Task Type:", 1, 2);
		this.add(taskType.getlTitle(), taskType.getGbc_lTitle());
		this.add(taskType.getlField(), taskType.getGbc_lField());

		// 3. OrderedDate - Datum Order
		orderDate = Pattern_InfoField.createInfoField("Order Datum:", 1, 3);
		this.add(orderDate.getlTitle(), orderDate.getGbc_lTitle());
		this.add(orderDate.getlField(), orderDate.getGbc_lField());

		// 4. EventInProgress - Zeitpunkt In Arbeit
		evInProgs = Pattern_InfoField.createInfoField("In Arbeit:", 1, 4);
		this.add(evInProgs.getlTitle(), evInProgs.getGbc_lTitle());
		this.add(evInProgs.getlField(), evInProgs.getGbc_lField());

		// 5. EventCompleted - Zeitpunkt Bereit zur Abholung
		evCompl = Pattern_InfoField.createInfoField("Bereit zur Abholung:", 1, 5);
		this.add(evCompl.getlTitle(), evCompl.getGbc_lTitle());
		this.add(evCompl.getlField(), evCompl.getGbc_lField());

		// 6. EventReceived - Zeitpunkt Abholung
		evReceived = Pattern_InfoField.createInfoField("Abholung:", 1, 6);
		this.add(evReceived.getlTitle(), evReceived.getGbc_lTitle());
		this.add(evReceived.getlField(), evReceived.getGbc_lField());

		// 7. EventResult - Zeitpunkt Prüfung
		evResult = Pattern_InfoField.createInfoField("Prüfung:", 1, 7);
		this.add(evResult.getlTitle(), evResult.getGbc_lTitle());
		this.add(evResult.getlField(), evResult.getGbc_lField());
	}

}
