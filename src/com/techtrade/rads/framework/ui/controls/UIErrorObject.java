package com.techtrade.rads.framework.ui.controls;

import java.util.Map;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIErrorObject extends UIControl{
	
	public enum ErrorType {
		WARNING, ERROR 
	} ;
	
	
	String messageKey;
	String message;
	ErrorType errorType; 
	Map<String , String > overRideCodes;
	
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorType getErrorType() {
		return errorType;
	}
	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
	public Map<String, String> getOverRideCodes() {
		return overRideCodes;
	}
	public void setOverRideCodes(Map<String, String> overRideCodes) {
		this.overRideCodes = overRideCodes;
	}
	
	
	
	

}
