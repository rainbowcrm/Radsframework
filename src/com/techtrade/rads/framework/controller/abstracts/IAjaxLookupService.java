package com.techtrade.rads.framework.controller.abstracts;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;

public interface IAjaxLookupService {
	
	
	public String lookupValues(Map<String,String> searchFields,IRadsContext ctx) ;
	
	public IRadsContext generateContext(HttpServletRequest request);

	
}
