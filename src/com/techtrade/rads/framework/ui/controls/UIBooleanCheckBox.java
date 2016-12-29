package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIBooleanCheckBox extends UIControl{
	
	String hiddenControlId;
	String displayText;
	
	public String getHiddenControlId() {
		return hiddenControlId;
	}
	public void setHiddenControlId(String hiddenControlId) {
		this.hiddenControlId = hiddenControlId;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public UIBooleanCheckBox(String id) {
		super(id);
	}
	public UIBooleanCheckBox() {
		
	}
	
	
	

}
