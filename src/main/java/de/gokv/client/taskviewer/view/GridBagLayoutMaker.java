package de.gokv.client.taskviewer.view;

import java.awt.GridBagLayout;

public class GridBagLayoutMaker {
	
	public GridBagLayout layout;
	
	public GridBagLayoutMaker() {
		super();
	}
	
	public static int[] createGBLArray(int sizeArr, int content, int border){
		int[] rows = new int[sizeArr];
		rows[0] = border;
		rows[sizeArr-1] = border;
		for (int i = 1; i < rows.length-1; i++) {
			rows[i] = content;
		}
		return rows;
	}

	public GridBagLayout createGBLayout(int[] columnWidth, int[] rowheight){
		layout = new GridBagLayout();
		layout.columnWidths = columnWidth;
		layout.rowHeights = rowheight;
		return layout;
	}

	public GridBagLayout getLayout() {
		return layout;
	}
	
	
	
}
