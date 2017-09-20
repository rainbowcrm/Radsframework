package com.techtrade.rads.framework.ui.controls;

import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UILookupDataList extends UIControl {
	
	String lookupType ;
	String dataListControl;
	String listId;
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

	

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public Map<String, String> getSupplimentaryFields() {
		return supplimentaryFields;
	}
	public void setSupplimentaryFields(Map<String, String> supplimentaryFields) {
		this.supplimentaryFields = supplimentaryFields;
	}
	
	
	

}
