package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIURL extends UIControl {

	int size ;
	String placeHolder ;
	
	boolean required;
	boolean autofocus;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getPlaceHolder() {
		return placeHolder;
	}
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isAutofocus() {
		return autofocus;
	}
	public void setAutofocus(boolean autofocus) {
		this.autofocus = autofocus;
	}
	
	
	
	
}
