package com.techtrade.rads.framework.context;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface ISettings {

	public  SimpleDateFormat getDateFormat(IRadsContext ctx);
	
	public NumberFormat getCurrencyFormat(IRadsContext ctx);
	
	public String getCurrencySymbol(IRadsContext ctx);
	
	public Locale getLocale(IRadsContext ctx);
	
	public ISettings getDefaultInstance();
	
}
