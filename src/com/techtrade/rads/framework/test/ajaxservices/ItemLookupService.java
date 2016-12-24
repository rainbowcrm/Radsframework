package com.techtrade.rads.framework.test.ajaxservices;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.controller.abstracts.IAjaxLookupService;

public class ItemLookupService implements IAjaxLookupService{

	@Override
	public String lookupValues(Map<String, String> searchFields,IRadsContext ctx) {
		String barcode = searchFields.get("itemBarCode");
		if ("abc".equalsIgnoreCase(barcode)) {
			return "{\"desc\":\"ABCItem\",\"price\":\"25\",\"discount\":\"3\"}";
		}
		if ("xyz".equalsIgnoreCase(barcode)) {
			return "{\"desc\":\"XYZItem\",\"price\":\"15\",\"discount\":\"2\"}";
		}
		return "";
		
	}

	@Override
	public IRadsContext generateContext(HttpServletRequest request) {
		return null;
		
	}
	
	
	
	

	
	
}
