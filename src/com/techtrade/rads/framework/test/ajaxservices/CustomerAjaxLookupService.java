package com.techtrade.rads.framework.test.ajaxservices;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.controller.abstracts.IAjaxLookupService;

public class CustomerAjaxLookupService  implements IAjaxLookupService{

	@Override
	public String lookupValues(Map<String,String> searchFields,IRadsContext ctx) {
		String phone = searchFields.get("customerphone");
		String email = searchFields.get("customeremail");
		if ("unni@abc.com".equalsIgnoreCase(email)) {
			return "{\"customerfirstName\":\"Unni\",\"customerlastname\":\"kris\",\"customeraddress\":\"Bangalore\",\"customerphone\":\"973922565\",\"customeremail\":\"unni@abc.com\" }";
		}
		if ("7760829591".equalsIgnoreCase(phone)) {
			return "{\"customerfirstName\":\"Sreyas\",\"customerlastname\":\"kris\",\"customeraddress\":\"Bangalore\",\"customerphone\":\"7760829591\",\"customeremail\":\"srrys@mmm.com\" }";
		}
		return  "";
	}

	@Override
	public IRadsContext generateContext(HttpServletRequest request) {
		return null;
		
	}
	
	
	



}
