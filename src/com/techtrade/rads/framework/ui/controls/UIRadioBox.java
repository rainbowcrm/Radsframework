package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIRadioBox extends UIControl {
private Map<String,String> options = null;

	
	public Map<String,String> getOptions() {
		return options;
	}

	public void setOptions(Map<String,String> options) {
		this.options = options;
	}

	public void addOption(String option, String value) {
		if (options == null)
			options = new HashMap<String,String>();
		options.put(option,value);
	}

	public UIRadioBox(String id,Map<String,String> options) {
		super(id);
		this.options = options;
	}
	
	public UIRadioBox(String id) {
		super(id);
	}
	
	

}
