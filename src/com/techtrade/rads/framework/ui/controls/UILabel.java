package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;

public class UILabel extends UIControl implements Cloneable{
	String label ;

	int size ;
	
	
	
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public UILabel(String id) {
		super(id);
		setExternalize(true);
	}
	
	public UILabel(String id, String label) {
		super(id);
		this.label = label;
		setExternalize(true);
	}

	
	
	

}
