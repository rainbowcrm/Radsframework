package com.techtrade.rads.framework.context;

import java.util.Locale;
import java.util.Map;


public interface IRadsContext {

	public String  getUser();
	public void setUser(String user) ;
	public Map getProperties() ;
	public void setProperties(Map properties) ;
	public void addProperty(String key, String value) ;
	public boolean isAuthenticated() ;
	public boolean isAuthorized() ;
	public void setAuthenticated(boolean isAuth) ;
	public Locale getLocale();
	public void setLocale(Locale locale) ;
	 
}
