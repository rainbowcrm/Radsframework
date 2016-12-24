package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIHidden  extends UIControl {

	
	public UIHidden (String id) {
		setId(id);
	}
	public UIHidden (String id,String value) {
		setId(id);
		setValue(value);
	}

}
