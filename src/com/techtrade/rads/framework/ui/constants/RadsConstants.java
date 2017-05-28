package com.techtrade.rads.framework.ui.constants;

import java.util.Locale;
import java.util.TimeZone;

public enum RadsConstants {
     
	DEFAULT;
	
	private  String dateFormat ="yyyy-MM-dd";
	private  String dateTimeFormat="mm/dd/yyyy 24hh:mm";
	private  TimeZone timeZone = TimeZone.getDefault();
	private  Locale locale = Locale.getDefault();
	private  String tabWidth = "80%";
	private  String tableWidth = "100%";
	private  String lookupIcon = "/images/rads/mg.png";
	private  String lookupURL = "/rdscontroller?page=Lookup";
	private  String lookupWindowHeight = "400" ; 
	private  String lookupWindowWidth = "250" ;
	private  int lookupListSize = 10;
	private  String lookupFrom = "0";
	private  String lookupWindowTitle = "Lookup";
	private  String lookupSearchObjectName = "txtSearch";
	
	 public static final String TRUE = "true" ; 	
	
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getDateTimeFormat() {
		return dateTimeFormat;
	}
	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getTabWidth() {
		return tabWidth;
	}
	public void setTabWidth(String tabWidth) {
		this.tabWidth = tabWidth;
	}
	public String getTableWidth() {
		return tableWidth;
	}
	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}
	public String getLookupIcon() {
		return lookupIcon;
	}
	public void setLookupIcon(String lookupICon) {
		this.lookupIcon = lookupICon;
	}
	public String getLookupURL() {
		return lookupURL;
	}
	public void setLookupURL(String lookupURL) {
		this.lookupURL = lookupURL;
	}
	public String getLookupWindowHeight() {
		return lookupWindowHeight;
	}
	public void setLookupWindowHeight(String lookupWindowHeight) {
		this.lookupWindowHeight = lookupWindowHeight;
	}
	public String getLookupWindowWidth() {
		return lookupWindowWidth;
	}
	public void setLookupWindowWidth(String lookupWindowWidth) {
		this.lookupWindowWidth = lookupWindowWidth;
	}
	public int getLookupListSize() {
		return lookupListSize;
	}
	public void setLookupListSize(int lookupListSize) {
		this.lookupListSize = lookupListSize;
	}
	public String getLookupWindowTitle() {
		return lookupWindowTitle;
	}
	public void setLookupWindowTitle(String lookupWindowTitle) {
		this.lookupWindowTitle = lookupWindowTitle;
	}
	public String getLookupSearchObjectName() {
		return lookupSearchObjectName;
	}
	public void setLookupSearchObjectName(String lookupSearchObjectName) {
		this.lookupSearchObjectName = lookupSearchObjectName;
	}
	
	
	
}
