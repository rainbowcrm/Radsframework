package com.techtrade.rads.framework.ui.components;

import com.techtrade.rads.framework.ui.abstracts.UIControl;



public class UITableHead  extends UIControl {
	UITableRow row ;

	public UITableRow getRow() {
		return row;
	}

	public void setRow(UITableRow row) {
		this.row = row;
	}

	public UITableHead(UITableRow row) {
		super();
		this.row = row;
	}
	
	public UITableHead(){ 
		
	}

	
}
