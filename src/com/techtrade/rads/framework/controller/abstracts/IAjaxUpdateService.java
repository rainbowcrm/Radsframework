package com.techtrade.rads.framework.controller.abstracts;


import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;

public interface IAjaxUpdateService {

	public String update(String jsonString ,IRadsContext ctx) ;
	
	public IRadsContext generateContext(HttpServletRequest request);
}
