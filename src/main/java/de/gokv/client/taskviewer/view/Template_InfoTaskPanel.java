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
		layout.rowHeights = new int[] { 13, 20, 20, 20, 20, 20, 20, 20, 90 };
		return layout;
	}

	@Override
	public void init() {
		// 1. State - Status
		state = Pattern_InfoField.createInfoField("Status:", 1, 1, 1, 2, 1);
		this.add(state.getlTitle(), state.getGbc_lTitle());
		this.add(state.getlField(), state.getGbc_lField());

		// 2. OrderedDate - Datum Order
		orderDate = Pattern_InfoField.createInfoField("Order Datum:", 2, 1, 2, 2, 1);
		this.add(orderDate.getlTitle(), orderDate.getGbc_lTitle());
		this.add(orderDate.getlField(), orderDate.getGbc_lField());
		
		// 3. Type - Task Type
		taskType = Pattern_InfoField.createInfoField("Task Type:", 1, 3, 1, 4, 1);
		this.add(taskType.getlTitle(), taskType.getGbc_lTitle());
		this.add(taskType.getlField(), taskType.getGbc_lField());

		// 4. EventInProgress - Zeitpunkt In Arbeit
		evInProgs = Pattern_InfoField.createInfoField("In Arbeit:", 1, 5, 1, 6, 1);
		this.add(evInProgs.getlTitle(), evInProgs.getGbc_lTitle());
		this.add(evInProgs.getlField(), evInProgs.getGbc_lField());

		// 5. EventResult - Zeitpunkt Prüfung
		evResult = Pattern_InfoField.createInfoField("Prüfung:", 2, 5, 2, 6, 1);
		this.add(evResult.getlTitle(), evResult.getGbc_lTitle());
		this.add(evResult.getlField(), evResult.getGbc_lField());
		
		// 6. EventCompleted - Zeitpunkt Bereit zur Abholung
		evCompl = Pattern_InfoField.createInfoField("Bereit zur Abholung:", 1, 7, 1, 8, 1);
		this.add(evCompl.getlTitle(), evCompl.getGbc_lTitle());
		this.add(evCompl.getlField(), evCompl.getGbc_lField());

		// 7. EventReceived - Zeitpunkt Abholung
		evReceived = Pattern_InfoField.createInfoField("Abholung:", 2, 7, 2, 8, 1);
		this.add(evReceived.getlTitle(), evReceived.getGbc_lTitle());
		this.add(evReceived.getlField(), evReceived.getGbc_lField());

	}

}
