package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class AjaxGroup extends UIControl{
	
	Map <String,String> requestElements;
	Map <String,String> responseElements;
	
	String ajaxService;
	
	public Map <String,String> getRequestElements() {
		return requestElements;
	}
	
	public void setRequestElements(Map <String,String> elements) {
		this.requestElements = elements;
	}
	
	public void addRequestElement (String key, String val) {
		if (requestElements == null) 
			requestElements = new HashMap <String,String>();
		requestElements.put(key,val);
	}
	
	
	
	public Map <String,String>  getResponseElements() {
		return responseElements;
	}

	public void setResponseElements(Map <String,String>  responseElements) {
		this.responseElements = responseElements;
	}
	
	public void addResponseElement (String key, String val) {
		if (responseElements == null) 
			responseElements =new HashMap <String,String>();
		responseElements.put(key, val);
	}
	
	

	public String getAjaxService() {
		return ajaxService;
	}
	public void setAjaxService(String ajaxService) {
		this.ajaxService = ajaxService;
	}
	
	
}
