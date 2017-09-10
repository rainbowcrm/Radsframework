package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIDataList extends UIControl{
	List<String> options = null;
	String onChangeJS;
	String textId;

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	public void setOption(String option) {
		if (options == null)
			 options = new ArrayList<String>();
		this.options.add(option);
	}

	public String getOnChangeJS() {
		return onChangeJS;
	}

	public void setOnChangeJS(String onChangeJS) {
		this.onChangeJS = onChangeJS;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}
	
	public UIDataList(String id) {
		super(id);
	}
	
	
	
}
