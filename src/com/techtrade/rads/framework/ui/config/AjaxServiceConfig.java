package com.techtrade.rads.framework.ui.config;

import java.util.ArrayList;
import java.util.List;

public class AjaxServiceConfig {
	
	String serviceClass;
	List<String> keys ;
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public void addKey(String key) {
		if (keys == null)
			keys = new ArrayList<String> ();
		keys.add(key);
		
	}
	
	
	

	

}
