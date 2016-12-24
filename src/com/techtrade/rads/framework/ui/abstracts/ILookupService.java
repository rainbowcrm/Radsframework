package com.techtrade.rads.framework.ui.abstracts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;

public interface ILookupService {

	public  List<Object> lookupData(IRadsContext ctx,String searchString, int from , int noRecords );
	
	public abstract IRadsContext generateContext(HttpServletRequest request);
}
