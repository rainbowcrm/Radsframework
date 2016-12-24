package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.utils.Utils;

public class UITableRow extends UIControl {

	List<UITableCol> cols = null;
	String uniqueIdValue ; // uniquey identifying vaue for row.
	String dataType ;
	String rendered; 

	public List<UITableCol> getCols() {
		return cols;
	}

	public void setCols(List<UITableCol> cols) {
		this.cols = cols;
	}
	
	public void addCol(UITableCol col) {
		if (cols == null) {
			cols = new ArrayList<UITableCol> ();
		}
		cols.add(col);
	}

	public String getUniqueIdValue() {
		return uniqueIdValue;
	}

	public void setUniqueIdValue(String uniqueIdValue) {
		this.uniqueIdValue = uniqueIdValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getRendered() {
		return rendered;
	}

	public void setRendered(String rendered) {
		this.rendered = rendered;
	}
	
	

	
	
	
	
	
}
