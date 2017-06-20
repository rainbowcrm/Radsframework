package com.techtrade.rads.framework.ui.config;

public class PageConfig {
	
	String configId ;
	String  definition;
	String modelClass;
	boolean authRequired = true;
	String accessCode; 
	
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getModelClass() {
		return modelClass;
	}
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	public boolean isAuthRequired() {
		return authRequired;
	}
	public void setAuthRequired(boolean authRequired) {
		this.authRequired = authRequired;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	

}
