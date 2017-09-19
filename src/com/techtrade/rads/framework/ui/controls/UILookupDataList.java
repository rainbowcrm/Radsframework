package com.techtrade.rads.framework.ui.controls;

import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UILookupDataList extends UIControl {
	
	String lookupType ;
	String dataListControl;
	String textId;
	Map<String,String>  supplimentaryFields;

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

	public Map<String, String> getSupplimentaryFields() {
		return supplimentaryFields;
	}
	public void setSupplimentaryFields(Map<String, String> supplimentaryFields) {
		this.supplimentaryFields = supplimentaryFields;
	}
	
	
	

}
