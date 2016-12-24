package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;

public class UICheckBox extends UIControl {
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

	public UICheckBox(String id, String option) {
		super(id);
		setExternalize(true);
		addOption(option,option);
	}
	public UICheckBox(String id, Map<String,String> options) {
		super(id);
		setExternalize(true);
		this.options = options;
	}
	
	public UICheckBox(String id) {
		super(id);
		setExternalize(true);
	}
	

}
