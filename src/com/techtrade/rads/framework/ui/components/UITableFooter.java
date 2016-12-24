package com.techtrade.rads.framework.ui.components;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;

public class UITableFooter  extends UIControl {
	UITableRow row ;

	public UITableRow getRow() {
		return row;
	}

	public void setRow(UITableRow row) {
		this.row = row;
	}

	public UITableFooter(UITableRow row) {
		super();
		this.row = row;
	}
	
	public UITableFooter(){ 
		
	}

	
	
}
