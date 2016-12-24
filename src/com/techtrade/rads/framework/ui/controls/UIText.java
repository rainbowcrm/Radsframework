package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;

public class UIText extends UIControl {

	int size ;
	boolean onlyNumbers = false ;
	boolean readOnly;
	String placeHolder;
	String onfocusin ;
	String onfocusout;
	
	
	boolean required;
	boolean autofocus;
	
	
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
	
	
	public UIText (String id) {
		setId(id);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isOnlyNumbers() {
		return onlyNumbers;
	}

	public void setOnlyNumbers(boolean onlyNumbers) {
		this.onlyNumbers = onlyNumbers;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public String getOnfocusin() {
		return onfocusin;
	}

	public void setOnfocusin(String onfocusin) {
		this.onfocusin = onfocusin;
	}

	public String getOnfocusout() {
		return onfocusout;
	}

	public void setOnfocusout(String onfocusout) {
		this.onfocusout = onfocusout;
	}
	
	
	
	
	
	

}
