package com.techtrade.rads.framework.context;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.ui.abstracts.UIPage;

public class RadsContext  implements IRadsContext{

	Map properties;
	String user;
	boolean isAuthenticated=true;

	
	
	public String getUser() {
		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public void setUser(String user) {
		this.user = user ;
	}
	@Override
	public Map getProperties() {
		return properties ;
	}
	@Override
	public void setProperties(Map properties) {
		this.properties =properties; 
	}
	@Override
	public boolean isAuthenticated() {
		return isAuthenticated ;
		
	}
	public void setAuthenticated(boolean isAuth){
		isAuthenticated = isAuth;
	}
	@Override
	public Locale getLocale() {
		return Locale.US;
	}
	@Override
	public void setLocale(Locale locale) {
		
	}
	
	
	
	
	
}
