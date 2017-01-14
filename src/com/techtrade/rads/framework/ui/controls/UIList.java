package com.techtrade.rads.framework.ui.controls;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.utils.Utils;

public class UIList extends UIControl {

	private  Map<String,String> options = null;
	private String size ;
	String dblClickJS; 
	String onChangeJS;

	
	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public void setOptionsAsList(List <String> options) {
		Map <String,String> opts = new HashMap<String , String>();
		if (!Utils.isNullList(options))
			for (String st : options ) {
				opts.put(st, st);
			}
		this.options = opts;
	}
	
	public void addOption(String key, String value) {
		if (options == null)
			options = new HashMap();
		options.put(key, value);
	}

	public UIList(String id, Map<String, String> options) {
		super(id);
		this.options = options;
	}
	
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public UIList(String id) {
		super(id);
	}

	public String getDblClickJS() {
		return dblClickJS;
	}

	public void setDblClickJS(String dblClickJS) {
		this.dblClickJS = dblClickJS;
	}

	public String getOnChangeJS() {
		return onChangeJS;
	}

	public void setOnChangeJS(String onChangeJS) {
		this.onChangeJS = onChangeJS;
	}
	
	
	
}
