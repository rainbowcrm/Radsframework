package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIErrorList extends UIControl{
 
	List<UIErrorObject> errorObjects ;
	boolean showInTable = true  ;
	

	public List<UIErrorObject> getErrorObjects() {
		return errorObjects;
	}

	public void setErrorObjects(List<UIErrorObject> errorObjects) {
		this.errorObjects = errorObjects;
	}
	
	public void addErrorObjects(UIErrorObject errorObject) {
		if (errorObjects  == null)  {
			errorObjects = new ArrayList<UIErrorObject>();
		}
		this.errorObjects.add(errorObject);
	}

	public boolean isShowInTable() {
		return showInTable;
	}

	public void setShowInTable(boolean showInTable) {
		this.showInTable = showInTable;
	}
	
	
 
 
	
}
