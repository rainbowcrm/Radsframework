package com.techtrade.rads.framework.ui.abstracts;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;

public interface ILookupService {

	
	public  Map<String, String> lookupData(IRadsContext ctx,String searchString, int from , int noRecords , String lookupParam , 
			List<String> additionalFieldsRequired);
	
	public abstract IRadsContext generateContext(HttpServletRequest request, UIPage page );
}
