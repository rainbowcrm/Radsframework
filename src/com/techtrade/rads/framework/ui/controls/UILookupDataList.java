package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UILookupDataList extends UIControl {
	
	String lookupType ;
	String dataListControl;
	String textId;

	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getDataListControl() {
		return dataListControl;
	}

	public void setDataListControl(String dataListControl) {
		this.dataListControl = dataListControl;
	}
	
	public UILookupDataList(String id) {
		super(id);
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}
	
	
	

}
