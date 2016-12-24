package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIFileUpload extends UIControl {
	
	boolean multiple = false;
	int maxSize ;
	String accept ;
	String onChangeJS;

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public UIFileUpload (String id) {
		setId(id);
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getOnChangeJS() {
		return onChangeJS;
	}

	public void setOnChangeJS(String onChangeJS) {
		this.onChangeJS = onChangeJS;
	}
	
	
	

	
	

}
